package PathCarrer.API.Model.Path.Comments;

import java.time.LocalDateTime;

public class Comment {

    private String fatherID;
    private String ID;
    private String userWordID;
    private String comment;

    private int gen;

    public void CommentBody(String comment, String fatherID, String userWordID, int genComment) {
        this.comment = comment;
        this.fatherID = fatherID;
        this.userWordID = userWordID;
        this.ID = ((userWordID + LocalDateTime.now() + comment.substring(0,5)).replace(".",""));
        this.gen = genComment;
    }



    // ==== Getters & Setters ==== //


    public String getFatherID() {
        return fatherID;
    }

    public String getID() {
        return ID;
    }

    public String getUserWordID() {
        return userWordID;
    }

    public String getComment() {
        return comment;
    }

    public int getGen() {
        return gen;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "fatherID='" + fatherID + '\'' +
                ", ID='" + ID + '\'' +
                ", userWordID='" + userWordID + '\'' +
                ", comment='" + comment + '\'' +
                ", gen=" + gen +
                '}';
    }
}