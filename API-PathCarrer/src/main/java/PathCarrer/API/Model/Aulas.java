package PathCarrer.API.Model;


import PathCarrer.API.DTO.CreatePathStep.threePath;
import lombok.*;
import org.springframework.data.annotation.PersistenceConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Aulas {

    private String title;
    private String description;
    private String link;

    public void ClassCreate(String title, String description, String link) {
        this.title = title;
        this.description = description;
        this.link = link;
    }


    public void ClassUpdate(threePath threePath){
        this.title = threePath.title();
        this.description = threePath.description();
        this.link = threePath.link();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
