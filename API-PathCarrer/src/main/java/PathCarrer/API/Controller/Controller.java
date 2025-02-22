package PathCarrer.API.Controller;


import PathCarrer.API.DTO.Update.PathUpdateDTO;
import PathCarrer.API.Lab.DTO.TestagemDTO;
import PathCarrer.API.Lab.Repository.TestagemRepository;
import PathCarrer.API.Model.Path;
import PathCarrer.API.Repository.PathRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import  PathCarrer.API.Lab.Model.RecadosDeTeste;

import java.util.List;

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


    /*@PostMapping("/PathCreate")
    public ResponseEntity PathCreate (@RequestBody PathDTO pathDTO){
        System.out.println("Chamada");
        var novo = new Path(pathDTO);
        System.out.println(novo.toString());
        pathRepositoy.save(novo);
        return ResponseEntity.noContent().build();
    }*/

    @PostMapping("/UpdatePath")
    public ResponseEntity pathUpadate (@RequestBody PathUpdateDTO pathUpdate){
        System.out.println("id: " + pathUpdate.title());

        List<Path> pathOK = pathRepositoy.findPath(pathUpdate.title());

        return ResponseEntity.ok(pathOK);
    }

    @PostMapping("/RecadosDeTeste")
    public ResponseEntity Testagem (@RequestBody TestagemDTO testagemDTO){
        System.out.println("Chamada");

        List<RecadosDeTeste> novoTestagem = testagemRepository.comBaseNoRecado(testagemDTO.recado());

        return ResponseEntity.ok(novoTestagem);
    }
}
