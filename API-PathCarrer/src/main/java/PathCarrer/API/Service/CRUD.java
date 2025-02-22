package PathCarrer.API.Service;

import PathCarrer.API.DTO.PathDTO;
import PathCarrer.API.Model.Path;
import PathCarrer.API.Repository.PathRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CRUD {

    @Autowired
    private PathRepository pathRepositoy;

    @Transactional
    public void create (PathDTO JSON){
        var path = new Path(JSON);
        pathRepositoy.save(path);
    }

}
