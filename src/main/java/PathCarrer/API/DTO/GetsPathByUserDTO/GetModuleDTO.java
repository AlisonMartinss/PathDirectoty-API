package PathCarrer.API.DTO.GetsPathByUserDTO;

import PathCarrer.API.Model.Path.Aulas;
import PathCarrer.API.Model.Path.modulo;

import java.util.List;

public record GetModuleDTO(List<Aulas> modulocontent, String name, String description, int qtdAulasModulo){
    public GetModuleDTO(modulo modulo) {
        this(
                modulo.getModulocontent(),
                modulo.getName(),
                modulo.getDescription(),
                modulo.getQtdAulasModulo());
    }
}
