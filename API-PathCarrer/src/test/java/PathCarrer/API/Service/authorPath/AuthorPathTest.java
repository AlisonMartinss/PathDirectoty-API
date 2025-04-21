package PathCarrer.API.Service.authorPath;

import PathCarrer.API.DTO.CreatePathStep.onePath;
import PathCarrer.API.DTO.CreatePathStep.threePath;
import PathCarrer.API.DTO.CreatePathStep.twoPath;
import PathCarrer.API.DTO.PathDTO;
import PathCarrer.API.DTO.Update.ClassUpdate;
import PathCarrer.API.DTO.Update.ModuloUpdateDTO;
import PathCarrer.API.DTO.Update.PathUpdate;
import PathCarrer.API.Model.Path.Path;
import PathCarrer.API.Model.Path.modulo;
import PathCarrer.API.Model.User.User;
import PathCarrer.API.Repository.PathRepository;
import PathCarrer.API.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AuthorPathTest {
    @InjectMocks
    private AuthorPath authorPath;

    @Mock
    private UserRepository userRepository;
    @Mock
    private PathRepository pathRepository;

    @Test
    public void shouldCreatePathWhenItExists() {
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


        User userMock = mock(User.class);
        when(userMock.getWorldID()).thenReturn(String.valueOf(UUID.fromString("123e4567-e89b-12d3-a456-426614174000")));

        when(userRepository.findByuserName("Sivirino")).thenReturn(userMock);


        Path pathMock = new Path();
        pathMock.CreateNewPath(pathDTOMock, userMock.getWorldID().toString());

        when(pathRepository.save(Mockito.any(Path.class))).thenReturn(pathMock);

        Path result = authorPath.PathCreate(pathDTOMock);

        assertNotNull(result);
        Mockito.verify(userRepository).findByuserName("Sivirino");
        Mockito.verify(pathRepository).save(Mockito.any(Path.class));
    }

    @Test
    public void shouldUpdatePathWhenItExists() {
        Path CurrentPath = mock(Path.class);
        var PathUpdateMock = new PathUpdate("4002",   new onePath(
                "Testes",
                "Teste",
                List.of("01", "02", "03", "04", "05"),
                List.of("#test"),
                "testando 'CreatePath'",
                "http/"
        ));

        when(pathRepository.findPath(PathUpdateMock.PathID())).thenReturn(CurrentPath);
        authorPath.pathUpdate(PathUpdateMock);

        Mockito.verify(pathRepository).findPath(PathUpdateMock.PathID());
        Mockito.verify(pathRepository).save(Mockito.any(Path.class));
    }

    @Test
    public void shouldDeletePathWhenItExists() {
        Path CurrentPath = mock(Path.class);
        var PathUpdateMock = new PathUpdate("4002",   new onePath(
                "Testes",
                "Teste",
                List.of("01", "02", "03", "04", "05"),
                List.of("#test"),
                "testando 'CreatePath'",
                "http/"
        ));

        when(pathRepository.findPath(PathUpdateMock.PathID())).thenReturn(CurrentPath);
        authorPath.pathDelete(PathUpdateMock);

        Mockito.verify(pathRepository).deleteById(PathUpdateMock.PathID());
    }

    @Test
    public void shouldUpdateModule() {
        Path CurrentPath = mock(Path.class);
        ModuloUpdateDTO ModuleDTOMock = mock(ModuloUpdateDTO.class);

        when(pathRepository.findPath(ModuleDTOMock.PathID())).thenReturn(CurrentPath);
        authorPath.UpadateNewModule(ModuleDTOMock);

        Mockito.verify(pathRepository).save(CurrentPath);
        Mockito.verify(CurrentPath).AddNewModulo(ModuleDTOMock);
    }

    @Test
    public void shouldDeleteModule() {
        Path CurrentPath = mock(Path.class);
        ModuloUpdateDTO ModuleDTOMock = mock(ModuloUpdateDTO.class);

        when(pathRepository.findPath(ModuleDTOMock.PathID())).thenReturn(CurrentPath);
        authorPath.DeleteModule(ModuleDTOMock);

        Mockito.verify(pathRepository).save(CurrentPath);
        Mockito.verify(CurrentPath).deleteModule(ModuleDTOMock.indexMoudulo());
    }

    // UpdateClassUnic

    @Test
    public void shouldDeleteClassUnic() {

        Path CurrentPath = mock(Path.class);
        ClassUpdate classUpdateMock = mock(ClassUpdate.class);

        modulo modulo = new modulo();
        modulo.moduloCreate("ModuleName", "ModuleDescription", List.of(new threePath("Title", "Link", "Desc")));

        List<modulo> modulosMock = new ArrayList<>();
        modulosMock.add(modulo);

        when(CurrentPath.getModulos()).thenReturn(modulosMock);
        when(classUpdateMock.indexModule()).thenReturn(0);

        when(pathRepository.findPath(classUpdateMock.PathID())).thenReturn(CurrentPath);

        authorPath.DeleteClassUnic(classUpdateMock);

        Mockito.verify(pathRepository).save(CurrentPath);
    }

    @Test
    public void shouldProfileInfo() {
        User userMock = mock(User.class);

        when(userRepository.findByWorldID(userMock.getWorldID())).thenReturn(userMock);
        var result  = authorPath.GetProfileInfo(userMock.getWorldID());

        assertNotNull(result);
    }










}

