package PathCarrer.API.Model;

import PathCarrer.API.Model.Path.tasks;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class weekDays {
    private List<tasks> monday;
    private List<tasks> tuesday;
    private List<tasks> wednesday;
    private List<tasks> thursday;
    private List<tasks> griday;
    private List<tasks> saturday;
    private List<tasks> Sunday;
    private List<tasks> all;


}