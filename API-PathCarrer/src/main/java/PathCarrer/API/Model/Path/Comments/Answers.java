package PathCarrer.API.Model.Path.Comments;

public class Answers {
    private String userId;
    private String comment;

    public Answers(String userId, String comment) {
        this.userId = userId;
        this.comment = comment;
    }

    // ==== Getters & Setters ====//

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
