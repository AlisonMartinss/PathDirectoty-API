package PathCarrer.API.Model.Path.Comments;

import java.util.ArrayList;
import java.util.List;

public class Comment {

    private String worldID;
    private String comment;
    private List<Integer> address;
    private List<Comment> answers;


    public Comment(String worldID, String comment, List<Integer> address) {
        this.worldID = worldID;
        this.comment = comment;
        this.answers = new ArrayList<>();
        this.address = address;
    }


    public void AnswerAdd (Comment comment){
        this.answers.add(comment);
    }

    // ==== Getters & Setters ==== //




    public String getWorldIDDesvio() {
        return worldID;
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