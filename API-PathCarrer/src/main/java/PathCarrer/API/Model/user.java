package PathCarrer.API.Model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "user")
@Getter
@Setter
public class user {
    @Id
    private String username;
    private String passworld;
    private String palavraUnica;
    private List<paths> pathsList;
    private List<weekDays> weekPlan;
}