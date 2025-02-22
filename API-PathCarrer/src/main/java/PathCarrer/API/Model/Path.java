package PathCarrer.API.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;




@Document(collection = "Testagem1312")
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


    public Path(String title, String category, String description) {

        this.title = title;
        this.category = category;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getnClass() {
        return nClass;
    }

    public void setnClass(int nClass) {
        this.nClass = nClass;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<adjectives> getAdjectivesElements() {
        return adjectivesElements;
    }

    public void setAdjectivesElements(List<adjectives> adjetives) {
        this.adjectivesElements = adjectivesElements;
    }

    public List<modulo> getModulos() {
        return modulos;
    }

    public void setModulos(List<modulo> modulos) {
        this.modulos = modulos;
    }

    public List<PathCarrer.API.Model.comments> getComments() {
        return comments;
    }

    public void setComments(List<PathCarrer.API.Model.comments> comments) {
        this.comments = comments;
    }

    public List<PathCarrer.API.Model.comments> getForum() {
        return forum;
    }

    public void setForum(List<PathCarrer.API.Model.comments> forum) {
        this.forum = forum;
    }
/*public Path(PathDTO JSON) {
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
    }*/






}

