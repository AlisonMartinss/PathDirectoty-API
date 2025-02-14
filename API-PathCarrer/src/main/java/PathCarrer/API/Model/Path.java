package PathCarrer.API.Model;
import PathCarrer.API.DTO.CreatePathStep.onePath;
import PathCarrer.API.DTO.moduloDTO;
import PathCarrer.API.DTO.PathDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import PathCarrer.API.DTO.CreatePathStep.twoPath;



@Document(collection = "Testagem1312")
@Getter
@Setter
public class Path {
    @Id
    private String id;

    private  String title;

    private  String category;

    private  String description;

    private List<String> tags;

    private List<adjectives> adjectivesElements;

    private List<modulo> modulos;

    private List <comments> comments;

    private List <comments> forum;

    @Override
    public String toString() {
        return "Path{" +
                "title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Path(PathDTO JSON) {
        this.title = JSON.onePathDTO().title();
        this.category = JSON.onePathDTO().category();
        this.description = JSON.onePathDTO().descPathOver();
        this.tags = new ArrayList<>();
        this.adjectivesElements = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.forum = new ArrayList<>();
        this.modulos = new ArrayList<>();


        if (JSON.onePathDTO().tags().size() != 0){
            fillSetTags(JSON.onePathDTO().tags(), tags);
        }

        else {
            System.out.println("Lista de tags vazia");
        }

        if (JSON.onePathDTO().adjetives().size() != 0){
            fillSetAdjectives(JSON.onePathDTO().adjetives(), adjectivesElements);
        }

        else {
            System.out.println("Lista de adjetivos vazia");
        }


        if (JSON.twoPathDTO().ClassList().size() != 0){
            modulos.add(new modulo(JSON.twoPathDTO()));
        }

        else {
            System.out.println("Lista de aulas vazia");
        }

    }

    private void fillSetTags(List<String> JSON, List<String> tagsList) {
        for (int i = 0; i < JSON.size(); i++) {
            tagsList.add(JSON.get(i));
        }
    }

    private void fillSetAdjectives(List<String> JSON, List<adjectives> List) {
        for (int i = 0; i < (JSON.size()); i++) {
            List.add(new adjectives(JSON.get(i)));
        }
    }

    private void fillSetModulo(twoPath JSON, List<modulo> List) {
        for (int i = 0; i < 1; i++) {
            List.add(new modulo(JSON));
        }
    }




}

