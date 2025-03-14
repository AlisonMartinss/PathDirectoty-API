package PathCarrer.API.DTO.GetsPathByUserDTO;

import PathCarrer.API.Model.Path;
import PathCarrer.API.Model.User.User;
import PathCarrer.API.Model.adjectives;
import PathCarrer.API.Model.comments;
import PathCarrer.API.Model.modulo;

import java.util.List;

public record PathGetDTO(String IdAuthor,String PictureProfile, String BannerProfile,String title,String description, List<adjectives> adjectives, List<modulo> modulos) {
    public PathGetDTO(Path pathList, User user) {
        this(
        pathList.getIdAuthor(),
        user.getPictureProfile(),
        user.getBannerProfile(),
        pathList.getTitle(),
        pathList.getDescription(),
        pathList.getAdjectivesElements(),
        pathList.getModulos());
    }
}
