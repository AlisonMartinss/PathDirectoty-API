package PathCarrer.API.Service.FastInfo;

import PathCarrer.API.Model.ExplorerModels.Explorer;
import PathCarrer.API.Model.MyStandardsResponde.Response;
import PathCarrer.API.Model.Path.Comments.Comment;
import PathCarrer.API.Model.Path.Path;
import PathCarrer.API.Model.Path.modulo;
import PathCarrer.API.Repository.PathRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FastInfoServiceTest {
    @InjectMocks
    private FastInfoService explorerService;

    @Mock
    private PathRepository pathRepository;

    @Test
    public void testPathBasicInfo() {
        String pathID = "path123";
        Explorer mockExplorer = mock(Explorer.class);

        when(pathRepository.BasicInfo(pathID)).thenReturn(mockExplorer);

        Explorer result = explorerService.PathBasicInfo(pathID);

        assertEquals(mockExplorer, result);
        verify(pathRepository).BasicInfo(pathID);
    }


    @Test
    public void testElementCommentInfo() {
        String pathID = "pathABC";
        int indexModule = 0;
        int gen = 1;
        String commentID = "commentXYZ";


        Path pathMock = mock(Path.class);
        modulo moduleMock = mock(modulo.class);
        Response<HashMap<String, Comment>> responseMock = mock(Response.class);

        List<modulo> modulos = List.of(moduleMock);
        when(pathMock.getModulos()).thenReturn(modulos);
        when(pathRepository.findPathByID(pathID)).thenReturn(pathMock);
        when(moduleMock.ElementCommentInfoAnswers(gen + 1, commentID)).thenReturn(responseMock);

        Response<HashMap<String, Comment>> result = explorerService.ElementCommentInfo(pathID, indexModule, gen, commentID);

        assertEquals(responseMock, result);
        verify(pathRepository).findPathByID(pathID);
        verify(pathMock).getModulos();
        verify(moduleMock).ElementCommentInfoAnswers(gen + 1, commentID);
    }

}