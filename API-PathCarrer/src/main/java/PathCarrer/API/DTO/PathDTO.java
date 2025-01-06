package PathCarrer.API.DTO;

import java.util.List;

public record PathDTO(String id, String categorys, String subcategorys, String description, List<String> adjectives, List<Integer> scores, List<moduloDTO> modulos){}

