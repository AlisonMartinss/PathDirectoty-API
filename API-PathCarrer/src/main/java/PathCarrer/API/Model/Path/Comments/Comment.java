package PathCarrer.API.Model.Path.Comments;

import java.util.ArrayList;
import java.util.List;

public class Comment {
    private String userId;
    private String comment;
    private List<Comment> answers;
    private List<Integer> address;

    public Comment(String userId, String comment, List<Integer> address) {
        this.userId = userId;
        this.comment = comment;
        this.answers = new ArrayList<>();
        this.address = new ArrayList<>();
        this.address = address;
    }

    public void AnswerAdd (Comment comment){
        this.answers.add(comment);
    }

    // ==== Getters & Setters ==== //


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
