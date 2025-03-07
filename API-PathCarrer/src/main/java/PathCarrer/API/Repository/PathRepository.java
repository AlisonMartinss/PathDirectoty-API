package PathCarrer.API.Repository;

import PathCarrer.API.Model.Path;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PathRepository extends MongoRepository<Path,String> {


    @Query("{ '_id': ?0 }")
    Path findPath(String id);

}
