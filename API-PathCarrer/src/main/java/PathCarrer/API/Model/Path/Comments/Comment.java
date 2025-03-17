package PathCarrer.API.Model.Path.Comments;

import java.util.ArrayList;
import java.util.List;

public class Comment {

    private String worldID;
    private String userName;
    private String PictureProfile;
    private String comment;
    private List<Comment> answers;
    private List<Integer> address;

    public Comment(String worldID, String comment, List<Integer> address) {
        this.worldID = worldID;
        this.comment = comment;
        this.answers = new ArrayList<>();
        this.address = address;
    }

    public void UpdatepictureProfile (String profilePic, String userName){
        this.PictureProfile = profilePic;
        this.userName = userName;
    }

    public void AnswerAdd (Comment comment){
        this.answers.add(comment);
    }

    // ==== Getters & Setters ==== //


    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWorldIDDesvio() {
        return worldID;
    }

    public String getPictureProfile() {
        return PictureProfile;
    }

    public String getComment() {
        return comment;
    }

    public List<Integer> getAddress() {
        return address;
    }

    public void setAddress(List<Integer> address) {
        this.address = address;
    }

    public List<Comment> getAnswers() {
        return answers;
    }
}
