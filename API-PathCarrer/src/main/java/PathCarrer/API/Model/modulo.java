package PathCarrer.API.Model;

import PathCarrer.API.DTO.CreatePathStep.threePath;
import lombok.*;
import org.springframework.data.annotation.PersistenceConstructor;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Builder
public class modulo {
    private List<Aulas> modulocontent; //modulosN
    private String name;
    private String description;
    private int qtdAulasModulo;

    public List<Aulas> getModulocontent() {
        return modulocontent;
    }

    public void setModulocontent(List<Aulas> modulocontent) {
        this.modulocontent = modulocontent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQtdAulasModulo() {
        return qtdAulasModulo;
    }

    public void setQtdAulasModulo(int qtdAulasModulo) {
        this.qtdAulasModulo = qtdAulasModulo;
    }

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
