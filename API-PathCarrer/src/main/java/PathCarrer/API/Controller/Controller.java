package PathCarrer.API.Controller;


import PathCarrer.API.DTO.DTOtest;
import PathCarrer.API.DTO.PathDTO;
import PathCarrer.API.Model.Path;
import PathCarrer.API.Repository.PathRepositoy;
import PathCarrer.API.Service.CRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/CRUD")
public class Controller {
    @Autowired
    private PathRepositoy pathRepositoy;
    @GetMapping
    public String helloWord (@RequestBody DTOtest JSON){
        return "Hello Word " + JSON.teste();
    }


    @PostMapping("/PathCreate")
    public ResponseEntity PathCreate (@RequestBody PathDTO pathDTO){
        System.out.println("Chamada");
        var novo = new Path(pathDTO);
        System.out.println(novo.toString());
        pathRepositoy.save(novo);
        return ResponseEntity.noContent().build();
    }
}
