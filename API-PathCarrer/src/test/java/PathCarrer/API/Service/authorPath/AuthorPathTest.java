package PathCarrer.API.Service.authorPath;

import PathCarrer.API.DTO.CreatePathStep.onePath;
import PathCarrer.API.DTO.CreatePathStep.threePath;
import PathCarrer.API.DTO.CreatePathStep.twoPath;
import PathCarrer.API.DTO.PathDTO;
import PathCarrer.API.DTO.Update.ClassUpdate;
import PathCarrer.API.DTO.Update.ModuloUpdateDTO;
import PathCarrer.API.DTO.Update.PathUpdate;
import PathCarrer.API.Model.Path.Aulas;
import PathCarrer.API.Model.Path.Path;
import PathCarrer.API.Model.Path.modulo;
import PathCarrer.API.Model.User.User;
import PathCarrer.API.Repository.PathRepository;
import PathCarrer.API.Repository.UserRepository;
import org.junit.jupiter.api.Assertions;
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
        var user = mock(User.class);
        when(user.getWorldID()).thenReturn("id-usuario-autenticado");

        try (MockedStatic<SecurityContextHolder> mockSecurityContextHolder = Mockito.mockStatic(SecurityContextHolder.class)) {
            SecurityContext securityContext = mock(SecurityContext.class);
            Authentication authentication = mock(Authentication.class);

            mockSecurityContextHolder.when(SecurityContextHolder::getContext).thenReturn(securityContext);
            when(securityContext.getAuthentication()).thenReturn(authentication);
            when(authentication.getPrincipal()).thenReturn(user);

            Path path = mock(Path.class);
            when(path.getIdAuthor()).thenReturn("id-usuario-autenticado");

            var pathUpdate = new PathUpdate("path-id", new onePath(
                    "Título", "Categoria", List.of("01", "02", "03", "04", "05"),
                    List.of("#tag"), "Descrição", "http://link"
            ));

            when(pathRepository.findPath(pathUpdate.PathID())).thenReturn(path);
            when(userRepository.findByWorldID("id-usuario-autenticado")).thenReturn(user);

            authorPath.pathUpdate(pathUpdate);

            Mockito.verify(pathRepository).save(path);
        }
    }


    @Test
    public void shouldDeletePathWhenItExists() {
        var user = mock(User.class);
        when(user.getWorldID()).thenReturn("id-usuario-autenticado");

        try (MockedStatic<SecurityContextHolder> mockSecurityContextHolder = Mockito.mockStatic(SecurityContextHolder.class)) {
            SecurityContext securityContext = mock(SecurityContext.class);
            Authentication authentication = mock(Authentication.class);

            mockSecurityContextHolder.when(SecurityContextHolder::getContext).thenReturn(securityContext);
            when(securityContext.getAuthentication()).thenReturn(authentication);
            when(authentication.getPrincipal()).thenReturn(user);

            Path path = mock(Path.class);
            when(path.getIdAuthor()).thenReturn("id-usuario-autenticado");

            PathUpdate updateDTO = new PathUpdate("path-id", mock(onePath.class));
            when(pathRepository.findPath(updateDTO.PathID())).thenReturn(path);
            when(userRepository.findByWorldID("id-usuario-autenticado")).thenReturn(user);

            authorPath.pathDelete(updateDTO);

            Mockito.verify(pathRepository).deleteById(updateDTO.PathID());
        }
    }


    @Test
    public void shouldUpdateModule() {
        var user = mock(User.class);
        when(user.getWorldID()).thenReturn("user-id");

        try (MockedStatic<SecurityContextHolder> mockSecurityContextHolder = Mockito.mockStatic(SecurityContextHolder.class)) {
            SecurityContext securityContext = mock(SecurityContext.class);
            Authentication authentication = mock(Authentication.class);
            mockSecurityContextHolder.when(SecurityContextHolder::getContext).thenReturn(securityContext);
            when(securityContext.getAuthentication()).thenReturn(authentication);
            when(authentication.getPrincipal()).thenReturn(user);

            Path path = mock(Path.class);
            when(path.getIdAuthor()).thenReturn("user-id");

            ModuloUpdateDTO dto = mock(ModuloUpdateDTO.class);
            when(dto.PathID()).thenReturn("path-id");
            when(dto.indexMoudulo()).thenReturn(0);
            when(dto.title()).thenReturn("novo titulo");
            when(dto.desc()).thenReturn("nova descrição");

            when(pathRepository.findPath("path-id")).thenReturn(path);
            when(userRepository.findByWorldID("user-id")).thenReturn(user);

            var moduloMock = mock(modulo.class);
            var aulasMock = mock(Aulas.class);

            List<modulo> modulos = List.of(moduloMock);
            when(path.getModulos()).thenReturn(modulos);
            when(moduloMock.getModulocontent()).thenReturn(List.of(aulasMock));

            authorPath.UpdateModule(dto);

            Mockito.verify(pathRepository).save(path);
            Mockito.verify(moduloMock).setName("novo titulo");
            Mockito.verify(moduloMock).setDescription("nova descrição");
            Mockito.verify(aulasMock).ClassUpdate("novo titulo", "nova descrição", "link Mock");
        }
    }


    @Test
    public void shouldDeleteClassUnic() {
        var user = mock(User.class);
        when(user.getWorldID()).thenReturn("user-id");

        try (MockedStatic<SecurityContextHolder> mockSecurityContextHolder = Mockito.mockStatic(SecurityContextHolder.class)) {
            SecurityContext securityContext = mock(SecurityContext.class);
            Authentication authentication = mock(Authentication.class);

            mockSecurityContextHolder.when(SecurityContextHolder::getContext).thenReturn(securityContext);
            when(securityContext.getAuthentication()).thenReturn(authentication);
            when(authentication.getPrincipal()).thenReturn(user);

            Path path = mock(Path.class);
            when(path.getIdAuthor()).thenReturn("user-id");

            threePath threePath = mock(threePath.class);


            ClassUpdate dto = new ClassUpdate("path-id", 0, "nome do modulo",0,threePath);

            when(userRepository.findByWorldID("user-id")).thenReturn(user);
            when(pathRepository.findPath("path-id")).thenReturn(path);

            modulo moduloMock = mock(modulo.class);
            when(path.getModulos()).thenReturn(List.of(moduloMock));

            Aulas aulaMock = mock(Aulas.class);
            when(moduloMock.getModulocontent()).thenReturn(new ArrayList<>(List.of(aulaMock)));

            authorPath.DeleteClassUnic(dto);


            Mockito.verify(moduloMock).getModulocontent();
            Mockito.verify(pathRepository).save(path);
        }
    }


    @Test
    public void shouldProfileInfo() {
        var user = mock(User.class);
        when(user.getWorldID()).thenReturn("user-id");

        try (MockedStatic<SecurityContextHolder> mockSecurityContextHolder = Mockito.mockStatic(SecurityContextHolder.class)) {
            SecurityContext securityContext = mock(SecurityContext.class);
            Authentication authentication = mock(Authentication.class);

            mockSecurityContextHolder.when(SecurityContextHolder::getContext).thenReturn(securityContext);

            when(userRepository.findByWorldID("user-id")).thenReturn(user);

            var dto = authorPath.GetProfileInfo(user.getWorldID());

            Assertions.assertNotNull(dto);
            Mockito.verify(userRepository).findByWorldID("user-id");
        }
    }












}

