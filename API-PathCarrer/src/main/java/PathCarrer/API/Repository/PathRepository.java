package PathCarrer.API.Repository;

import PathCarrer.API.Model.ExplorerModels.Explorer;
import PathCarrer.API.Model.Path;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PathRepository extends MongoRepository<Path,String> {


    @Query("{ '_id': ?0 }")
    Path findPath(String id);
    @Query(value = "{}", fields = "{'title': 1, 'category': 1, 'adjectivesElements': 1}")
    List<Explorer> defautExplorer();

    @Query(value = "{'category':?0}")
    List<Explorer> CategoryExplorer(String category);
}
