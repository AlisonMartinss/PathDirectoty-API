package PathCarrer.API.Controller;


import PathCarrer.API.DTO.PathDTO;
import PathCarrer.API.DTO.Update.ClassUpdate;
import PathCarrer.API.DTO.Update.ModuloUpdateDTO;
import PathCarrer.API.DTO.Update.PathUpdate;
import PathCarrer.API.Service.authorPath.AuthorPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/CRUD")
public class AuthorController {
    @Autowired
    private AuthorPath authorPath;

    @GetMapping
    public String helloWord (){
        return "Hello Word ";
    }

    @PostMapping("/PathCreate")
    @Transactional
    public ResponseEntity PathCreate (@RequestBody PathDTO path){
        authorPath.PathCreate(path);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/PathUpdate")
    @Transactional
    public ResponseEntity pathUpdate(@RequestBody PathUpdate pathUpdate){
        authorPath.pathUpdate(pathUpdate);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/PathDelete")
    @Transactional
    public ResponseEntity pathDelete(@RequestBody PathUpdate pathUpdate){
        authorPath.pathDelete(pathUpdate);
        return ResponseEntity.noContent().build();
    }
    @Transactional
    @PostMapping("/UpadateNewModule")
    public ResponseEntity pathUpadateNewModule(@RequestBody ModuloUpdateDTO pathUpdate){
        System.out.println("New moduile");
        authorPath.UpadateNewModule(pathUpdate);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PutMapping("/UpdateModule")
    public ResponseEntity UpdateModule(@RequestBody ModuloUpdateDTO pathUpdate){
        authorPath.UpdateModule(pathUpdate);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PostMapping("/DeleteModule")
    public ResponseEntity DeleteModule(@RequestBody ModuloUpdateDTO pathUpdate){
        authorPath.DeleteModule(pathUpdate);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PostMapping("/UpadateNewClass")
    public ResponseEntity UpadateNewClass(@RequestBody ClassUpdate classUpdate){
        authorPath.UpadateNewClass(classUpdate);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PutMapping("/UpdateClassUnic")
    public ResponseEntity UpdateClassUnic (@RequestBody ClassUpdate classUpdate){
        authorPath.UpdateClassUnic(classUpdate);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PutMapping("/DeleteClassUnic")
    public ResponseEntity DeleteClassUnic (@RequestBody ClassUpdate classUpdate){
        authorPath.DeleteClassUnic(classUpdate);
        return ResponseEntity.noContent().build();
    }






}
