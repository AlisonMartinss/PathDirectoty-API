package PathCarrer.API.Service.authorPath;

import PathCarrer.API.DTO.ParallelDataDTO.ParallelDataDTO;
import PathCarrer.API.DTO.PathDTO;
import PathCarrer.API.DTO.Update.ClassUpdate;
import PathCarrer.API.DTO.Update.ModuloUpdateDTO;
import PathCarrer.API.DTO.Update.PathUpdate;
import PathCarrer.API.ExeptionsClasses.GenericErro;
import PathCarrer.API.ExeptionsClasses.Hackers;
import PathCarrer.API.ExeptionsClasses.NotFound;
import PathCarrer.API.ExeptionsClasses.PathAspectsUnexpected;
import PathCarrer.API.Model.Path.Aulas;
import PathCarrer.API.Model.Path.Path;
import PathCarrer.API.Model.User.User;
import PathCarrer.API.Repository.PathRepository;
import PathCarrer.API.Repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Classe destinada a realizar operações que tangem a relação entre um Path e seu autor.
 *
 * <p>
 * This class is responsible for handling operations related to a Path and its author.
 * </p>
 *
 * <p><strong>Operações disponíveis / Available operations:</strong></p>
 *
 * <ul>
 *   <li><strong>DefaultExplorer:</strong> Criação de um novo Path. <br>
 *       Creates a new Path.</li>
 *   <li><strong>PathUpdate:</strong> Atualiza as informações de um Path. <br>
 *       Updates the Path information.</li>
 *   <li><strong>PathDelete:</strong> Remove um Path existente. <br>
 *       Deletes an existing Path.</li>
 *   <li><strong>UpdateNewModule:</strong> Adiciona um novo módulo ao Path. <br>
 *       Adds a new module to the Path.</li>
 *   <li><strong>UpdateModule:</strong> Atualiza um módulo existente. <br>
 *       Updates an existing module.</li>
 *   <li><strong>DeleteModule:</strong> Remove um módulo do Path. <br>
 *       Deletes a module from the Path.</li>
 *   <li><strong>UpdateNewClass:</strong> Adiciona uma nova aula ao módulo. <br>
 *       Adds a new class to the module.</li>
 *   <li><strong>UpdateClassUnic:</strong> Atualiza os dados de uma aula específica. <br>
 *       Updates a specific class.</li>
 *   <li><strong>GetProfileInfo:</strong> Obtém as informações mais recentes de um determinado usuário. <br>
 *       Retrieves the latest information from a specific user.</li>
 * </ul>
 *
 * <p>
 * Essa classe centraliza as ações de edição e gerenciamento de Paths,
 * promovendo consistência e controle sobre o conteúdo educacional produzido por seus autores.
 * </p>
 *
 * <p>
 * This class centralizes editing and management actions for Paths,
 * ensuring consistency and control over the educational content produced by its authors.
 * </p>
 */

@Service
public class AuthorPath {
    @Autowired
    private PathRepository pathRepositoy;
    @Autowired
    private UserRepository userRepository;

    public String PathCreate(PathDTO pathDTO) {

        var novo = new Path();
        var User = userRepository.findByuserName(pathDTO.authorID());
        if (User == null) {
            throw new NotFound("PathCreate - Usuario nao encontrado");
        }
        try{
           novo.CreateNewPath(pathDTO, User.getWorldID().toString());
           pathRepositoy.save(novo);
           return "Path criado com sucesso";
        } catch (NullPointerException erro) {
            throw new GenericErro("PathCreate - Falha ao criar path");
        }
    }

    public String pathUpdate(PathUpdate pathDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User usuarioLogado = (User) auth.getPrincipal();

        Path path = pathRepositoy.findPathByID(pathDTO.PathID());
        if (path == null) {
            throw new NotFound("PathUpdate - Path nao encontrado");
        }

        User autor = userRepository.findByWorldID(path.getIdAuthor());
        if (autor == null) {
            throw new NotFound("PathUpdate - Author do path nao encontrado");
        }

        if (!usuarioLogado.getWorldID().equals(autor.getWorldID())) {
            throw new Hackers("fingindo ser outro");
        }

        path.UpdatePathStats(pathDTO);
        pathRepositoy.save(path);
        return "Path atualizado com sucesso";
    }

    public String pathDelete(PathUpdate pathDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User usuarioLogado = (User) auth.getPrincipal();

        var path = pathRepositoy.findPathByID(pathDTO.PathID());

        if (path == null) {
            throw new NotFound("PathDelete - Path nao encontrado");
        }

        var autor = userRepository.findByWorldID(path.getIdAuthor());

        if (autor == null) {
            throw new NotFound("PathDelete - Autor nao encontrado");
        }

        if (!usuarioLogado.getWorldID().equals(autor.getWorldID())) {
            throw new Hackers("fingindo ser outro");
        }

        pathRepositoy.deleteById(pathDTO.PathID());
        return "Path deletado com sucesso";
    }

    public String UpadateNewModule(ModuloUpdateDTO ModuleUpdate) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User usuarioLogado = (User) auth.getPrincipal();

        Path Path = pathRepositoy.findPathByID(ModuleUpdate.PathID());

        if (Path == null) {
            throw new NotFound("UpadateNewModule - Path não encontrado");
        }

        User autor = userRepository.findByWorldID(Path.getIdAuthor());

        if (autor == null) {
            throw new NotFound("UpadateNewModule - Autor não encontrado");
        }

        if (!usuarioLogado.getWorldID().equals(autor.getWorldID())) {
            throw new Hackers("fingindo ser outro");
        }

        try {
            Path.AddNewModulo(ModuleUpdate);
            pathRepositoy.save(Path);
            return "Novo modulo adicionado";
        } catch (NullPointerException erro) {
            throw new PathAspectsUnexpected("UpadateNewModule Falha no sucesso do metodo AddNewModulo");
        }
    }

    public String UpdateModule(ModuloUpdateDTO pathUpdate) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User usuarioLogado = (User) auth.getPrincipal();

        Path Path = pathRepositoy.findPathByID(pathUpdate.PathID());

        if (Path == null) {
            throw new PathAspectsUnexpected("UpdateModule - Path não existe");
        }

        User autor = userRepository.findByWorldID(Path.getIdAuthor());

        if (autor == null) {
            throw new NotFound("UpdateModule - Autor nao encontrado");
        }

        if (!usuarioLogado.getWorldID().equals(autor.getWorldID())) {
            throw new Hackers("fingindo ser outro");
        }

        try {
            Path.getModulos().get(pathUpdate.indexMoudulo()).setName(pathUpdate.title());
            Path.getModulos().get(pathUpdate.indexMoudulo()).setDescription(pathUpdate.desc());
            Path.getModulos().get(pathUpdate.indexMoudulo()).getModulocontent().get(0).ClassUpdate(pathUpdate.title(), pathUpdate.desc(), "link Mock");
            pathRepositoy.save(Path);
            return "Modulo atualizado com sucesso.";
        } catch (NullPointerException erro) {
            throw new PathAspectsUnexpected("UpdateModule - Não econtramos esse modulo em meio ao Path");
        }

    }

    public String DeleteModule(ModuloUpdateDTO pathUpdate) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User usuarioLogado = (User) auth.getPrincipal();

        Path pathList = pathRepositoy.findPathByID(pathUpdate.PathID());

        if (pathList == null) {
            throw new PathAspectsUnexpected("DeleteModule - O Path requisitado não existe");
        }

        User autor = userRepository.findByWorldID(pathList.getIdAuthor());

        if (autor == null) {
            throw new NotFound("DeleteModule - Autor não encontrado");
        }

        if (!usuarioLogado.getWorldID().equals(autor.getWorldID())) {
            throw new Hackers("fingindo ser outro");
        }

        try {
            pathList.deleteModule(pathUpdate.indexMoudulo());
            pathRepositoy.save(pathList);
            return "Modulo deletado com sucesso";
        } catch (NullPointerException erro) {
            throw new PathAspectsUnexpected("DeleteModule - O modulo requisitado não existe");
        }
    }

    public String UpadateNewClass(ClassUpdate classUpdate) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User usuarioLogado = (User) auth.getPrincipal();

        Path Path = pathRepositoy.findPathByID(classUpdate.PathID());

        if (Path == null) {
            throw new PathAspectsUnexpected("UpadateNewClass - O Path requisitado não existe");
        }

        User autor = userRepository.findByWorldID(Path.getIdAuthor());

        if (autor == null) {
            throw new NotFound("UpadateNewClass - Autor não encontrado");
        }

        if (!usuarioLogado.getWorldID().equals(autor.getWorldID())) {
            throw new Hackers("fingindo ser outro");
        }

        try {
            Path.getModulos().get(classUpdate.indexModule()).UpdateNewClass(classUpdate.threePath());
            var recentClass = Path.getModulos().get(classUpdate.indexModule()).getModulocontent().get(Path.getModulos().get(classUpdate.indexModule()).getModulocontent().size() - 1);
            Path.UpdatenClass(true, recentClass.getID());
            pathRepositoy.save(Path);
            return "Aula adicionada com sucesso.";
        } catch (IndexOutOfBoundsException erro) {
            throw new PathAspectsUnexpected("UpadateNewClass - O modulo requisitado não existe");
        }
    }


    public String UpdateClassUnic(ClassUpdate classUpdate){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User usuarioLogado = (User) auth.getPrincipal();

        Path pathList = pathRepositoy.findPathByID(classUpdate.PathID());

        if (pathList == null) {
            throw new PathAspectsUnexpected("UpdateClassUnic - O Path requisitado não existe");
        }

        User autor = userRepository.findByWorldID(pathList.getIdAuthor());

        if (autor == null) {
            throw new NotFound("UpdateClassUnic - Autor não encontrado");
        }

        if (!usuarioLogado.getWorldID().equals(autor.getWorldID())) {
            throw new Hackers("fingindo ser outro");
        }

        var newClass = new Aulas();
        newClass.ClassCreate(classUpdate.threePath().title(),classUpdate.threePath().description(),classUpdate.threePath().link());
        try {
            pathList.getModulos().get(classUpdate.indexModule()).getModulocontent().set(classUpdate.indexClass(),newClass);
            pathRepositoy.save(pathList);
            return "Aula atualizada com sucesso";
        }catch (NullPointerException erro) {
            throw new PathAspectsUnexpected("UpdateClassUnic - O modulo ou aula que se pretende atualizar não existe");
        }
    }

     public String DeleteClassUnic(ClassUpdate classUpdate){
         Authentication auth = SecurityContextHolder.getContext().getAuthentication();
         User usuarioLogado = (User) auth.getPrincipal();

         Path pathList = pathRepositoy.findPathByID(classUpdate.PathID());

         if (pathList == null) {
             throw new PathAspectsUnexpected("DeleteClassUnic - Falha ao encontrar modulo em meio ao Path");
         }

         User autor = userRepository.findByWorldID(pathList.getIdAuthor());

         if (autor == null) {
             throw new NotFound("DeleteClassUnic - Autor não encontrado");
         }

         if (!usuarioLogado.getWorldID().equals(autor.getWorldID())) {
             throw new Hackers("fingindo ser outro");
         }

         try {
             var modulo = pathList.getModulos().get(classUpdate.indexModule());
             pathList.UpdatenClass(false,modulo.getModulocontent().get(classUpdate.indexClass()).getID());
             modulo.DeleteClass(classUpdate.indexClass());
             pathRepositoy.save(pathList);
             return "Aula deletada com sucesso";
         }catch (NullPointerException erro){
             throw new PathAspectsUnexpected("DeleteClassUnic - Falha em econtrar modulo ou aula");
         }
     }
    public ParallelDataDTO GetProfileInfo (String worldID){

        var User = userRepository.findByWorldID(worldID);

        if (User == null) {
            throw new NotFound("GetProfileInfo - Autor/Usuario não encontrado");
        }

        return new ParallelDataDTO(User);
    }
}
