package PathCarrer.API.Model;

import PathCarrer.API.DTO.AulasDTO;
import java.util.ArrayList;
import java.util.List;

public class modulo {
    private List<Aulas> modulocontent; //modulosN
    private String name;
    private String description;


    public modulo(String name, String description, List<AulasDTO> modulosA) {
        this.modulocontent = new ArrayList<>();
        this.name = name;
        this.description = description;
        fillSet(modulosA, modulocontent);
    }


    private void fillSet(List<AulasDTO> JSON, List<Aulas> List) {
        for (int i = 0; i < JSON.size(); i++) {
            List.add(new Aulas(JSON.get(i)));
            System.out.println("GPT");
        }
    }



}
