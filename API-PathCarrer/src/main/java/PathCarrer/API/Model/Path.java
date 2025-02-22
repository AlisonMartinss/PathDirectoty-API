package PathCarrer.API.Model;
import PathCarrer.API.DTO.PathDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;




@Document(collection = "Testagem1312")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Path {
    @Id
    private String id;

    private  String title;

    private  String category;

    private  String description;

    private int nClass;

    private List<String> tags;

    private List<adjectives> adjectivesElements;

    private List<modulo> modulos;

    private List <comments> comments;

    private List <comments> forum;

    public Path(PathDTO JSON) {
        this.title = JSON.onePathDTO().title();
        this.category = JSON.onePathDTO().category();
        this.description = JSON.onePathDTO().descPathOver();
        this.tags = new ArrayList<>();
        this.adjectivesElements = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.forum = new ArrayList<>();
        this.modulos = new ArrayList<>();


        if (!JSON.onePathDTO().tags().isEmpty()){
            fillSetTags(JSON.onePathDTO().tags(), tags);
        }

        else {
            System.out.println("Lista de tags vazia");
        }

        if (!JSON.onePathDTO().adjetives().isEmpty()){
            fillSetAdjectives(JSON.onePathDTO().adjetives(), adjectivesElements);
        }

        else {
            System.out.println("Lista de adjetivos vazia");
        }


        if (!JSON.twoPathDTO().ClassList().isEmpty()){
            var novo = new modulo(JSON.twoPathDTO().title(),JSON.twoPathDTO().desc(),JSON.twoPathDTO().ClassList());
            modulos.add(novo);
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






}

