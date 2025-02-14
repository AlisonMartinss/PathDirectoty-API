package PathCarrer.API.DTO;
import PathCarrer.API.DTO.CreatePathStep.onePath;
import PathCarrer.API.DTO.CreatePathStep.twoPath;
import PathCarrer.API.DTO.CreatePathStep.threePath;
import java.util.List;


public record PathDTO(onePath onePathDTO, twoPath twoPathDTO,List<threePath> threePathsDTO){}

