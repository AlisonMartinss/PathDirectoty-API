package PathCarrer.API.Controller;

import PathCarrer.API.DTO.InteractionsDTO.InteractionDTO;
import PathCarrer.API.Model.Path.Path;
import PathCarrer.API.Service.FastInfo.FastInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Short")
public class ShortPull {
    @Autowired
    private FastInfoService fastInfo;

    @GetMapping
    public ResponseEntity fastinfo (@RequestParam String PathID){
        return ResponseEntity.ok(fastInfo.PathBasicInfo(PathID));
    }

    @GetMapping("/ElementCommentInfo")
    public ResponseEntity ElementCommentInfo (@RequestParam String PathID,@RequestParam int indexModule,@RequestParam int Gen, @RequestParam String commentID){
        return ResponseEntity.ok(fastInfo.ElementCommentInfo(PathID,indexModule,Gen,commentID));
    }


}
