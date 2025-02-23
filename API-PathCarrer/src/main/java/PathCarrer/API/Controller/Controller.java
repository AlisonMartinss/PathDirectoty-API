package PathCarrer.API.Controller;


import PathCarrer.API.DTO.PathDTO;
import PathCarrer.API.DTO.Update.ClassUpdate;
import PathCarrer.API.DTO.Update.ModuloUpdateDTO;
import PathCarrer.API.Lab.DTO.TestagemDTO;
import PathCarrer.API.Lab.Repository.TestagemRepository;
import PathCarrer.API.Model.Path;
import PathCarrer.API.Model.modulo;
import PathCarrer.API.Repository.PathRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import  PathCarrer.API.Lab.Model.RecadosDeTeste;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/CRUD")
public class Controller {
    @Autowired
    private PathRepository pathRepositoy;
    @Autowired
    private TestagemRepository testagemRepository;
    @GetMapping
    public String helloWord (){
        return "Hello Word ";
    }


    @PostMapping("/PathCreate")
    public ResponseEntity PathCreate (@RequestBody PathDTO pathDTO){

        var novo = new Path();
        novo.CreateNewPath(pathDTO);

        pathRepositoy.save(novo);
        return ResponseEntity.noContent().build();
    }
    @Transactional
    @PutMapping("/UpdateModulo")
    public ResponseEntity pathUpadateModule(@RequestBody ModuloUpdateDTO pathUpdate){

        List<Path> pathList = pathRepositoy.findPath(pathUpdate.id());
        var pathMain = pathList.get(0);
        pathMain.AddNewModulo(pathUpdate);
        pathRepositoy.save(pathMain);

        return ResponseEntity.ok(pathList.get(0));
    }
    @Transactional
    @PutMapping("/UpdateOverModulo")
    public ResponseEntity pathOverteModule(@RequestBody ModuloUpdateDTO pathUpdate){

        List<Path> pathList = pathRepositoy.findPath(pathUpdate.id());
        var path  = pathList.get(0);

        var novoModulo = new modulo();
        novoModulo.AddNewModulo(pathUpdate.title(), pathUpdate.desc(), pathUpdate.ClassList());

        for (int i = 0; i < path.getModulos().size(); i++){

            if ((Objects.equals(path.getModulos().get(i).getName(), pathUpdate.nameModulo()))){
                System.out.println("Elemento achado no indice: " + i + " " + path.getModulos().get(i).getName());
                path.getModulos().set(i,novoModulo);
                pathRepositoy.save(path);
                return ResponseEntity.ok(path);

            }
        }

        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PutMapping("/UpdateClassUnic")
    public ResponseEntity pathUpadateModule(@RequestBody ClassUpdate classUpdate){

        List<Path> pathList = pathRepositoy.findPath(classUpdate.id());
        var pathMain = pathList.get(0);

        for (int i = 0; i < pathMain.getModulos().size(); i++){
            if ((Objects.equals(pathMain.getModulos().get(i).getName(), classUpdate.nameModulo()))){
                pathMain.getModulos().get(i).getModulocontent().get(classUpdate.placeClass()).ClassUpdate(classUpdate.threePath());
                pathRepositoy.save(pathMain);
                return ResponseEntity.ok(pathMain);
            }
        }

        return ResponseEntity.ok(pathList.get(0));
    }

    @PostMapping("/RecadosDeTeste")
    public ResponseEntity Testagem (@RequestBody TestagemDTO testagemDTO){
        System.out.println("Chamada");

        List<RecadosDeTeste> novoTestagem = testagemRepository.comBaseNoRecado(testagemDTO.recado());

        return ResponseEntity.ok(novoTestagem);
    }
}
