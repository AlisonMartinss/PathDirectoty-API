package PathCarrer.API.Model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.PersistenceConstructor;


@NoArgsConstructor  // Adicione essa anotação do Lombok
@Getter
@Setter
public class Aulas {

    private String title;
    private String description;
    private String link;

    @PersistenceConstructor
    public Aulas(String title, String description, String link) {
        this.title = title;
        this.description = description;
        this.link = link;
    }

}
