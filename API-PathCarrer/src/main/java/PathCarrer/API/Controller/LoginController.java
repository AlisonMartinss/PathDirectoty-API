package PathCarrer.API.Controller;

import PathCarrer.API.Configurations.TokenConfig.Token;
import PathCarrer.API.DTO.TokenDTO;
import PathCarrer.API.DTO.UsersDTO.userDTO;
import PathCarrer.API.Model.User.User;
import PathCarrer.API.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Login")

public class LoginController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private Token TokenService;
    @Transactional
    @PostMapping
    public ResponseEntity login (@RequestBody userDTO userDTO){
        var token = new UsernamePasswordAuthenticationToken(userDTO.userName(),userDTO.password());
        var login = manager.authenticate(token);
        var TokenOK = TokenService.TokenGenerate((User) login.getPrincipal());

        return ResponseEntity.ok(new TokenDTO(TokenOK));
    }
    @Transactional
    @PostMapping("/NewUser")
    public ResponseEntity CreateUser (@RequestBody userDTO userDTO){

        String crip = passwordEncoder.encode(userDTO.password());
        userRepository.save(new User(userDTO.userName(),crip));

        return ResponseEntity.ok().build();
    }

}
