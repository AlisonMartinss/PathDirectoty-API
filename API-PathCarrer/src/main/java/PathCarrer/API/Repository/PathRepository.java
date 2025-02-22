package PathCarrer.API.Repository;

import PathCarrer.API.Model.Path;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PathRepository extends MongoRepository<Path,String> {

    @Query("{ 'title': ?0 }")
    List<Path> findPath(String title);



}
