package PathCarrer.API.Controller;


import PathCarrer.API.DTO.ParallelDataDTO.ParallelDataDTO;
import PathCarrer.API.DTO.PathDTO;
import PathCarrer.API.DTO.SucefullGenericDTO.SucefullGeneric;
import PathCarrer.API.DTO.Update.ClassUpdate;
import PathCarrer.API.DTO.Update.ModuloUpdateDTO;
import PathCarrer.API.DTO.Update.PathUpdate;
import PathCarrer.API.ExeptionsClasses.GenericErro;
import PathCarrer.API.Service.authorPath.AuthorPath;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/CRUD")
public class AuthorController {
    @Autowired
    private AuthorPath authorPath;


    @GetMapping
    public String helloWord (){
        return "Hello Word ";
    }

    @Operation(
            summary = "Cria um novo Path",
            description = "Este endpoint cria um novo um novo Path com base nos parametros passados. Em caso de sucesso, retorna uma mensagem confirmando sucesso da operação.",
            tags = {"Author Operations"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Path criado com sucesso.Menssagem de validação.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = SucefullGeneric.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Credenciais inválidas. Usuario (Autor) usado para criar Path não foi encontrado.",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Author não encontrado",
                                                    summary = "O autor (usuário) que pretende criar o Path não foi localizado",
                                                    value = "{ \"erro\": \"PathCreate - Usuario nao encontrado\" }")
                                    }


                            )

                    )

            }
    )
    @PostMapping("/PathCreate")
    @Transactional
    public ResponseEntity PathCreate (@RequestBody PathDTO path){
        var response = authorPath.PathCreate(path);
        return ResponseEntity.ok(new SucefullGeneric(response));
    }

    @Operation(
            summary = "Atualiza um Path existente",
            description = "Este endpoint atualiza um Path já existente com base nos parâmetros fornecidos. Em caso de sucesso, retorna uma mensagem confirmando a operação.",
            tags = {"Author Operations"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Atualiza os campos: título, categoria, descrição, link do banner e tags. Retorna uma mensagem confirmando o sucesso.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = SucefullGeneric.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Autor ou Path não encontrado.",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Path não encontrado",
                                                    summary = "O Path fornecido não foi localizado",
                                                    value = "{ \"erro\": \"PathUpdate - Path não encontrado\" }"
                                            ),
                                            @ExampleObject(
                                                    name = "Author não encontrado",
                                                    summary = "O autor (usuário) vinculado ao Path não foi localizado",
                                                    value = "{ \"erro\": \"PathUpdate - Author do path não encontrado\" }"
                                            )
                                    }
                            )
                    )
            }
    )
    @PostMapping("/PathUpdate")
    @Transactional
    public ResponseEntity pathUpdate(@RequestBody PathUpdate pathUpdate) {
        var response = authorPath.pathUpdate(pathUpdate);
        return ResponseEntity.ok(new SucefullGeneric(response));
    }

    @Operation(
            summary = "Deleta Path",
            description = "Este endpoint deleta um Path já existente com base nos parâmetros fornecidos. Em caso de sucesso, retorna uma mensagem confirmando a operação.",
            tags = {"Author Operations"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Deleta Path. Retorna uma mensagem confirmando o sucesso.",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Sucesso da operação",
                                                    summary = "Path deletado com sucesso",
                                                    value = "{ \"message\": \"Path deletado com sucesso.\" }"
                                            )}
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Autor ou Path não encontrado.",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Path não encontrado",
                                                    summary = "O ID do Path fornecido não foi localizado",
                                                    value = "{ \"erro\": \"PathDelete - Path não encontrado\" }"
                                            ),
                                            @ExampleObject(
                                                    name = "Author não encontrado",
                                                    summary = "O autor (usuário) vinculado ao Path não foi localizado",
                                                    value = "{ \"erro\": \"PathDelete - Author do path não encontrado\" }"
                                            )
                                    }
                            )
                    )
            }
    )

    @PostMapping("/PathDelete")
    @Transactional
    public ResponseEntity pathDelete(@RequestBody PathUpdate pathUpdate){
        var response = authorPath.pathDelete(pathUpdate);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Adciona modulo",
            description = "Este endpoint adiciona um novo modulo a um Path já existente com base nos parâmetros fornecidos. Em caso de sucesso, retorna uma mensagem confirmando a operação.",
            tags = {"Author Operations"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Deleta Path. Retorna uma mensagem confirmando o sucesso.",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Sucesso da operação",
                                                    summary = "Novo modulo adicionado ao Path com sucesso",
                                                    value = "{ \"message\": \"Novo modulo adicionado.\" }"
                                            )}
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Autor ou Path não encontrado.",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Path não encontrado",
                                                    summary = "O ID do Path fornecido não foi localizado",
                                                    value = "{ \"erro\": \"UpadateNewModule - Path não encontrado\" }"
                                            ),
                                            @ExampleObject(
                                                    name = "Author não encontrado",
                                                    summary = "O autor (usuário) vinculado ao Path não foi localizado",
                                                    value = "{ \"erro\": \"UpadateNewModule - Author do path não encontrado\" }"
                                            )
                                    }
                            )
                    )
            }
    )
    @Transactional
    @PostMapping("/UpadateNewModule")
    public ResponseEntity pathUpadateNewModule(@RequestBody ModuloUpdateDTO pathUpdate){
        var response = authorPath.UpadateNewModule(pathUpdate);
        return ResponseEntity.ok(new SucefullGeneric(response));
    }

    @Operation(
            summary = "Atualizar modulo",
            description = "Este endpoint atualiza um modulo já existente com base nos parâmetros fornecidos. Em caso de sucesso, retorna uma mensagem confirmando a operação.",
            tags = {"Author Operations"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Atualiza Modulo. Retorna uma mensagem confirmando o sucesso.",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Sucesso da operação",
                                                    summary = "Modulo atualiziado com sucesso",
                                                    value = "{ \"message\": \"Modulo atualizado com sucesso.\" }"
                                            )}
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Autor ou Path ou Modulo não encontrado.",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Path não encontrado",
                                                    summary = "Path não existe",
                                                    value = "{ \"erro\": \"UpdateModule - Path não existe\" }"
                                            ),
                                            @ExampleObject(
                                                    name = "Modulo não encontrado",
                                                    summary = "Falha ao encontrar modulo em meio ao Path",
                                                    value = "{ \"erro\": \"UpdateModule - Não econtramos esse modulo em meio ao Path\" }"
                                            ),
                                            @ExampleObject(
                                                    name = "Author não encontrado",
                                                    summary = "O autor (usuário) vinculado ao Path não foi localizado",
                                                    value = "{ \"erro\": \"UpdateModule - Autor não encontrado\" }"
                                            )
                                    }
                            )
                    )
            }
    )
    @Transactional
    @PutMapping("/UpdateModule")
    public ResponseEntity UpdateModule(@RequestBody ModuloUpdateDTO pathUpdate){
        var response  = authorPath.UpdateModule(pathUpdate);
        return ResponseEntity.ok(new SucefullGeneric(response));
    }
    @Operation(
            summary = "Deletar modulo",
            description = "Este endpoint deleta um modulo já existente com base nos parâmetros fornecidos. Em caso de sucesso, retorna uma mensagem confirmando a operação.",
            tags = {"Author Operations"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Deleta Modulo. Retorna uma mensagem confirmando o sucesso.",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Sucesso da operação",
                                                    summary = "Modulo deletado com sucesso",
                                                    value = "{ \"message\": \"Modulo deletado com sucesso.\" }"
                                            )}
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Autor ou Path ou Modulo não encontrado.",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Path não encontrado",
                                                    summary = "O Path requisitado não existe",
                                                    value = "{ \"erro\": \"DeleteModule - O Path requisitado não existe\" }"
                                            ),
                                            @ExampleObject(
                                                    name = "Modulo não encontrado",
                                                    summary = "O modulo requisitado não existe",
                                                    value = "{ \"erro\": \"DeleteModule - O modulo requisitado não existe\" }"
                                            ),
                                            @ExampleObject(
                                                    name = "Author não encontrado",
                                                    summary = "O autor (usuário) vinculado ao Path não foi localizado",
                                                    value = "{ \"erro\": \"DeleteModule - Autor não encontrado\" }"
                                            )
                                    }
                            )
                    )
            }
    )
    @Transactional
    @PostMapping("/DeleteModule")
    public ResponseEntity DeleteModule(@RequestBody ModuloUpdateDTO pathUpdate){
        var response = authorPath.DeleteModule(pathUpdate);
        return ResponseEntity.ok(new SucefullGeneric(response));
    }


    @Operation(
            summary = "Adiciona aula",
            description = "Este endpoint adiciona uma nova aula a um modulo já existente com base nos parâmetros fornecidos. Em caso de sucesso, retorna uma mensagem confirmando a operação.",
            tags = {"Author Operations"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Adiciona nova aula. Retorna uma mensagem confirmando o sucesso.",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Sucesso da operação",
                                                    summary = "Aula adicionada com sucesso.",
                                                    value = "{ \"message\": \"Modulo deletado com sucesso.\" }"
                                            )}
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Autor ou Path ou Modulo não encontrado.",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Path não encontrado",
                                                    summary = "O Path requisitado não existe",
                                                    value = "{ \"erro\": \"UpadateNewClass - O Path requisitado não existe\" }"
                                            ),
                                            @ExampleObject(
                                                    name = "Modulo não encontrado",
                                                    summary = "O modulo requisitado não existe",
                                                    value = "{ \"erro\": \"UpadateNewClass - O modulo requisitado não existe\" }"
                                            ),
                                            @ExampleObject(
                                                    name = "Author não encontrado",
                                                    summary = "O autor (usuário) vinculado ao Path não foi localizado",
                                                    value = "{ \"erro\": \"UpadateNewClass - Autor não encontrado\" }"
                                            )
                                    }
                            )
                    )
            }
    )
    @Transactional
    @PostMapping("/UpadateNewClass")
    public ResponseEntity UpadateNewClass(@RequestBody ClassUpdate classUpdate){
        var response = authorPath.UpadateNewClass(classUpdate);
        return ResponseEntity.ok(new SucefullGeneric(response));
    }
    @Operation(
            summary = "Atualza aula já existente",
            description = "Este endpoint atualzia uma aula com base nos parâmetros fornecidos. Em caso de sucesso, retorna uma mensagem confirmando a operação.",
            tags = {"Author Operations"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Atualza aula. Retorna uma mensagem confirmando o sucesso.",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Sucesso da operação",
                                                    summary = "Aula atualizada com sucesso.",
                                                    value = "{ \"message\": \"Aula atualizada com sucesso.\" }"
                                            )}
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Autor ou Path ou Modulo ou Aula não encontrado.",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Path não encontrado",
                                                    summary = "O Path requisitado não existe",
                                                    value = "{ \"erro\": \"UpdateClassUnic - O Path requisitado não existe\" }"
                                            ),
                                            @ExampleObject(
                                                    name = "Modulo não encontrado ou Aula não encontrado",
                                                    value = "{ \"erro\": \"UpdateClassUnic - O modulo ou aula que se pretende atualizar não existe\" }"
                                            ),
                                            @ExampleObject(
                                                    name = "Author não encontrado",
                                                    summary = "O autor (usuário) vinculado ao Path não foi localizado",
                                                    value = "{ \"erro\": \"UpdateClassUnic - Autor não encontrado\" }"
                                            )
                                    }
                            )
                    )
            }
    )
    @Transactional
    @PutMapping("/UpdateClassUnic")
    public ResponseEntity UpdateClassUnic (@RequestBody ClassUpdate classUpdate){
        var resonse = authorPath.UpdateClassUnic(classUpdate);
        return ResponseEntity.ok(resonse);
    }

    @Operation(
            summary = "Deleta aula",
            description = "Este endpoint deleta uma aula com base nos parâmetros fornecidos. Em caso de sucesso, retorna uma mensagem confirmando a operação.",
            tags = {"Author Operations"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Deleta aula. Retorna uma mensagem confirmando o sucesso.",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Sucesso da operação",
                                                    summary = "Aula deletada com sucesso.",
                                                    value = "{ \"message\": \"Aula deletada com sucesso.\" }"
                                            )}
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Autor ou Path ou Modulo não encontrado.",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Path não encontrado",
                                                    summary = "O Path requisitado não existe",
                                                    value = "{ \"erro\": \"DeleteClassUnic - O Path requisitado não existe\" }"
                                            ),
                                            @ExampleObject(
                                                    name = "Modulo não encontrado ou Aula não encontrado",
                                                    value = "{ \"erro\": \"DeleteClassUnic - O modulo ou aula que se pretende atualizar não existe\" }"
                                            ),
                                            @ExampleObject(
                                                    name = "Author não encontrado",
                                                    summary = "O autor (usuário) vinculado ao Path não foi localizado",
                                                    value = "{ \"erro\": \"DeleteClassUnic - Autor não encontrado\" }"
                                            )
                                    }
                            )
                    )
            }
    )
    @Transactional
    @PutMapping("/DeleteClassUnic")
    public ResponseEntity DeleteClassUnic (@RequestBody ClassUpdate classUpdate){
        var response = authorPath.DeleteClassUnic(classUpdate);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Info user.",
            description = "Este endpoint traz a tona certas informações sobre um usuario com base nos parâmetros fornecidos. Em caso de sucesso, retorna uma mensagem confirmando a operação.",
            tags = {"Author Operations"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Traz a tona informações do usuario. Retorna uma mensagem confirmando o sucesso.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ParallelDataDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "usuario não existe.",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "usuário não encontrado",
                                                    summary = "O usuário não existe ou não pode momentaneamente não ser encontrado.",
                                                    value = "{ \"erro\": \"GetInfoUser - usuário não encontrado\" }"
                                            )
                                    }
                            )
                    )
            }
    )

    @Transactional
    @GetMapping("/GetInfoUser")
    public ResponseEntity GetInfoUser (@RequestParam String username){
        return ResponseEntity.ok(authorPath.GetProfileInfo(username));
    }










}
