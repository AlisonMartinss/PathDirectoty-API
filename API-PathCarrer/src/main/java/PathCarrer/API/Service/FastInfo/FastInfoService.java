package PathCarrer.API.Service.FastInfo;

import PathCarrer.API.Model.ExplorerModels.Explorer;
import PathCarrer.API.Model.Path.Path;
import PathCarrer.API.Repository.PathRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FastInfoService {
    @Autowired
    private PathRepository pathRepository;

    public Explorer PathBasicInfo (String PathID){
        return pathRepository.BasicInfo(PathID);
    }

}
