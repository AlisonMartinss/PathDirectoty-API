package PathCarrer.API.Model;

import PathCarrer.API.DTO.AulasDTO;
import PathCarrer.API.DTO.moduloDTO;
import org.springframework.data.mongodb.core.mapping.Field;


public class Aulas {
    @Field("title")
    private String title;

    @Field("description")
    private String description;
    @Field("link")
    private String link;

    public Aulas(AulasDTO JSON) {
        this.title = JSON.title();
        this.description = JSON.description();
        this.link = JSON.link();
    }

    @Override
    public String toString() {
        return "Aulas{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
