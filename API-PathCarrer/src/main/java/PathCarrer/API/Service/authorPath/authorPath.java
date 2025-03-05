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
import java.util.List;
import java.util.Objects;

 /**

  MOTIVAÇÃO: CLASSE DESTIANADA A FAZER AS OPERAÇÕES QUE TANGEM A RELAÇÃO PATH E SEU AUTOR.

 - PathCreate: Criar path.
 - PathUpdate: Atualiza o Path.
 - UpadateNewModule: Adiciona um novo modulo.
 - UpdateModule: Atualiza o modulo já existente.
 - UpadateNewClass: Adiciona nova aula.
 - UpdateClassUnic: Atualiza uma aula.

 **/

@Service
public class authorPath {
    @Autowired
    private PathRepository pathRepositoy;

    public void PathCreate(PathDTO pathDTO){

        var novo = new Path();
        novo.CreateNewPath(pathDTO);

        pathRepositoy.save(novo);
    }

     public void  pathUpdate (PathUpdate pathDTO){

         List<Path> pathList = pathRepositoy.findPath(pathDTO.id());
         var path = pathList.get(0);

         path.UpdateModuloStats(pathDTO);

         pathRepositoy.save(path);
     }

     public void  pathDelete (PathUpdate pathDTO){

         List<Path> pathList = pathRepositoy.findPath(pathDTO.id());
         var path = pathList.get(0);
         path.deletePath();

         pathRepositoy.save(path);
     }


     public void UpadateNewModule(ModuloUpdateDTO pathUpdate){

        List<Path> pathList = pathRepositoy.findPath(pathUpdate.id());
        var pathMain = pathList.get(0);
        pathMain.AddNewModulo(pathUpdate);

        pathRepositoy.save(pathMain);
    }


    public void UpdateModule(ModuloUpdateDTO pathUpdate){

        List<Path> pathList = pathRepositoy.findPath(pathUpdate.id());
        var path  = pathList.get(0);

        var novoModulo = new modulo();
        novoModulo.AddNewModulo(pathUpdate.title(), pathUpdate.desc(), pathUpdate.ClassList());

        for (int i = 0; i < path.getModulos().size(); i++){

            if ((Objects.equals(path.getModulos().get(i).getName(), pathUpdate.nameModulo()))){
                path.getModulos().set(i,novoModulo);
                pathRepositoy.save(path);
            }
        }
    }

     public void UpadateNewClass(ClassUpdate classUpdate){

         List<Path> pathList = pathRepositoy.findPath(classUpdate.id());
         var pathMain = pathList.get(0);

         var newClass = new Aulas();
         newClass.ClassUpdate(classUpdate.threePath());

         for (int i = 0; i < pathMain.getModulos().size(); i++){
             if ((Objects.equals(pathMain.getModulos().get(i).getName(), classUpdate.nameModulo()))){
                 pathMain.getModulos().get(i).getModulocontent().add(newClass);
                 pathRepositoy.save(pathMain);
             }
         }
     }


    public void UpdateClassUnic(ClassUpdate classUpdate){

        List<Path> pathList = pathRepositoy.findPath(classUpdate.id());
        var pathMain = pathList.get(0);

        for (int i = 0; i < pathMain.getModulos().size(); i++){
            if ((Objects.equals(pathMain.getModulos().get(i).getName(), classUpdate.nameModulo()))){
                pathMain.getModulos().get(i).getModulocontent().get(classUpdate.placeClass()).ClassUpdate(classUpdate.threePath());
                pathRepositoy.save(pathMain);
            }
        }
    }

     public void UpdateNewClass(ClassUpdate classUpdate){

         List<Path> pathList = pathRepositoy.findPath(classUpdate.id());
         var pathMain = pathList.get(0);

         for (int i = 0; i < pathMain.getModulos().size(); i++){
             if ((Objects.equals(pathMain.getModulos().get(i).getName(), classUpdate.nameModulo()))){
                 var newClass = new Aulas();
                 newClass.ClassUpdate(classUpdate.threePath());
                 pathMain.getModulos().get(i).getModulocontent().add(newClass);
                 pathRepositoy.save(pathMain);
             }
         }
     }



}
