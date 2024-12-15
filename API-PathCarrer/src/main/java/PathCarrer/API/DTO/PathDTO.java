package PathCarrer.API.DTO;
import PathCarrer.API.Model.MODULOSX;
import PathCarrer.API.Model.modulos;

import java.util.List;

public record PathDTO(String id, String categorys, String subcategorys, String description, List<String> adjectives, List<Integer> scores, List<MODULO2DTO> modulos){}

