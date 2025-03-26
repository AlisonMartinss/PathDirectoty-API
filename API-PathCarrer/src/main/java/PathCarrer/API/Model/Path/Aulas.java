package PathCarrer.API.Model.Path;


import PathCarrer.API.DTO.CreatePathStep.threePath;
import lombok.*;
import org.springframework.data.annotation.PersistenceConstructor;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Aulas {
    private String ID;
    private String title;
    private String link;
    private String description;


    public void ClassCreate(String title, String description, String link) {
        this.ID = title + (LocalDateTime.now().toString()).replace(".","");
        this.title = title;
        this.description = description;
        this.link = link;
    }


    public void ClassUpdate(threePath threePath){
        this.title = threePath.title();
        this.description = threePath.description();
        this.link = threePath.link();
    }

    @Override
    public String toString() {
        return "Aulas{" +
                "ID='" + ID + '\'' +
                ", title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    // ==== Getters & Setters ==== //
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

    public String getID() {
        return ID;
    }
}
