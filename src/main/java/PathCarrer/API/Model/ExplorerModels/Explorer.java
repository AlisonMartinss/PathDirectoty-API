package PathCarrer.API.Model.ExplorerModels;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import java.util.HashSet;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Explorer {
    @Id
    private String id;
    private  String title;
    private  String category;
    private String banner;
    private  int everAdd;
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

    public HashSet<String> getClassPresent() {
        return ClassPresent;
    }

    public int getEverAdd() {
        return everAdd;
    }

    public String getBanner() {
        return banner;
    }
}
