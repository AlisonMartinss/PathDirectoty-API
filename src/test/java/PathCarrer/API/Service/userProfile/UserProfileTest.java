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
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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

        var user = mock(User.class);
        when(user.getWorldID()).thenReturn("id-usuario-autenticado");

        var Path = mock(Path.class);

        when(userRepository.findByuserName(user.getUserName())).thenReturn(user);

        try (MockedStatic<SecurityContextHolder> mockSecurityContextHolder = Mockito.mockStatic(SecurityContextHolder.class)) {
            SecurityContext securityContext = mock(SecurityContext.class);
            Authentication authentication = mock(Authentication.class);

            mockSecurityContextHolder.when(SecurityContextHolder::getContext).thenReturn(securityContext);
            when(securityContext.getAuthentication()).thenReturn(authentication);
            when(authentication.getPrincipal()).thenReturn(user);

            var result = userProfile.Loby(user.getUserName());
            assertNotNull(result);
        }
    }

    @Test
    public void AddPathOnUserLoby (){
        AddPath MockAddPath = mock(AddPath.class);

        User MockUser = mock(User.class);
        when(MockUser.getWorldID()).thenReturn("id-usuario-autenticado");
        Path MockPath = mock(Path.class);

        when(userRepository.findByuserName(MockUser.getUserName())).thenReturn(MockUser);
        when(pathRepository.findPathByID(MockPath.getId())).thenReturn(MockPath);

        try (MockedStatic<SecurityContextHolder> mockSecurityContextHolder = Mockito.mockStatic(SecurityContextHolder.class)) {
            SecurityContext securityContext = mock(SecurityContext.class);
            Authentication authentication = mock(Authentication.class);

            mockSecurityContextHolder.when(SecurityContextHolder::getContext).thenReturn(securityContext);
            when(securityContext.getAuthentication()).thenReturn(authentication);
            when(authentication.getPrincipal()).thenReturn(MockUser);

            userProfile.AddPath(MockAddPath);

            verify(MockPath).UpdatePathCount(true);
            verify(MockUser).AddMyPaths(MockPath);
        }
    }

    @Test
    public void RemovePathOnUserLoby (){
        AddPath MockAddPath = mock(AddPath.class);

        User MockUser = mock(User.class);
        when(MockUser.getWorldID()).thenReturn("id-usuario-autenticado");

        Path MockPath = mock(Path.class);

        HashMap<String, MyPathsAdd> pathsMock = mock(HashMap.class);

        when(userRepository.findByuserName(MockUser.getUserName())).thenReturn(MockUser);
        when(pathRepository.findPathByID(MockPath.getId())).thenReturn(MockPath);
        when(MockUser.getMyPaths()).thenReturn(pathsMock);

        try (MockedStatic<SecurityContextHolder> mockSecurityContextHolder = Mockito.mockStatic(SecurityContextHolder.class)) {
            SecurityContext securityContext = mock(SecurityContext.class);
            Authentication authentication = mock(Authentication.class);

            mockSecurityContextHolder.when(SecurityContextHolder::getContext).thenReturn(securityContext);
            when(securityContext.getAuthentication()).thenReturn(authentication);
            when(authentication.getPrincipal()).thenReturn(MockUser);

            userProfile.RemovePath(MockAddPath);

            verify(MockPath).UpdatePathCount(false);
            verify(pathsMock).remove(MockPath.getId());
        }
    }

    @Test
    public void shouldPathInfo (){
        Path MockPath = mock(Path.class);
        User UserMock = mock(User.class);


        when(pathRepository.findPathByID(MockPath.getId())).thenReturn(MockPath);
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
        when(UserMock.getWorldID()).thenReturn("id-usuario-autenticado");

        when(MockUpdateProfileName.userName()).thenReturn("usuarioAntigo");
        when(MockUpdateProfileName.newUsername()).thenReturn("usuarioNovo");

        try (MockedStatic<SecurityContextHolder> mockSecurityContextHolder = Mockito.mockStatic(SecurityContextHolder.class)) {
            SecurityContext securityContext = mock(SecurityContext.class);
            Authentication authentication = mock(Authentication.class);

            mockSecurityContextHolder.when(SecurityContextHolder::getContext).thenReturn(securityContext);
            when(securityContext.getAuthentication()).thenReturn(authentication);
            when(authentication.getPrincipal()).thenReturn(UserMock);

            when(userRepository.findByuserName(MockUpdateProfileName.userName())).thenReturn(UserMock);
            when(userRepository.findByuserName(MockUpdateProfileName.newUsername())).thenReturn(null);

            userProfile.NewName(MockUpdateProfileName);

            verify(userRepository).delete(UserMock);
            verify(userRepository).save(UserMock);
        }
    }

    @Test
    public void UpdadePicture (){
        UserEasyAspects UserEasyAspects = mock(UserEasyAspects.class);


        User UserMock = mock(User.class);
        when(UserMock.getWorldID()).thenReturn("id-usuario-autenticado");

        when(userRepository.findByuserName(UserEasyAspects.userName())).thenReturn(UserMock);

        try (MockedStatic<SecurityContextHolder> mockSecurityContextHolder = Mockito.mockStatic(SecurityContextHolder.class)) {
            SecurityContext securityContext = mock(SecurityContext.class);
            Authentication authentication = mock(Authentication.class);

            mockSecurityContextHolder.when(SecurityContextHolder::getContext).thenReturn(securityContext);
            when(securityContext.getAuthentication()).thenReturn(authentication);
            when(authentication.getPrincipal()).thenReturn(UserMock);

            userProfile.UpdatePictureProfile(UserEasyAspects);

            verify(userRepository).save(UserMock);
        }

    }

    @Test
    public void UpdadeDesc (){
        UserEasyAspects UserEasyAspects = mock(UserEasyAspects.class);
        when(UserEasyAspects.desc()).thenReturn("desc not null");

        User UserMock = mock(User.class);
        when(UserMock.getWorldID()).thenReturn("id-usuario-autenticado");

        when(userRepository.findByuserName(UserEasyAspects.userName())).thenReturn(UserMock);
        when(UserEasyAspects.desc()).thenReturn("Descrição para testar input de Desc.");

        try (MockedStatic<SecurityContextHolder> mockSecurityContextHolder = Mockito.mockStatic(SecurityContextHolder.class)) {
            SecurityContext securityContext = mock(SecurityContext.class);
            Authentication authentication = mock(Authentication.class);

            mockSecurityContextHolder.when(SecurityContextHolder::getContext).thenReturn(securityContext);
            when(securityContext.getAuthentication()).thenReturn(authentication);
            when(authentication.getPrincipal()).thenReturn(UserMock);

            userProfile.UpdateDesc(UserEasyAspects);

            verify(UserMock).setDesc(UserEasyAspects.desc());
        }
    }

    @Test
    public void NewPassword (){
        Password passwordMock = mock(Password.class);
        User UserMock = mock(User.class);
        when(UserMock.getWorldID()).thenReturn("id-usuario-autenticado");

        when(userRepository.findByuserName(passwordMock.userName())).thenReturn(UserMock);
        when(passwordEncoder.matches(passwordMock.curretPassword(),UserMock.getPassword())).thenReturn(true);

        try (MockedStatic<SecurityContextHolder> mockSecurityContextHolder = Mockito.mockStatic(SecurityContextHolder.class)) {
            SecurityContext securityContext = mock(SecurityContext.class);
            Authentication authentication = mock(Authentication.class);

            mockSecurityContextHolder.when(SecurityContextHolder::getContext).thenReturn(securityContext);
            when(securityContext.getAuthentication()).thenReturn(authentication);
            when(authentication.getPrincipal()).thenReturn(UserMock);

            userProfile.NewPassword(passwordMock);

            verify(UserMock).setPassword(passwordEncoder.encode(passwordMock.newPassWord()));
            verify(userRepository).save(UserMock);
        }
    }

    @Test
    public void AddSeeClass (){

        User UserMock = mock(User.class);
        when(UserMock.getWorldID()).thenReturn("id-usuario-autenticado");

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

        try (MockedStatic<SecurityContextHolder> mockSecurityContextHolder = Mockito.mockStatic(SecurityContextHolder.class)) {
            SecurityContext securityContext = mock(SecurityContext.class);
            Authentication authentication = mock(Authentication.class);

            mockSecurityContextHolder.when(SecurityContextHolder::getContext).thenReturn(securityContext);
            when(securityContext.getAuthentication()).thenReturn(authentication);
            when(authentication.getPrincipal()).thenReturn(UserMock);

            userProfile.AddSeeClass(SeeClassMock);

            verify(myPathsAddMock).AddSeeClass(true, "classId123", 0);
            verify(userRepository).save(UserMock);
        }
    }

    @Test
    public void RemoveSeeClass (){

        User UserMock = mock(User.class);
        when(UserMock.getWorldID()).thenReturn("id-usuario-autenticado");

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
        try (MockedStatic<SecurityContextHolder> mockSecurityContextHolder = Mockito.mockStatic(SecurityContextHolder.class)) {
            SecurityContext securityContext = mock(SecurityContext.class);
            Authentication authentication = mock(Authentication.class);

            mockSecurityContextHolder.when(SecurityContextHolder::getContext).thenReturn(securityContext);
            when(securityContext.getAuthentication()).thenReturn(authentication);
            when(authentication.getPrincipal()).thenReturn(UserMock);

            userProfile.RemoveSeeClass(SeeClassMock);

            verify(myPathsAddMock).AddSeeClass(false, "classId123", 0);
            verify(userRepository).save(UserMock);
        }
    }


    @Test
    public void AddNote (){

        User UserMock = mock(User.class);
        when(UserMock.getWorldID()).thenReturn("id-usuario-autenticado");

        NoteDTO NoteMock = mock(NoteDTO.class);


        when(userRepository.findByuserName("Sivirino123")).thenReturn(UserMock);
        when(NoteMock.UserName()).thenReturn("Sivirino123");
        when(NoteMock.message()).thenReturn("Testes Java. JUnit + Mocks");

        try (MockedStatic<SecurityContextHolder> mockSecurityContextHolder = Mockito.mockStatic(SecurityContextHolder.class)) {
            SecurityContext securityContext = mock(SecurityContext.class);
            Authentication authentication = mock(Authentication.class);

            mockSecurityContextHolder.when(SecurityContextHolder::getContext).thenReturn(securityContext);
            when(securityContext.getAuthentication()).thenReturn(authentication);
            when(authentication.getPrincipal()).thenReturn(UserMock);

            userProfile.AddNote(NoteMock);

            verify(userRepository).save(UserMock);
        }
    }

    @Test
    public void RemoveNote (){

        User UserMock = mock(User.class);
        when(UserMock.getWorldID()).thenReturn("id-usuario-autenticado");

        NoteDTO NoteMock = mock(NoteDTO.class);


        when(userRepository.findByuserName("Sivirino123")).thenReturn(UserMock);
        when(NoteMock.UserName()).thenReturn("Sivirino123");
        when(NoteMock.key()).thenReturn("400wio2w");

        try (MockedStatic<SecurityContextHolder> mockSecurityContextHolder = Mockito.mockStatic(SecurityContextHolder.class)) {
            SecurityContext securityContext = mock(SecurityContext.class);
            Authentication authentication = mock(Authentication.class);

            mockSecurityContextHolder.when(SecurityContextHolder::getContext).thenReturn(securityContext);
            when(securityContext.getAuthentication()).thenReturn(authentication);
            when(authentication.getPrincipal()).thenReturn(UserMock);

            userProfile.RemoveNote(NoteMock);

            verify(userRepository).save(UserMock);
        }
    }

}