package PathCarrer.API.Service.Login;


import PathCarrer.API.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private UserRepository repository;

    public boolean UserNameExist (String userName){
        if (repository.findByuserName(userName) == null){
            return false;
        }
        else {return true;}
    }


}
