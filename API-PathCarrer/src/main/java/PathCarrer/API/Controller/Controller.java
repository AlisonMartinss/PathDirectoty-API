package PathCarrer.API.Controller;


import PathCarrer.API.DTO.PathDTO;
import PathCarrer.API.Service.CRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/CRUD")
public class Controller {
    @Autowired
    private CRUD crud;


    @GetMapping()
    public ResponseEntity Hello(@RequestBody PathDTO JSON){
        return  ResponseEntity.ok(JSON);
    }
    @PostMapping("/OverPath")
    public ResponseEntity PathOverView (@RequestBody  PathDTO pathDTO){

        return ResponseEntity.noContent().build();
    }
    @PostMapping()
    public ResponseEntity C (@RequestBody PathDTO JSON){
        crud.create(JSON);
        return ResponseEntity.ok().build();
    }
}
