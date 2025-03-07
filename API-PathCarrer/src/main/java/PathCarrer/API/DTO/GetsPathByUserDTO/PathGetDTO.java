package PathCarrer.API.DTO.GetsPathByUserDTO;

import PathCarrer.API.Model.Path;
import PathCarrer.API.Model.adjectives;
import PathCarrer.API.Model.comments;
import PathCarrer.API.Model.modulo;

import java.util.List;

public record PathGetDTO(String title,String description, List<adjectives> adjectives, List<modulo> modulos, List<comments> comments) {
    public PathGetDTO(Path pathList) {
        this(
        pathList.getTitle(),
        pathList.getDescription(),
        pathList.getAdjectivesElements(),
        pathList.getModulos(),
        pathList.getComments());
    }
}
