package PathCarrer.API.Service.interaction;

import PathCarrer.API.DTO.InteractionsDTO.InteractionDTO;
import PathCarrer.API.ExeptionsClasses.Hackers;
import PathCarrer.API.ExeptionsClasses.NotFound;
import PathCarrer.API.ExeptionsClasses.PathAspectsUnexpected;
import PathCarrer.API.Model.User.User;
import PathCarrer.API.Repository.PathRepository;
import PathCarrer.API.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


/**
 * Classe destinada a lidar com os pontos onde os usuários interagem entre si.
 *
 * <p>
 * This class handles the areas where users interact with each other.
 * </p>
 *
 * <p><strong>Operações disponíveis / Available operations:</strong></p>
 *
 * <ul>
 *   <li><strong>PostComment:</strong> Posta um comentário. <br>
 *       Posts a comment.</li>
 *   <li><strong>DeleteComment:</strong> Deleta um comentário. <br>
 *       Deletes a comment.</li>
 * </ul>
 *
 * <p>
 * Essas operações representam formas básicas de interação entre usuários,
 * como compartilhar opiniões ou remover conteúdo previamente postado.
 * </p>
 *
 * <p>
 * These operations represent basic user interactions, such as sharing opinions
 * or removing previously posted content.
 * </p>
 */


@Service
public class interaction {
    @Autowired
    private PathRepository pathRepository;
    @Autowired
    private UserRepository userRepository;


    public void PostComment (InteractionDTO interactionDTO){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User usuarioLogado = (User) auth.getPrincipal();

        var Path = pathRepository.findPathByID(interactionDTO.PathID());
        if (Path != null){

            try {
                    var modulo = Path.getModulos().get(interactionDTO.indexModule());
                    modulo.postComment(interactionDTO.Gen(),interactionDTO.fatherID(),interactionDTO.comment(),usuarioLogado.getWorldID());
                    pathRepository.save(Path);
            }catch (IndexOutOfBoundsException error){
                    throw new PathAspectsUnexpected("PostComment - Modulo não encontrado " + error);
            }
        }
        else {
            throw new NotFound("PostComment - Path não encontrado");
        }

    }
    public void DeleteComment (InteractionDTO interactionDTO){
        var Path = pathRepository.findPathByID(interactionDTO.PathID());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User usuarioLogado = (User) auth.getPrincipal();


        if (Path != null){

        try {
            var modulo = Path.getModulos().get(interactionDTO.indexModule());
            var anwerID = modulo.ElementCommentInfo(interactionDTO.Gen(),interactionDTO.fatherID(),interactionDTO.commentID()).getUserWordID();

            if (!usuarioLogado.getWorldID().equals(anwerID)) {
                throw new Hackers("fingindo ser outro");
            }

            modulo.DeleteComment(interactionDTO.Gen(),interactionDTO.fatherID(),interactionDTO.commentID());
            pathRepository.save(Path);
        }
        catch (NullPointerException error){
            throw new PathAspectsUnexpected("DeleteComment - Modulo não encontrado");
        }

        }else {
            throw new NotFound("DeleteComment - Path não encontrado");
        }
    }



}
