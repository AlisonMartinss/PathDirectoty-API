package PathCarrer.API.Model;
import PathCarrer.API.DTO.MODULO2DTO;
import PathCarrer.API.DTO.PathDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

//TESTANTO

@Document(collection = "Testagem1312")
@Getter
@Setter
public class Path {
    @Id
    private String id;
    private  String categorys;

    private String subcategorys;

    private  String description;

    private List<String> adjectives;

    private List<Integer> scores;

    private List <comments> comments;

    private List<comments> forum;



    private List<MODULOSX> modulosxes;

    public Path(PathDTO JSON) {
        this.id = JSON.id();
        this.categorys = JSON.categorys();
        this.subcategorys = JSON.subcategorys();
        this.description = JSON.description();
        this.adjectives = JSON.adjectives();

        this.modulosxes = new ArrayList<>();


        if (JSON.modulos() != null) {
            fillset(modulosxes, JSON.modulos());
        } else {
            System.out.println("A lista modulos está vazia!");
        }
    }

    private void fillset(List<MODULOSX> List, List<MODULO2DTO> JSON) {
        for (int i = 0; i < 2; i++) {
            List.add(new MODULOSX(JSON.get(i).modulosN()));
            System.out.println("GPT");
        }
    }


}

