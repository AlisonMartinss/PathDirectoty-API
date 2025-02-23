package PathCarrer.API.Repository;

import PathCarrer.API.Model.Path;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PathRepository extends MongoRepository<Path,String> {


    @Query("{ '_id': ?0 }")
    List<Path> findPath(String title);

    @Query("{ 'modulos.name': ?0}")
    List<Path> findModuloByName(String name);
}
