package PathCarrer.API.Model.Path.Comments;


import java.util.HashMap;


public class ZComments {

    //A propria classe representa a GEN

    private HashMap<String, HashMap<String,Comment>> FatherList;

    public void ZCommentsBurn(String FatherID) {
        this.FatherList = new HashMap<>();
        this.FatherList.put(FatherID,new HashMap<String,Comment>());
    }

    public void AddNewFather(String FatherID) {
        this.FatherList.put(FatherID,new HashMap<String,Comment>());
    }


    public HashMap<String,HashMap<String,Comment>> GetFatherList(){
        return FatherList;
    }

    public HashMap<String, HashMap<String, Comment>> getFatherList() {
        return FatherList;
    }

    @Override
    public String toString() {
        return "ZComments{" +
                "FatherList=" + FatherList +
                '}';
    }
}
