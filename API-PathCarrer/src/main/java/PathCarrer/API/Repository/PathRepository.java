package PathCarrer.API.Repository;

import PathCarrer.API.Model.ExplorerModels.Explorer;
import PathCarrer.API.Model.Path.Path;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PathRepository extends MongoRepository<Path,String> {


    @Query("{ '_id': ?0 }")
    Path findPath(String id);

    @Query(value = "{'idAuthor':?0}")
    List<Path> findByAuthor(String Author);
    @Query(value = "{}", fields = "{'title': 1, 'category': 1, 'adjectivesElements': 1, 'banner':1 }")
    List<Explorer> defautExplorer();

    @Query(value = "{'_id': ?0 }", fields = "{'title': 1, 'category': 1, 'banner': 1, 'adjectivesElements': 1,'ClassPresent': 1 }")
    Explorer BasicInfo (String id);

    @Query(value = "{'category':?0}")
    List<Explorer> CategoryExplorer(String category);

}
