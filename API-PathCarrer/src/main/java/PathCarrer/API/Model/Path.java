package PathCarrer.API.Model;
import PathCarrer.API.DTO.moduloDTO;
import PathCarrer.API.DTO.PathDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;



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

    private List<modulo> modulos; // Esta lista está para 'modulos'. E 'modulo' está para 'modulosN'

    public Path(PathDTO JSON) {
        this.id = JSON.id();
        this.categorys = JSON.categorys();
        this.subcategorys = JSON.subcategorys();
        this.description = JSON.description();
        this.adjectives = JSON.adjectives();
        this.modulos = new ArrayList<>();


        if (JSON.modulos() != null) {
            fillSet(modulos, JSON.modulos());
        } else {
            System.out.println("A lista modulos está vazia!");
        }
    }

    private void fillSet(List<modulo> List, List<moduloDTO> JSON) {
        for (int i = 0; i < (JSON.size()); i++) {
            List.add(new modulo(JSON.get(i).name(),JSON.get(i).description(),JSON.get(i).modulosN()));
        }
    }


}

