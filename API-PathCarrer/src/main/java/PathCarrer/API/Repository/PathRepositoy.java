package PathCarrer.API.Repository;

import PathCarrer.API.Model.Path;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PathRepositoy extends MongoRepository<Path,String> {
}
