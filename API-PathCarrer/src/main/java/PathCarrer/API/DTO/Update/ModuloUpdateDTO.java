package PathCarrer.API.DTO.Update;

import PathCarrer.API.DTO.CreatePathStep.threePath;

import java.util.List;

public record ModuloUpdateDTO(String id, String title, String desc, List<threePath> ClassList, String nameModulo) {
}
