package PathCarrer.API.Repository;

import PathCarrer.API.Model.User.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends MongoRepository<User,String> {

    UserDetails findByuserName(String username);
}
