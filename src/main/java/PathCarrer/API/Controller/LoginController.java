package PathCarrer.API.Controller;

import PathCarrer.API.Configurations.ConfigurationsAll.FilterSecurityConfingAndCORS;
import PathCarrer.API.Configurations.TokenConfig.Token;
import PathCarrer.API.DTO.SucefullGenericDTO.SucefullGeneric;
import PathCarrer.API.DTO.TokenDTO;
import PathCarrer.API.DTO.UsersDTO.userDTO;
import PathCarrer.API.ExeptionsClasses.GenericErro;
import PathCarrer.API.Model.User.User;
import PathCarrer.API.Repository.UserRepository;
import PathCarrer.API.Service.Login.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@SecurityRequirement(name = FilterSecurityConfingAndCORS.SECURITY)
public class LoginController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private Token TokenService;

    @Autowired
    private LoginService loginService;

    @Operation(
            summary = "Autentica um usuário e retorna um token JWT",
            description = "Este endpoint autentica um usuário com base nas credenciais fornecidas (usuário e senha). Em caso de sucesso, retorna um token JWT válido. Caso contrário, retorna erro de autenticação.",
            tags = {"Login"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Autenticação realizada com sucesso. Token retornado.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TokenDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Credenciais inválidas. Usuário não encontrado ou senha incorreta.",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            value = "{ \"erro\": \"Credenciais inválidas\" }")
                            )
                    )
            }
    )
    @Transactional
    @PostMapping
    public ResponseEntity login (@RequestBody userDTO userDTO){

        try {
            if (!loginService.UserNameExist(userDTO.userName())){
                throw new GenericErro("Usuario inexistente!");
            }

            var token = new UsernamePasswordAuthenticationToken(userDTO.userName(),userDTO.password());
            var login = manager.authenticate(token);
            var TokenOK = TokenService.TokenGenerate((User) login.getPrincipal());

            return ResponseEntity.ok(new TokenDTO(TokenOK));
        } catch (RuntimeException erro) {
            throw new GenericErro("Credenciais invalidas!");
        }
    }

    @Operation(
            summary = "Cria um usuario. Retorna mensagem confirmando sucesso da operação",
            description = "Este endpoint cria um novo usuário com base nas credenciais fornecidas (usuário e senha). Em caso de sucesso, retorna uma mensagem confirmando sucesso da operação.",
            tags = {"Login"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Autenticação realizada com sucesso.Menssagem de validação.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = SucefullGeneric.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Credenciais inválidas. Nome de usario já existente.",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                        @ExampleObject(
                                            value = "{ \"erro\": \"Um usuario com esse username já existe!\" }")
                                    }


                                    )

                            )

            }
    )
    @Transactional
    @PostMapping("/NewUser")
    public ResponseEntity CreateUser (@RequestBody userDTO userDTO){
        var response = loginService.createUser(userDTO);
        return ResponseEntity.ok(new SucefullGeneric(response));
    }

}
