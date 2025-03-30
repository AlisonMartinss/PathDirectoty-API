package PathCarrer.API.Service.interaction;

import PathCarrer.API.DTO.InteractionsDTO.InteractionDTO;
import PathCarrer.API.Model.Path.Comments.Comment;
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
        var User = userRepository.findByuserName(interactionDTO.userName());
        var modulo = Path.getModulos().get(interactionDTO.indexModule());
        modulo.postComment(interactionDTO.Gen(),interactionDTO.fatherID(),interactionDTO.comment(),User.getWorldID());
        pathRepository.save(Path);
    }
    public String DeleteComment (InteractionDTO interactionDTO){
        //verificação
        var Path = pathRepository.findPath(interactionDTO.PathID());
        var modulo = Path.getModulos().get(interactionDTO.indexModule());
        String retorno =  modulo.DeleteComment(interactionDTO.Gen(),interactionDTO.fatherID(),interactionDTO.commentID());
        pathRepository.save(Path);

        return  retorno;
    }



}
