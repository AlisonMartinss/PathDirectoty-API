package PathCarrer.API.Service.Explorer;


import PathCarrer.API.Model.ExplorerModels.Explorer;
import PathCarrer.API.Repository.PathRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExplorerService {
    @Autowired
    private PathRepository pathRepository;

    public List<Explorer> DefaultExplorer (){
        return pathRepository.defautExplorer();
    }

    public List<Explorer> CategoryExplorer (String category){
        return pathRepository.CategoryExplorer(category);
    }


}
