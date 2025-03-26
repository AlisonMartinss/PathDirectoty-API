package PathCarrer.API.Model.Path;

import PathCarrer.API.DTO.CreatePathStep.threePath;
import PathCarrer.API.Model.Path.Comments.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private List <Comment> comments;
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



    public  void postComment (String wordID, String comment, List<Integer> address){

        Funcional:

        if (!(address.size() > 1)){ // Siguinifica que será postado no forum.
            List<Integer> newAddres = List.of(address.get(0),this.comments.size());
            var newComment = new Comment(wordID,comment,newAddres);
            this.comments.add(newComment);
        }
        else {
            var commentX = this.comments.get(address.get(1));
            int dimension = 0;

            for (int i = 2; i < address.size(); i++) {
                commentX = commentX.getAnswers().get(address.get(i));
            }
            if (!commentX.getAnswers().isEmpty()){
                dimension = commentX.getAnswers().size();
            }
            address.add(dimension);
            var newComment = new Comment(wordID, comment, address);
            commentX.AnswerAdd(newComment);
        }
    }

    public  void DeleteComment (List<Integer> address){

        if (!(address.size() > 2)){
            var commentX = this.comments;
            commentX.remove((int) address.get(1));
            if (!(commentX.size() == address.get(1)) && !(commentX.isEmpty())){
                updateAdrresCommetns(commentX,address.get(0));
            }
        }
        else {
            var commentX = this.comments.get(address.get(1));
            for (int i = 2; i < address.size()-1; i++) {
                commentX = commentX.getAnswers().get(address.get(i)); // objeto pai
            }

            int a = address.get(address.size()-1); // cordenada do proprio
            commentX.getAnswers().remove(a);

            if (!(commentX.getAnswers().size() == a) && !(commentX.getAnswers().isEmpty())){
                updateAdrresCommetns(commentX.getAnswers(),a);
            }
        }
    }

    private void updateAdrresCommetns(List<Comment> commentlist,int indexY){
        // Se ta aqui eu se que: não é o ultimo nem o unico
        for(int i = (indexY); i < commentlist.size(); i++){
            var neighbor  = commentlist.get(i); // pegou elemento
            var sunAddres = neighbor.getAddress(); // List
            sunAddres.set(sunAddres.size()-1,i); // redefine endereço
            neighbor.setAddress(sunAddres); // seta endereço
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

    public List<Comment> getComments() {
        return comments;
    }

    public int getQtdAulasModulo() {
        return qtdAulasModulo;
    }

    public void setQtdAulasModulo(int qtdAulasModulo) {
        this.qtdAulasModulo = qtdAulasModulo;
    }


}
