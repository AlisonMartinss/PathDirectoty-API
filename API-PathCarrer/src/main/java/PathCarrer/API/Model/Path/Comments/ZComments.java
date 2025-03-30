package PathCarrer.API.Model.Path.Comments;


import java.util.HashMap;
import java.util.HashSet;


public class ZComments {

    private HashMap<String, HashMap<String,Comment>> FatherList;

    public void ZCommentsBurn(String fatherID) {
        this.FatherList = new HashMap<>();
        this.FatherList.put(fatherID,new HashMap<String,Comment>());
    }

    public HashMap<String,HashMap<String,Comment>> GetFatherList() {
        return FatherList;
    }

    public HashMap<String, HashMap<String,Comment>> getFatherList() {
        return FatherList;
    }
}
