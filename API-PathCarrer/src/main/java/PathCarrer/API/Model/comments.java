package PathCarrer.API.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class comments {
    private String userId;
    private String comment;
    private int date;
    private int timeSigned;
}
