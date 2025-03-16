package PathCarrer.API.Service.authorPath;

import PathCarrer.API.DTO.PathDTO;
import PathCarrer.API.DTO.Update.ClassUpdate;
import PathCarrer.API.DTO.Update.ModuloUpdateDTO;
import PathCarrer.API.DTO.Update.PathUpdate;
import PathCarrer.API.Model.Path.Aulas;
import PathCarrer.API.Model.Path.Path;
import PathCarrer.API.Repository.PathRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**

  MOTIVAÇÃO: CLASSE DESTIANADA A FAZER AS OPERAÇÕES QUE TANGEM A RELAÇÃO PATH E SEU AUTOR.

 - DefaultExplorer: Criar path.
 - PathUpdate: Atualiza o Path.
 - PathDelete: Deleta Path.
 - UpadateNewModule: Adiciona um novo modulo.
 - UpdateModule: Atualiza o modulo já existente.
 - DeleteModule: Deleta modulo
 - UpadateNewClass: Adiciona nova aula.
 - UpdateClassUnic: Atualiza uma aula.

 */

@Service
public class AuthorPath {
    @Autowired
    private PathRepository pathRepositoy;

    public void PathCreate(PathDTO pathDTO){

        var novo = new Path();
        novo.CreateNewPath(pathDTO);

        pathRepositoy.save(novo);
    }

     public void  pathUpdate (PathUpdate pathDTO){

         Path path = pathRepositoy.findPath(pathDTO.PathID());
         path.UpdatePathStats(pathDTO);

         pathRepositoy.save(path);
     }

     public void  pathDelete (PathUpdate pathDTO){
        if (pathRepositoy.findPath(pathDTO.PathID()) != null){
            pathRepositoy.deleteById(pathDTO.PathID());
         }
     }


     public void UpadateNewModule(ModuloUpdateDTO pathUpdate){

        Path pathList = pathRepositoy.findPath(pathUpdate.PathID());
        pathList.AddNewModulo(pathUpdate);

        pathRepositoy.save(pathList);
    }


    public void UpdateModule(ModuloUpdateDTO pathUpdate){

        Path pathList = pathRepositoy.findPath(pathUpdate.PathID());
        pathList.getModulos().get(pathUpdate.indexMoudulo()).setName(pathUpdate.title());
        pathList.getModulos().get(pathUpdate.indexMoudulo()).setDescription(pathUpdate.desc());

        pathRepositoy.save(pathList);
    }

     public void DeleteModule(ModuloUpdateDTO pathUpdate){
         Path pathList = pathRepositoy.findPath(pathUpdate.PathID());
         pathList.getModulos().remove(pathUpdate.indexMoudulo());
         pathRepositoy.save(pathList);
     }



     public void UpadateNewClass(ClassUpdate classUpdate){

         Path pathList = pathRepositoy.findPath(classUpdate.id());

         var newClass = new Aulas();
         newClass.ClassUpdate(classUpdate.threePath());

         pathList.getModulos().get(classUpdate.indexModule()).getModulocontent().add(newClass);
         pathRepositoy.save(pathList);
     }


    public void UpdateClassUnic(ClassUpdate classUpdate){
        Path pathList = pathRepositoy.findPath(classUpdate.id());

        var newClass = new Aulas();
        newClass.ClassUpdate(classUpdate.threePath());

        pathList.getModulos().get(classUpdate.indexModule()).getModulocontent().set(classUpdate.indexClass(),newClass);
        pathRepositoy.save(pathList);
    }
     public void DeleteClassUnic(ClassUpdate classUpdate){
         Path pathList = pathRepositoy.findPath(classUpdate.id());

         pathList.getModulos().get(classUpdate.indexModule()).getModulocontent().remove(classUpdate.indexClass());
         pathRepositoy.save(pathList);
     }



}
