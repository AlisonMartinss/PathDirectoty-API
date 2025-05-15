package PathCarrer.API.Service.Explorer;

import PathCarrer.API.Model.ExplorerModels.Explorer;
import PathCarrer.API.Model.Path.Path;
import PathCarrer.API.Model.User.User;
import PathCarrer.API.Repository.PathRepository;
import PathCarrer.API.Service.Explorer.ExplorerService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExplorerServeceTest {

    @InjectMocks
    private ExplorerService explorerService;

    @Mock
    private PathRepository pathRepository;

    @Mock
    private User userMock;

    @Test
    public void testDefaultExplorer() {
        List<Explorer> mockList = List.of(mock(Explorer.class));
        when(pathRepository.defautExplorer()).thenReturn(mockList);

        List<Explorer> result = explorerService.DefaultExplorer();

        assertEquals(mockList, result);
        verify(pathRepository).defautExplorer();
    }

    @Test
    public void testCategoryExplorer_Todos() {
        List<Explorer> mockList = List.of(mock(Explorer.class));
        when(pathRepository.defautExplorer()).thenReturn(mockList);

        List<Explorer> result = explorerService.CategoryExplorer("Todos");

        assertEquals(mockList, result);
        verify(pathRepository).defautExplorer();
    }

    @Test
    public void testCategoryExplorer_Especifica() {
        String category = "Tecnologia";
        List<Explorer> mockList = List.of(mock(Explorer.class));
        when(pathRepository.CategoryExplorer(category)).thenReturn(mockList);

        List<Explorer> result = explorerService.CategoryExplorer(category);

        assertEquals(mockList, result);
        verify(pathRepository).CategoryExplorer(category);
    }

    @Test
    public void testMyPathsAuthor() {
        String userID = "id-usuario-autenticado";
        when(userMock.getWorldID()).thenReturn(userID);

        List<Path> mockPaths = List.of(mock(Path.class));
        when(pathRepository.findByAuthor(userID)).thenReturn(mockPaths);

        // Mock de segurança
        try (MockedStatic<SecurityContextHolder> mockSecurityContextHolder = Mockito.mockStatic(SecurityContextHolder.class)) {
            SecurityContext securityContext = mock(SecurityContext.class);
            Authentication authentication = mock(Authentication.class);

            mockSecurityContextHolder.when(SecurityContextHolder::getContext).thenReturn(securityContext);
            when(securityContext.getAuthentication()).thenReturn(authentication);
            when(authentication.getPrincipal()).thenReturn(userMock);

            List<Path> result = explorerService.MyPathsAuthor();

            assertEquals(mockPaths, result);
            verify(pathRepository).findByAuthor(userID);
        }
    }
}
