package PathCarrer.API.DTO.GetsPathByUserDTO;

import PathCarrer.API.Model.Aulas;
import PathCarrer.API.Model.modulo;

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
