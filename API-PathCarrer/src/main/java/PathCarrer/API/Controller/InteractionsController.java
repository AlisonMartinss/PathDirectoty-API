package PathCarrer.API.Controller;

import PathCarrer.API.DTO.InteractionsDTO.InteractionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import PathCarrer.API.Service.interaction.interaction;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/interactions")
public class InteractionsController {
    @Autowired
    private interaction interaction;

    @PostMapping("/PostComment")
    @Transactional
    public ResponseEntity PostComment (@RequestBody  InteractionDTO interactionDTO){
        interaction.PostComment(interactionDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/DeleteComment")
    @Transactional
    public ResponseEntity DeleteComment (@RequestBody InteractionDTO interactionDTO){
        interaction.DeleteComment(interactionDTO);
        return ResponseEntity.ok().build();
    }


}
