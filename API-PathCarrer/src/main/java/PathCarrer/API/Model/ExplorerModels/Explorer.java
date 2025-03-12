package PathCarrer.API.Model.ExplorerModels;

import PathCarrer.API.Model.adjectives;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Explorer {
    private String id;
    private String title;
    private String category;
    private List<adjectives> adjectivesElements;

    // ==== Getters & Setters ==== //


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

    public List<adjectives> getAdjectivesElements() {
        return adjectivesElements;
    }

    public void setAdjectivesElements(List<adjectives> adjectivesElements) {
        this.adjectivesElements = adjectivesElements;
    }
}
