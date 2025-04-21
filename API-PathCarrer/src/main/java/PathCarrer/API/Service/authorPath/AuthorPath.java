package PathCarrer.API.Service.authorPath;

import PathCarrer.API.DTO.ParallelDataDTO.ParallelDataDTO;
import PathCarrer.API.DTO.PathDTO;
import PathCarrer.API.DTO.Update.ClassUpdate;
import PathCarrer.API.DTO.Update.ModuloUpdateDTO;
import PathCarrer.API.DTO.Update.PathUpdate;
import PathCarrer.API.ExeptionsClasses.NotFound;
import PathCarrer.API.ExeptionsClasses.PathAspectsUnexpected;
import PathCarrer.API.Model.Path.Aulas;
import PathCarrer.API.Model.Path.Path;
import PathCarrer.API.Repository.PathRepository;
import PathCarrer.API.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Path PathCreate(PathDTO pathDTO){

        var novo = new Path();
        var User = userRepository.findByuserName(pathDTO.authorID());
        if (User != null){
            try {
                novo.CreateNewPath(pathDTO, User.getWorldID().toString());
                pathRepositoy.save(novo);
                return novo;
            }catch (NullPointerException erro){
                throw new NotFound("PathCreate - Erro no Objeto User! " + erro);
            }
        }
        else {
            throw new NotFound("PathCreate - Usuario não encontrado");
        }
    }

     public void pathUpdate (PathUpdate pathDTO){

         var path = pathRepositoy.findPath(pathDTO.PathID());
         if (path != null){
             path.UpdatePathStats(pathDTO);
             pathRepositoy.save(path);

         }
         else {throw new NotFound("pathUpdate - Path não encontrado");}
     }

     public void  pathDelete (PathUpdate pathDTO){
        if (pathRepositoy.findPath(pathDTO.PathID()) != null){
            pathRepositoy.deleteById(pathDTO.PathID());
         }
        else { throw new NotFound("pathDelete - Path não encontrado");}
     }

     public void UpadateNewModule(ModuloUpdateDTO ModuleUpdate){

        Path pathList = pathRepositoy.findPath(ModuleUpdate.PathID());
        if (pathList != null){
            try {
            pathList.AddNewModulo(ModuleUpdate);
            pathRepositoy.save(pathList);
            }catch (NullPointerException erro){
                throw new PathAspectsUnexpected("UpadateNewModule - Falha no sucesso do metodo AddNewModulo");
            }
        }
        else  {throw new NotFound("UpadateNewModule - Path não encontrado");}

    }

    public void UpdateModule(ModuloUpdateDTO pathUpdate){

        Path pathList = pathRepositoy.findPath(pathUpdate.PathID());
        if (pathList != null){
            try {
                pathList.getModulos().get(pathUpdate.indexMoudulo()).setName(pathUpdate.title());
                pathList.getModulos().get(pathUpdate.indexMoudulo()).setDescription(pathUpdate.desc());
                pathRepositoy.save(pathList);
            } catch (NullPointerException erro) {
                throw new PathAspectsUnexpected("UpdateModule - Falha ao encontrar modulo em meio ao Path");
            }
        }
        else {throw new NotFound("UpdateModule - Path não encontrado");}
    }

     public void DeleteModule(ModuloUpdateDTO pathUpdate){
         Path pathList = pathRepositoy.findPath(pathUpdate.PathID());
         if (pathList != null){
             try {
                 pathList.deleteModule(pathUpdate.indexMoudulo());
                 pathRepositoy.save(pathList);
             }catch (NullPointerException erro){
                 throw new PathAspectsUnexpected("DeleteModule - Falha ao encontrar modulo em meio ao Path");
             }

         }
         else {throw new NotFound("DeleteModule - Path não encontrado");}
     }

     public void UpadateNewClass(ClassUpdate classUpdate){
         Path Path = pathRepositoy.findPath(classUpdate.PathID());
         if (Path != null){
             try {
                 Path.getModulos().get(classUpdate.indexModule()).UpdateNewClass(classUpdate.threePath());
                 var recentClass = Path.getModulos().get(classUpdate.indexModule()).getModulocontent().get(Path.getModulos().get(classUpdate.indexModule()).getModulocontent().size()-1);
                 Path.UpdatenClass(true,recentClass.getID());
                 pathRepositoy.save(Path);
             }catch (IndexOutOfBoundsException erro){
                 throw new PathAspectsUnexpected("UpadateNewClass - Falha ao encontrar modulo em meio ao Path");
             }
         }
         else {throw new NotFound("UpadateNewClass - Path não encontrado");}
     }

    public void UpdateClassUnic(ClassUpdate classUpdate){
        Path pathList = pathRepositoy.findPath(classUpdate.PathID());
        if (pathList != null){
            var newClass = new Aulas();
            newClass.ClassUpdate(classUpdate.threePath());
            try {
                pathList.getModulos().get(classUpdate.indexModule()).getModulocontent().set(classUpdate.indexClass(),newClass);
                pathRepositoy.save(pathList);
            }catch (NullPointerException erro) {
                throw new PathAspectsUnexpected("UpdateClassUnic - Falha em econtrar modulo ou aula");
            }

        } else {throw new NotFound("UpdateClassUnic - Path não encontrado");}
    }

     public void DeleteClassUnic(ClassUpdate classUpdate){
         Path pathList = pathRepositoy.findPath(classUpdate.PathID());
         if (pathList != null){
         try {
             var modulo = pathList.getModulos().get(classUpdate.indexModule());
             pathList.UpdatenClass(false,modulo.getModulocontent().get(classUpdate.indexClass()).getID());
             modulo.DeleteClass(classUpdate.indexClass());
             pathRepositoy.save(pathList);
         }catch (NullPointerException erro){
             throw new PathAspectsUnexpected("DeleteClassUnic - Falha em econtrar modulo ou aula");
         }

         }else {throw new NotFound("DeleteClassUnic - Path não encontrado");}
     }

    public ParallelDataDTO GetProfileInfo (String worldID){
        var User = userRepository.findByWorldID(worldID);
        if (User != null) {
            return new ParallelDataDTO(User);
        }else {throw new NotFound("GetProfileInfo - Autor/Usuario não encontrado");}
    }
}
