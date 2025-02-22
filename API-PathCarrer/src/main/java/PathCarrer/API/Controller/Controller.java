package PathCarrer.API.Controller;


import PathCarrer.API.DTO.PathDTO;
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
import java.util.Optional;

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
        System.out.println("Chamada");
        var novo = new Path(pathDTO);
        System.out.println(novo.toString());
        pathRepositoy.save(novo);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/UpdatePath")
    public ResponseEntity pathUpadate (@RequestBody PathUpdateDTO pathUpdate){
        System.out.println("id: " + pathUpdate.id());

        List<Path> pathOK = pathRepositoy.findPath(pathUpdate.id());
        System.out.println(pathOK);
        return ResponseEntity.ok(pathOK);
    }

    @PostMapping("/RecadosDeTeste")
    public ResponseEntity Testagem (@RequestBody TestagemDTO testagemDTO){
        System.out.println("Chamada");

        var novo = new RecadosDeTeste();
        novo.setRecado(testagemDTO.recado());
        novo.setNome(testagemDTO.nome());
        testagemRepository.save(novo);

        List<RecadosDeTeste> novoTestagem = testagemRepository.comBaseNoRecadp(novo.getRecado());

        return ResponseEntity.ok(novoTestagem);
    }
}
