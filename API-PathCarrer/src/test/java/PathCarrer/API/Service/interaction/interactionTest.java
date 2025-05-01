package PathCarrer.API.Service.interaction;

import PathCarrer.API.DTO.InteractionsDTO.InteractionDTO;
import PathCarrer.API.Model.Path.Path;
import PathCarrer.API.Model.Path.modulo;
import PathCarrer.API.Model.User.User;
import PathCarrer.API.Repository.PathRepository;
import PathCarrer.API.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class interactionTest {
    @InjectMocks
    private interaction interaction;
    @Mock
    private PathRepository pathRepository;
    @Mock
    private UserRepository userRepository;

    @Test
    public void PostComment (){
         InteractionDTO interactionMock = mock(InteractionDTO.class);
         Path PathMock = mock(Path.class);
         User UserMock = mock(User.class);
         modulo moduleMock = mock(modulo.class);
         List<modulo> ModuloListMock = List.of(moduleMock);

        when(interactionMock.PathID()).thenReturn("400w8ioww");
        when(interactionMock.userName()).thenReturn("Sivirino");
        when(interactionMock.indexModule()).thenReturn(0);
        when(interactionMock.Gen()).thenReturn(1);
        when(interactionMock.fatherID()).thenReturn("400w8ioww");
        when(interactionMock.comment()).thenReturn("Teste de comentario");
        when(UserMock.getWorldID()).thenReturn("400w8ioww");

        when(userRepository.findByuserName(interactionMock.userName())).thenReturn(UserMock);
        when(pathRepository.findPath(interactionMock.PathID())).thenReturn(PathMock);
        when(PathMock.getModulos()).thenReturn(ModuloListMock);


        interaction.PostComment(interactionMock);

        verify(pathRepository).save(PathMock);
    }

    @Test
    public void DeleteCommet (){
        InteractionDTO interactionMock = mock(InteractionDTO.class);
        Path PathMock = mock(Path.class);
        modulo moduleMock = mock(modulo.class);
        List<modulo> ModuloListMock = List.of(moduleMock);

        when(interactionMock.PathID()).thenReturn("400w8ioww");
        when(interactionMock.indexModule()).thenReturn(0);
        when(interactionMock.Gen()).thenReturn(1);
        when(interactionMock.fatherID()).thenReturn("400w8ioww");

        when(pathRepository.findPath(interactionMock.PathID())).thenReturn(PathMock);
        when(PathMock.getModulos()).thenReturn(ModuloListMock);


        interaction.DeleteComment(interactionMock);

        verify(pathRepository).save(PathMock);
    }

}