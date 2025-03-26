package PathCarrer.API.Service.interaction;

import PathCarrer.API.DTO.InteractionsDTO.InteractionDTO;
import PathCarrer.API.Repository.PathRepository;
import PathCarrer.API.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class interaction {
    @Autowired
    private PathRepository pathRepository;
    @Autowired
    private UserRepository userRepository;


    public void PostComment (InteractionDTO interactionDTO){
        //verificação
        var Path = pathRepository.findPath(interactionDTO.PathID());
        var User = userRepository.findByuserName(interactionDTO.userID());
        var modulo = Path.getModulos().get(interactionDTO.address().get(0));
        modulo.postComment(User.getWorldID().toString(),interactionDTO.comment(),interactionDTO.address());
        pathRepository.save(Path);
    }
    public void DeleteComment (InteractionDTO interactionDTO){
        //verificação
        var Path = pathRepository.findPath(interactionDTO.PathID());
        var modulo = Path.getModulos().get(interactionDTO.address().get(0));
        modulo.DeleteComment(interactionDTO.address());
        pathRepository.save(Path);
    }



}
