package PathCarrer.API.Repository;

import PathCarrer.API.Model.ExplorerModels.Explorer;
import PathCarrer.API.Model.Path.Path;
import PathCarrer.API.Model.Path.modulo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PathRepository extends MongoRepository<Path,String> {


    @Query("{ '_id': ?0 }")
    Path findPathByID(String id);

    @Query(value = "{'idAuthor':?0}")
    List<Path> findByAuthor(String Author);
    @Query(value = "{}", fields = "{'title': 1, 'category': 1, 'adjectivesElements': 1, 'banner': 1, 'everAdd': 1 }")
    List<Explorer> defautExplorer();

    @Query(value = "{'_id': ?0 }", fields = "{'title': 1, 'category': 1, 'banner': 1, 'adjectivesElements': 1,'ClassPresent': 1, 'everAdd': 1 }")
    Explorer BasicInfo (String id);

    @Query(value = "{'category':?0}")
    List<Explorer> CategoryExplorer(String category);


    @Query(value = "{'_id': ?0 }", fields = "{'modulos': 1}")
    List<modulo> ElementCommentInfo (String id);

}
