package PathCarrer.API.Model;

import PathCarrer.API.DTO.CreatePathStep.threePath;
import PathCarrer.API.DTO.CreatePathStep.twoPath;
import java.util.ArrayList;
import java.util.List;

public class modulo {
    private List<Aulas> modulocontent; //modulosN
    private String name;
    private String description;


    public modulo(twoPath JSON) {
        this.modulocontent = new ArrayList<>();
        this.name = JSON.title();
        this.description = JSON.desc();
        fillSet(JSON.ClassList(), modulocontent);
    }


    private void fillSet(List<threePath> ClassListJSON, List<Aulas> List) {
        for (int i = 0; i < ClassListJSON.size(); i++) {
            List.add(new Aulas(ClassListJSON.get(i)));
        }
    }



}
