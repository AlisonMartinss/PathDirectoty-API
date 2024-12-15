package PathCarrer.API.Service;

import PathCarrer.API.DTO.PathDTO;
import PathCarrer.API.Model.Path;
import PathCarrer.API.Repository.PathRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class CRUD {
    @Autowired
    private PathRepositoy pathRepositoy;

    @Transactional
    public void create (PathDTO JSON){
        var path = new Path(JSON);
        pathRepositoy.save(path);
    }

}
