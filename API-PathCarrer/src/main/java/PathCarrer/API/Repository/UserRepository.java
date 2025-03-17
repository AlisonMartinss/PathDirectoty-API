package PathCarrer.API.Repository;

import PathCarrer.API.Model.User.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<User,String> {

    @Query("{'userName':?0}")
    User findByuserName(String username);
    @Query("{'_id':?0}")
    User findByWorldID(String wordID);
}
