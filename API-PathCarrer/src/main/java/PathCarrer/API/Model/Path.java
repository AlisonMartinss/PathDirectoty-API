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

    private List<comments> forum;

    public Path(PathDTO JSON) {
        this.title = JSON.onePathDTO().title();
        this.category = JSON.onePathDTO().category();
        this.description = JSON.onePathDTO().descPathOver();
        this.adjectivesElements = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.forum = new ArrayList<>();
        this.modulos = new ArrayList<>();

        fillSetTags(JSON.onePathDTO(), tags);
        fillSetAdjectives(JSON.onePathDTO().adjetives(), adjectivesElements);
        fillSetModulo(JSON.twoPathDTO(), modulos);

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

    private void fillSetTags(onePath JSON, List<String> tagsList) {
        for (int i = 0; i < tags.size(); i++) {
            tagsList.add(JSON.tags().get(i));
        }
    }


}

