package PathCarrer.API.DTO.GetsPathByUserDTO;

import PathCarrer.API.Model.Path.Comments.Comment;
import PathCarrer.API.Model.Path.Path;
import PathCarrer.API.Model.User.User;
import PathCarrer.API.Model.Path.adjectives;
import PathCarrer.API.Model.Path.modulo;

import java.util.List;

public record PathGetDTO(
        String IdAuthor, String PictureProfile,
        String BannerProfile, String title,
        String description, List<adjectives> adjectives,
        List<modulo> modulos, List<Comment> comments ) {
    public PathGetDTO(Path pathList, User user) {
        this(
        pathList.getIdAuthor(),
        user.getPictureProfile(),
        user.getBannerProfile(),
        pathList.getTitle(),
        pathList.getDescription(),
        pathList.getAdjectivesElements(),
        pathList.getModulos(),
        pathList.getComments());
    }
}
