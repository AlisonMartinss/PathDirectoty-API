package PathCarrer.API.DTO;
import PathCarrer.API.DTO.CreatePathStep.onePath;
import PathCarrer.API.DTO.CreatePathStep.twoPath;
import PathCarrer.API.DTO.CreatePathStep.threePath;
import java.util.List;


public record PathDTO(String authorID,onePath onePathDTO, twoPath twoPathDTO){}

