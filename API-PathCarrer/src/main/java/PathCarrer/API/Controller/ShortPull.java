package PathCarrer.API.Controller;

import PathCarrer.API.Model.Path.Path;
import PathCarrer.API.Service.FastInfo.FastInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Short")
public class ShortPull {
    @Autowired
    private FastInfoService fastInfo;

    @GetMapping
    public ResponseEntity fastinfo (@RequestParam String PathID){
        return ResponseEntity.ok(fastInfo.PathBasicInfo(PathID));
    }
}
