package PathCarrer.API.Controller;

import PathCarrer.API.Service.Explorer.ExplorerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Explore")
public class ExplorerController {
    @Autowired
    private ExplorerService explorerService;
    @GetMapping()
    public ResponseEntity DefaultExplorer(){
        return ResponseEntity.ok(explorerService.DefaultExplorer());
    }
    @GetMapping("/CategoryExplorer")
    public ResponseEntity CategoryExplorer (@RequestParam String category){
        return ResponseEntity.ok(explorerService.CategoryExplorer(category));
    }

}
