package PathCarrer.API.Model;


import org.springframework.data.mongodb.core.mapping.Field;
import PathCarrer.API.DTO.CreatePathStep.threePath;


public class Aulas {

    private String title;
    private String description;
    private String link;

    public Aulas(threePath JSON) {
        this.title = JSON.title();
        this.description = JSON.description();
        this.link = JSON.link();
    }

}
