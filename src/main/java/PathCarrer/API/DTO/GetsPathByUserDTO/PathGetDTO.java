package PathCarrer.API.DTO.GetsPathByUserDTO;

import PathCarrer.API.Model.Path.Comments.Comment;
import PathCarrer.API.Model.Path.Path;
import PathCarrer.API.Model.User.User;
import PathCarrer.API.Model.Path.modulo;

import java.util.List;

public record PathGetDTO(
        String IdAuthor, String PictureProfile,
        String BannerProfile, String title,
        String category,
        String description,
        List<modulo> modulos) {
    public PathGetDTO(Path pathList, User user) {
        this(
        user.getUsername(),
        user.getPictureProfile(),
        user.getBannerProfile(),
        pathList.getTitle(),
        pathList.getCategory(),
        pathList.getDescription(),
        pathList.getModulos());
    }
}
