package PathCarrer.API.Service.authorPath;

import PathCarrer.API.DTO.PathDTO;
import PathCarrer.API.DTO.Update.ClassUpdate;
import PathCarrer.API.DTO.Update.ModuloUpdateDTO;
import PathCarrer.API.DTO.Update.PathUpdate;
import PathCarrer.API.Model.Aulas;
import PathCarrer.API.Model.Path;
import PathCarrer.API.Model.modulo;
import PathCarrer.API.Repository.PathRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

 /**

  MOTIVAÇÃO: CLASSE DESTIANADA A FAZER AS OPERAÇÕES QUE TANGEM A RELAÇÃO PATH E SEU AUTOR.

 - PathCreate: Criar path.
 - PathUpdate: Atualiza o Path.
 - UpadateNewModule: Adiciona um novo modulo.
 - UpdateModule: Atualiza o modulo já existente.
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

         Path pathList = pathRepositoy.findPath(pathDTO.PathID());
         pathList.UpdateModuloStats(pathDTO);

         pathRepositoy.save(pathList);
     }

     public void  pathDelete (PathUpdate pathDTO){

         Path pathList = pathRepositoy.findPath(pathDTO.PathID());
         pathList.deletePath();

         pathRepositoy.save(pathList);
     }


     public void UpadateNewModule(ModuloUpdateDTO pathUpdate){

        Path pathList = pathRepositoy.findPath(pathUpdate.id());
        pathList.AddNewModulo(pathUpdate);

        pathRepositoy.save(pathList);
    }


    public void UpdateModule(ModuloUpdateDTO pathUpdate){

        Path pathList = pathRepositoy.findPath(pathUpdate.id());

        var novoModulo = new modulo();
        novoModulo.AddNewModulo(pathUpdate.title(), pathUpdate.desc(), pathUpdate.ClassList());

        for (int i = 0; i < pathList.getModulos().size(); i++){

            if ((Objects.equals(pathList.getModulos().get(i).getName(), pathUpdate.nameModulo()))){
                pathList.getModulos().set(i,novoModulo);
                pathRepositoy.save(pathList);
            }
        }
    }

     public void UpadateNewClass(ClassUpdate classUpdate){

         Path pathList = pathRepositoy.findPath(classUpdate.id());

         var newClass = new Aulas();
         newClass.ClassUpdate(classUpdate.threePath());

         for (int i = 0; i < pathList.getModulos().size(); i++){
             if ((Objects.equals(pathList.getModulos().get(i).getName(), classUpdate.nameModulo()))){
                 pathList.getModulos().get(i).getModulocontent().add(newClass);
                 pathRepositoy.save(pathList);
             }
         }
     }


    public void UpdateClassUnic(ClassUpdate classUpdate){

        Path pathList = pathRepositoy.findPath(classUpdate.id());

        for (int i = 0; i < pathList.getModulos().size(); i++){
            if ((Objects.equals(pathList.getModulos().get(i).getName(), classUpdate.nameModulo()))){
                pathList.getModulos().get(i).getModulocontent().get(classUpdate.placeClass()).ClassUpdate(classUpdate.threePath());
                pathRepositoy.save(pathList);
            }
        }
    }

     public void UpdateNewClass(ClassUpdate classUpdate){

         Path pathList = pathRepositoy.findPath(classUpdate.id());

         for (int i = 0; i < pathList.getModulos().size(); i++){
             if ((Objects.equals(pathList.getModulos().get(i).getName(), classUpdate.nameModulo()))){
                 var newClass = new Aulas();
                 newClass.ClassUpdate(classUpdate.threePath());
                 pathList.getModulos().get(i).getModulocontent().add(newClass);
                 pathRepositoy.save(pathList);
             }
         }
     }



}
