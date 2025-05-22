package PathCarrer.API.Service.interaction;

import PathCarrer.API.DTO.InteractionsDTO.InteractionDTO;
import PathCarrer.API.Model.Path.Comments.Comment;
import PathCarrer.API.Model.Path.Path;
import PathCarrer.API.Model.Path.modulo;
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

        when(UserMock.getWorldID()).thenReturn("id-usuario-autenticado");

        when(interactionMock.PathID()).thenReturn("400w8ioww");
        when(interactionMock.indexModule()).thenReturn(0);
        when(interactionMock.Gen()).thenReturn(1);
        when(interactionMock.fatherID()).thenReturn("400w8ioww");
        when(interactionMock.comment()).thenReturn("Teste de comentario");

        when(pathRepository.findPathByID(interactionMock.PathID())).thenReturn(PathMock);
        when(PathMock.getModulos()).thenReturn(ModuloListMock);

        try (MockedStatic<SecurityContextHolder> mockSecurityContextHolder = Mockito.mockStatic(SecurityContextHolder.class)) {
            SecurityContext securityContext = mock(SecurityContext.class);
            Authentication authentication = mock(Authentication.class);

            mockSecurityContextHolder.when(SecurityContextHolder::getContext).thenReturn(securityContext);
            when(securityContext.getAuthentication()).thenReturn(authentication);
            when(authentication.getPrincipal()).thenReturn(UserMock);

            interaction.PostComment(interactionMock);

            verify(pathRepository).save(PathMock);
        }
    }

    @Test
    public void DeleteCommet (){
        InteractionDTO interactionMock = mock(InteractionDTO.class);
        Path PathMock = mock(Path.class);
        modulo moduleMock = mock(modulo.class);
        Comment commentMock = mock(Comment.class);
        User user = mock(User.class);

        List<modulo> ModuloListMock = List.of(moduleMock);

        when(interactionMock.PathID()).thenReturn("400w8ioww");
        when(interactionMock.indexModule()).thenReturn(0);
        when(interactionMock.Gen()).thenReturn(1);
        when(interactionMock.fatherID()).thenReturn("400w8ioww");
        when(moduleMock.ElementCommentInfo(interactionMock.Gen(),interactionMock.fatherID(),interactionMock.commentID())).thenReturn(commentMock);
        when(commentMock.getUserWordID()).thenReturn("id-usuario-autenticado");
        when(user.getWorldID()).thenReturn("id-usuario-autenticado");

        try (MockedStatic<SecurityContextHolder> mockSecurityContextHolder = Mockito.mockStatic(SecurityContextHolder.class)) {
                SecurityContext securityContext = mock(SecurityContext.class);
                Authentication authentication = mock(Authentication.class);

                mockSecurityContextHolder.when(SecurityContextHolder::getContext).thenReturn(securityContext);
                when(securityContext.getAuthentication()).thenReturn(authentication);
                when(authentication.getPrincipal()).thenReturn(user);

            when(pathRepository.findPathByID(interactionMock.PathID())).thenReturn(PathMock);
            when(PathMock.getModulos()).thenReturn(ModuloListMock);


            interaction.DeleteComment(interactionMock);

            verify(pathRepository).save(PathMock);
        }
    }

}