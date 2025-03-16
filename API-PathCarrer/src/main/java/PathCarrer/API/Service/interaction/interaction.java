package PathCarrer.API.Service.interaction;

import PathCarrer.API.DTO.InteractionsDTO.InteractionDTO;
import PathCarrer.API.Repository.PathRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class interaction {
    @Autowired
    private PathRepository pathRepository;


    public void PostComment (InteractionDTO interactionDTO){
        //verificação
        var Path = pathRepository.findPath(interactionDTO.PathID());
        Path.postComment(interactionDTO.userID(),interactionDTO.comment(),interactionDTO.address());
        pathRepository.save(Path);
    }
    public void DeleteComment (InteractionDTO interactionDTO){
        //verificação
        var Path = pathRepository.findPath(interactionDTO.PathID());
        Path.DeleteComment(interactionDTO.address());
        pathRepository.save(Path);
    }



}
