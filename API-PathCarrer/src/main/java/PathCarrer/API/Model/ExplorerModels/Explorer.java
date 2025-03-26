package PathCarrer.API.Model.ExplorerModels;

import PathCarrer.API.Model.Path.Aulas;
import PathCarrer.API.Model.Path.adjectives;
import PathCarrer.API.Model.Path.modulo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.HashSet;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Explorer {
    @Id
    private String id;

    private  String title;

    private  String category;

    private List<adjectives> adjectivesElements;

    private HashSet<String> ClassPresent;

    // ==== Getters & Setters ==== //


    public String getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }


    public List<adjectives> getAdjectivesElements() {
        return adjectivesElements;
    }

    public HashSet<String> getClassPresent() {
        return ClassPresent;
    }
}
