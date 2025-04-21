package PathCarrer.API.Service.userProfile;

import PathCarrer.API.DTO.GetsPathByUserDTO.AddPath;
import PathCarrer.API.DTO.GetsPathByUserDTO.PathGetDTO;
import PathCarrer.API.DTO.Update.AddSeeClassDTO;
import PathCarrer.API.DTO.UsersDTO.NoteDTO;
import PathCarrer.API.DTO.UsersDTO.UpdateProfileName;
import PathCarrer.API.DTO.UsersDTO.UserEasyAspects;
import PathCarrer.API.Model.Path.Path;
import PathCarrer.API.Model.User.MyPathsAdd;
import PathCarrer.API.Model.User.User;
import PathCarrer.API.Repository.PathRepository;
import PathCarrer.API.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserProfileTest {
    @InjectMocks
    private UserProfile userProfile;
    @Mock
    private PathRepository pathRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;


    @Test
    public void shouldLobyInfo (){
        User user = mock(User.class);
        when(userRepository.findByuserName(user.getUserName())).thenReturn(user);

        var result = userProfile.Loby(user.getUserName());

        assertNotNull(result);
    }

    @Test
    public void AddPathOnUserLoby (){
        AddPath MockAddPath = mock(AddPath.class);
        User MockUser = mock(User.class);
        Path MockPath = mock(Path.class);

        when(userRepository.findByuserName(MockUser.getUserName())).thenReturn(MockUser);
        when(pathRepository.findPath(MockPath.getId())).thenReturn(MockPath);

        userProfile.AddPath(MockAddPath);

        verify(MockPath).UpdatePathCount(true);
        verify(MockUser).AddMyPaths(MockPath);

    }

    @Test
    public void RemovePathOnUserLoby (){
        AddPath MockAddPath = mock(AddPath.class);
        User MockUser = mock(User.class);
        Path MockPath = mock(Path.class);
        HashMap<String, MyPathsAdd> pathsMock = mock(HashMap.class);

        when(userRepository.findByuserName(MockUser.getUserName())).thenReturn(MockUser);
        when(pathRepository.findPath(MockPath.getId())).thenReturn(MockPath);
        when(MockUser.getMyPaths()).thenReturn(pathsMock);

        userProfile.RemovePath(MockAddPath);

        verify(MockPath).UpdatePathCount(false);
        verify(pathsMock).remove(MockPath.getId());
    }

    @Test
    public void shouldPathInfo (){
        Path MockPath = mock(Path.class);
        User UserMock = mock(User.class);


        when(pathRepository.findPath(MockPath.getId())).thenReturn(MockPath);
        when(MockPath.getIdAuthor()).thenReturn("Sivirino");
        when(userRepository.findByWorldID(MockPath.getIdAuthor())).thenReturn(UserMock);

        var result = userProfile.GetPath(MockPath.getId());

        assertNotNull(result);
        assertTrue(result instanceof PathGetDTO);
    }

    @Test
    public void MakeNewName (){
        UpdateProfileName MockUpdateProfileName = mock(UpdateProfileName.class);
        User UserMock = mock(User.class);

        when(MockUpdateProfileName.userName()).thenReturn("usuarioAntigo");
        when(MockUpdateProfileName.newUsername()).thenReturn("usuarioNovo");

        when(userRepository.findByuserName(MockUpdateProfileName.userName())).thenReturn(UserMock);
        when(userRepository.findByuserName(MockUpdateProfileName.newUsername())).thenReturn(null);

        userProfile.NewName(MockUpdateProfileName);

        verify(userRepository).delete(UserMock);
        verify(userRepository).save(UserMock);
    }

    @Test
    public void UpdadePicture (){
        UserEasyAspects UserMockA = mock(UserEasyAspects.class);
        User UserMockB = mock(User.class);

        when(userRepository.findByuserName(UserMockA.userName())).thenReturn(UserMockB);
        userProfile.UpdatePictureProfile(UserMockA);

        verify(userRepository).save(UserMockB);

    }

    @Test
    public void UpdadeDesc (){
        UserEasyAspects UserMockA = mock(UserEasyAspects.class);
        User UserMockB = mock(User.class);

        when(userRepository.findByuserName(UserMockA.userName())).thenReturn(UserMockB);
        when(UserMockA.desc()).thenReturn("Descrição para testar input de Desc.");

        userProfile.UpdateDesc(UserMockA);

        verify(UserMockB).setDesc(UserMockA.desc());
    }

    @Test
    public void NewPassword (){
        Password passwordMock = mock(Password.class);
        User UserMock = mock(User.class);

        when(userRepository.findByuserName(passwordMock.userName())).thenReturn(UserMock);
        when(passwordEncoder.matches(passwordMock.curretPassword(),UserMock.getPassword())).thenReturn(true);

        userProfile.NewPassword(passwordMock);

        verify(UserMock).setPassword(passwordEncoder.encode(passwordMock.newPassWord()));
        verify(userRepository).save(UserMock);
    }

    @Test
    public void AddSeeClass (){

        User UserMock = mock(User.class);
        AddSeeClassDTO SeeClassMock = mock(AddSeeClassDTO.class);
        MyPathsAdd myPathsAddMock = mock(MyPathsAdd.class);

        when(SeeClassMock.UserName()).thenReturn("joao123");
        when(SeeClassMock.PathID()).thenReturn("testagem");
        when(SeeClassMock.IDclass()).thenReturn("classId123");
        when(SeeClassMock.indexModule()).thenReturn(0);

        HashMap<String, MyPathsAdd> myPaths = new HashMap<>();
        myPaths.put("testagem", myPathsAddMock);
        when(UserMock.getMyPaths()).thenReturn(myPaths);

        when(userRepository.findByuserName("joao123")).thenReturn(UserMock);

        userProfile.AddSeeClass(SeeClassMock);

        verify(myPathsAddMock).AddSeeClass(true, "classId123", 0);
        verify(userRepository).save(UserMock);
    }

    @Test
    public void RemoveSeeClass (){

        User UserMock = mock(User.class);
        AddSeeClassDTO SeeClassMock = mock(AddSeeClassDTO.class);
        MyPathsAdd myPathsAddMock = mock(MyPathsAdd.class);

        when(SeeClassMock.UserName()).thenReturn("joao123");
        when(SeeClassMock.PathID()).thenReturn("testagem");
        when(SeeClassMock.IDclass()).thenReturn("classId123");
        when(SeeClassMock.indexModule()).thenReturn(0);

        HashMap<String, MyPathsAdd> myPaths = new HashMap<>();
        myPaths.put("testagem", myPathsAddMock);
        when(UserMock.getMyPaths()).thenReturn(myPaths);

        when(userRepository.findByuserName("joao123")).thenReturn(UserMock);

        userProfile.RemoveSeeClass(SeeClassMock);

        verify(myPathsAddMock).AddSeeClass(false, "classId123", 0);
        verify(userRepository).save(UserMock);
    }


    @Test
    public void AddNote (){

        User UserMock = mock(User.class);
        NoteDTO NoteMock = mock(NoteDTO.class);


        when(userRepository.findByuserName("Sivirino123")).thenReturn(UserMock);
        when(NoteMock.UserName()).thenReturn("Sivirino123");
        when(NoteMock.message()).thenReturn("Testes Java. JUnit + Mocks");

        userProfile.AddNote(NoteMock);

        verify(userRepository).save(UserMock);
    }

    @Test
    public void RemoveNote (){

        User UserMock = mock(User.class);
        NoteDTO NoteMock = mock(NoteDTO.class);


        when(userRepository.findByuserName("Sivirino123")).thenReturn(UserMock);
        when(NoteMock.UserName()).thenReturn("Sivirino123");
        when(NoteMock.key()).thenReturn("400wio2w");

        userProfile.RemoveNote(NoteMock);

        verify(userRepository).save(UserMock);
    }







}