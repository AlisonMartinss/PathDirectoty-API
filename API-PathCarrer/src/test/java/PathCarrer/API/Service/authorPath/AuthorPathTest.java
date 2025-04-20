package PathCarrer.API.Service.authorPath;

import PathCarrer.API.DTO.CreatePathStep.onePath;
import PathCarrer.API.DTO.CreatePathStep.threePath;
import PathCarrer.API.DTO.CreatePathStep.twoPath;
import PathCarrer.API.DTO.PathDTO;
import PathCarrer.API.Model.Path.Path;
import PathCarrer.API.Model.User.User;
import PathCarrer.API.Repository.PathRepository;
import PathCarrer.API.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.UUID;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;


@ExtendWith(MockitoExtension.class)
class AuthorPathTest {
    @InjectMocks
    private AuthorPath authorPath;

    @Mock
    private UserRepository userRepository;
    @Mock
    private PathRepository pathRepository;

    @Test
    public void willReturnPath() {
        PathDTO pathDTOMock = new PathDTO(
                "Sivirino",
                new onePath(
                        "Testes",
                        "Teste",
                        List.of("01", "02", "03", "04", "05"),
                        List.of("#test"),
                        "testando 'CreatePath'",
                        "http/"
                ),
                new twoPath(
                        "title 01",
                        "Desc teste",
                        List.of(
                                new threePath("Title 01", "Link 01", "Desc 01"),
                                new threePath("Title 02", "Link 02", "Desc 02")
                        )
                )
        );


        User userMock = Mockito.mock(User.class);
        Mockito.when(userMock.getWorldID()).thenReturn(String.valueOf(UUID.fromString("123e4567-e89b-12d3-a456-426614174000")));

        Mockito.when(userRepository.findByuserName("Sivirino")).thenReturn(userMock);


        Path pathMock = new Path();
        pathMock.CreateNewPath(pathDTOMock, userMock.getWorldID().toString());

        Mockito.when(pathRepository.save(Mockito.any(Path.class))).thenReturn(pathMock);

        Path result = authorPath.PathCreate(pathDTOMock);

        assertNotNull(result);
        Mockito.verify(userRepository).findByuserName("Sivirino");
        Mockito.verify(pathRepository).save(Mockito.any(Path.class));
    }

}

