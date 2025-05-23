package PathCarrer.API.Service.Login;


import PathCarrer.API.DTO.UsersDTO.userDTO;
import PathCarrer.API.ExeptionsClasses.GenericErro;
import PathCarrer.API.Model.User.User;
import PathCarrer.API.Repository.PathRepository;
import PathCarrer.API.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private PathRepository pathRepository;
    @Value("${default.Path.user}")
    private String PathIdDefaultForNewUsers;


    public boolean UserNameExist (String userName){
        if (userRepository.findByuserName(userName) == null){
            return false;
        }
        else {return true;}
    }

    public void createUser (userDTO userDTO){
        if (UserNameExist(userDTO.userName())){
            throw new GenericErro("Um usuario com esse username já existe!");
        }
        String crip = passwordEncoder.encode(userDTO.password());

        var user = new User(userDTO.userName(),crip);
        var pathDefault = pathRepository.findPathByID(PathIdDefaultForNewUsers);

        if (pathDefault == null){
            userRepository.save((user));
        }
        else {
            user.AddMyPaths(pathRepository.findPathByID(PathIdDefaultForNewUsers));
            userRepository.save((user));
        }
    }


}
