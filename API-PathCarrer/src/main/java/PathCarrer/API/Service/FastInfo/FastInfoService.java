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
