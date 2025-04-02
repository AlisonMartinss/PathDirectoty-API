package PathCarrer.API.Model.Path;

import PathCarrer.API.DTO.CreatePathStep.threePath;
import PathCarrer.API.Model.Path.Comments.Comment;
import PathCarrer.API.Model.Path.Comments.ZComments;
import PathCarrer.API.Model.Response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder

public class modulo {
    private String ID;
    private String name;
    private String description;
    private List <Aulas> modulocontent;
    private HashSet<String> ClassPresent;
    private List<ZComments> comments;
    private int qtdAulasModulo;

    public void moduloCreate(String name, String  description, List<threePath> modulocontent) {

        this.ID = name + (LocalDateTime.now().toString()).replace(".","");
        this.modulocontent = new ArrayList<>();
        this.ClassPresent = new HashSet<>();

        this.name = name;
        this.description = description;

        var presentation = new Aulas();
        presentation.ClassCreate("Apresentação",description,null);
        this.modulocontent.add(presentation);
        this.qtdAulasModulo++;

        this.qtdAulasModulo = fillSet(modulocontent,this.modulocontent);
        this.comments = new ArrayList<>();
        var ForumPostGen = new ZComments();
        ForumPostGen.ZCommentsBurn("forumPost");
        this.comments.add(ForumPostGen);
    }

    private int fillSet(List<threePath> ClassListJSON, List<Aulas> List) {
        for (int i = 0; i < ClassListJSON.size(); i++) {
            var ClassNew = new Aulas();
            ClassNew.ClassCreate(ClassListJSON.get(i).title(),ClassListJSON.get(i).description(),ClassListJSON.get(i).link());
            List.add(ClassNew);
            this.ClassPresent.add(ClassListJSON.get(i).title());
            qtdAulasModulo++;
        }

        return this.qtdAulasModulo;
    }

    public void UpdateNewClass (threePath ClassInfo){
        var newClass = new Aulas();
        newClass.ClassCreate(ClassInfo.title(),ClassInfo.description(),ClassInfo.link());
        this.modulocontent.add(newClass);
        this.ClassPresent.add(newClass.getID());
    }

    public void DeleteClass (int indexClass){
        var IDClass = this.modulocontent.get(indexClass).getID();
        this.ClassPresent.remove(IDClass);
        this.modulocontent.remove(indexClass);
    }



    public  void postComment (int gen,String fatherID,String commentCore,String UserID){
        if (gen == 0){
            var newComment = new Comment();
            newComment.CommentBody(commentCore,"commnetONforum0", UserID, 0);
            this.comments.get(gen).GetFatherList().get("forumPost").put(newComment.getID(),newComment);
        }
        else {
            var newComment = new Comment();
            newComment.CommentBody(commentCore,fatherID,UserID,gen);
            try {
                this.comments.get(gen).GetFatherList().get(fatherID).put(newComment.getID(),newComment);
            }
            catch (IndexOutOfBoundsException exception) {
               var newGen = new ZComments();
               newGen.ZCommentsBurn(fatherID);
               this.comments.add(newGen);
               this.comments.get(gen).GetFatherList().get(fatherID).put(newComment.getID(),newComment);
            }
        }
    }

    public  String DeleteComment (int gen,String fatherID,String commentID){
        if (gen == 0){
           // this.comments.get(1).GetFatherList().get(commentID).clear();
            this.comments.get(gen).GetFatherList().get("forumPost").remove(commentID);
            return "Comentario excluido com Sucesso [POST]";
        }
        else {
            try {
              //  this.comments.get(gen+1).GetFatherList().get(commentID).clear();
                this.comments.get(gen).GetFatherList().get(fatherID).remove(commentID);

                return "Comentario excluido com Sucesso [!POST]";
            } catch (IndexOutOfBoundsException exception) {
               return "Erro: Índice inválido no array." ;
            }
        }
    }

    public Response<HashMap<String,Comment>> ElementCommentInfo (int gen, String CommentID){
        try {
         var result = this.comments.get(gen).GetFatherList().get(CommentID);
         return  new Response<>(result);
        }
        catch (NullPointerException e){
            return  new Response<>("Não existem respostas para esse comentário");
        }
    }


    //* ===== Getters & Setters ====== *//


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Aulas> getModulocontent() {
        return modulocontent;
    }

    public void setModulocontent(List<Aulas> modulocontent) {
        this.modulocontent = modulocontent;
    }

    public List<ZComments> getComments() {
        return comments;
    }

    public int getQtdAulasModulo() {
        return qtdAulasModulo;
    }

    public void setQtdAulasModulo(int qtdAulasModulo) {
        this.qtdAulasModulo = qtdAulasModulo;
    }


}
