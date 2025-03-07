package PathCarrer.API.Repository;

import PathCarrer.API.Model.User.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserRepository extends MongoRepository<User,String> {

    UserDetails findByuserName(String username);
    @Query("{'_id':?0}")
    User findByID (String username);
}
