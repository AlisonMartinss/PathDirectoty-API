package PathCarrer.API.Model;

import PathCarrer.API.DTO.CreatePathStep.threePath;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.PersistenceConstructor;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class modulo {
    private List<Aulas> modulocontent; //modulosN
    private String name;
    private String description;
    private int qtdAulasModulo;
    @PersistenceConstructor
    public modulo(String name, String  description, List<threePath> modulocontent) {
        this.modulocontent = new ArrayList<>();
        this.name = name;
        this.description = description;

    }

    private int fillSet(List<threePath> ClassListJSON, List<Aulas> List) {
        for (int i = 0; i < ClassListJSON.size(); i++) {
            List.add(new Aulas(ClassListJSON.get(i).title(),ClassListJSON.get(i).description(),ClassListJSON.get(i).link()));
            qtdAulasModulo++;
        }

        return this.qtdAulasModulo;
    }



}
