package PathCarrer.API.Service.FastInfo;

import PathCarrer.API.DTO.InteractionsDTO.InteractionDTO;
import PathCarrer.API.Model.ExplorerModels.Explorer;
import PathCarrer.API.Model.Path.Comments.Comment;
import PathCarrer.API.Model.Path.Comments.ZComments;
import PathCarrer.API.Model.Response;
import PathCarrer.API.Repository.PathRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
/**
 * Classe destinada a conter endpoints que retornam JSON simples, com poucas informações,
 * focados essencialmente em fornecer dados atualizados sobre determinadas entidades.
 * <br>
 * This class provides endpoints that return lightweight JSON responses, mainly built to
 * retrieve up-to-date information about specific entities such as Users, Comments, etc.
 *
 * <p><strong>Operações disponíveis / Available operations:</strong></p>
 *
 * <ul>
 *   <li><strong>PathBasicInfo:</strong> Retorna informações básicas sobre um Path. <br>
 *       Returns basic information about a Path.</li>
 *   <li><strong>ElementCommentInfo:</strong> Retorna informações básicas sobre um comentário. <br>
 *       Returns basic information about a Comment.</li>
 * </ul>
 *
 * <p>
 * Essa classe é útil para situações onde se deseja atualizar trechos de dados sem carregar
 * estruturas complexas ou detalhadas.
 * </p>
 *
 * <p>
 * This class is useful when updating snippets of data without loading full or complex structures.
 * </p>
 */

@Service
public class FastInfoService {
    @Autowired
    private PathRepository pathRepository;

    public Explorer PathBasicInfo (String PathID){
        return pathRepository.BasicInfo(PathID);
    }

    public Response<HashMap<String,Comment>> ElementCommentInfo (String PathID,int indexModule, int Gen, String commentID){
        var module = pathRepository.findPath(PathID).getModulos().get(indexModule);
        return module.ElementCommentInfo(Gen,commentID);
    }

}
