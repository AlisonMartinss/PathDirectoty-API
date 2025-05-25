package PathCarrer.API.DTO.UsersDTO;


import io.swagger.v3.oas.annotations.media.Schema;

public record userDTO (
        @Schema(description = "Nome de usuário", example = "joaosilva")
        String userName,
        @Schema(description = "Senha do usuário", example = "senha7878")
        String password,
        @Schema(description = "Confirmação da senha do usuário", example = "joaosilva")
        String confirmPassword,
        @Schema(description = "Novo nome do usuario", example = "JoaoAlberto")
        String newUserName,
        @Schema(description = "Link da imagem de perfil", example = "htts//:pinteres.com/da5w7da6da")
        String PictureProfile,
        @Schema(description = "Link da imagem de banner", example = "htts//:pinteres.com/da5w7da6da")
        String BannerProfile){}
