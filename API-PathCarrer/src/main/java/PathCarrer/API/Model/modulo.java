package PathCarrer.API.Model;

import PathCarrer.API.DTO.CreatePathStep.threePath;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Builder
public class modulo {
    private List<Aulas> modulocontent;
    private String name;
    private String description;
    private int qtdAulasModulo;

    public void AddNewModulo(String name, String  description, List<threePath> modulocontent) {
        this.modulocontent = new ArrayList<>();
        this.name = name;
        this.description = description;
        this.qtdAulasModulo = fillSet(modulocontent,this.modulocontent);

    }

    private int fillSet(List<threePath> ClassListJSON, List<Aulas> List) {
        for (int i = 0; i < ClassListJSON.size(); i++) {
            var ClassNew = new Aulas();
            ClassNew.ClassCreate(ClassListJSON.get(i).title(),ClassListJSON.get(i).description(),ClassListJSON.get(i).link());
            List.add(ClassNew);
            qtdAulasModulo++;
        }

        return this.qtdAulasModulo;
    }

    //* ===== Getters & Setters ====== *//

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
}
