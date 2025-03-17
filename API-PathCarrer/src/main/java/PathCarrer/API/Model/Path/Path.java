package PathCarrer.API.Model.Path;

import PathCarrer.API.DTO.PathDTO;
import PathCarrer.API.DTO.Update.ModuloUpdateDTO;
import PathCarrer.API.DTO.Update.PathUpdate;
import PathCarrer.API.Model.Path.Comments.Comment;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Document(collection = "Testagem1312")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Path {
    @Id
    private String id;

    private  boolean active;

    private ObjectId idAuthor;

    private  String title;

    private  String category;

    private String banner;

    private  String description;

    private int nClass;

    private List<String> tags;

    private List<adjectives> adjectivesElements;

    private List<modulo> modulos;

    private List <Comment> comments;

    private List <Comment> forum;


    public void CreateNewPath(PathDTO JSON,ObjectId worldID) {
        this.idAuthor = worldID;
        this.title = JSON.onePathDTO().title();
        this.category = JSON.onePathDTO().category();
        this.description = JSON.onePathDTO().descPathOver();
        this.banner = JSON.onePathDTO().banner();
        this.tags = new ArrayList<>();
        this.adjectivesElements = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.forum = new ArrayList<>();
        this.modulos = new ArrayList<>();


        if (!JSON.onePathDTO().tags().isEmpty()){
            fillSetTags(JSON.onePathDTO().tags(), tags);
        }

        else {
            System.out.println("Lista de tags vazia");
        }

        if (!JSON.onePathDTO().adjetives().isEmpty()){
            fillSetAdjectives(JSON.onePathDTO().adjetives(), adjectivesElements);
        }

        else {
            System.out.println("Lista de adjetivos vazia");
        }


        if (!JSON.twoPathDTO().ClassList().isEmpty()){
            var novo = new modulo();
            novo.AddNewModulo(JSON.twoPathDTO().title(),JSON.twoPathDTO().desc(),JSON.twoPathDTO().ClassList());
            modulos.add(novo);
        }
        else {
            System.out.println("Lista de aulas vazia");
        }

        this.active = true;

    }
    private void fillSetTags(List<String> JSON, List<String> tagsList) {
        tagsList.addAll(JSON);
    }


    private void UpdatefillSetTags(List<String> JSON, List<String> tagsList) {
        for (int i = 0; i < JSON.size(); i++) {
            tagsList.set(i,JSON.get(i));
        }
    }

    private void fillSetAdjectives(List<String> JSON, List<adjectives> List) {
        for (int i = 0; i < (JSON.size()); i++) {
            List.add(new adjectives(JSON.get(i)));
        }
    }

    private void UpdatefillSetAdjectives(List<String> JSON, List<adjectives> List) {
        for (int i = 0; i < (JSON.size()); i++) {
            List.set(i,new adjectives(JSON.get(i)));
        }
    }

    public void AddNewModulo (ModuloUpdateDTO updateDTO){
            var novo = new modulo();
            novo.AddNewModulo(updateDTO.title(),updateDTO.desc(),updateDTO.ClassList());
            modulos.add(novo);
    }

    public void UpdatePathStats(PathUpdate path){
        this.title = path.onePathDTO().title();
        this.category = path.onePathDTO().category();
        this.description = path.onePathDTO().descPathOver();
        this.banner = path.onePathDTO().banner();
        UpdatefillSetTags(path.onePathDTO().tags(),this.tags);
        UpdatefillSetAdjectives(path.onePathDTO().adjetives(),this.adjectivesElements);
    }

    public  void postComment (String wordID, String comment, List<Integer> address, String profilePIC,String userName){

        // BUSCAR OBJETO COM TAL ADDRES E POSTAR NO SEU CAMPO ANSWERS

       

        /*

        Funcional:

        if (address.isEmpty()){ // Siguinifica que será postado no forum.
            List<Integer> newAddres = List.of(this.comments.size());
            var newComment = new Comment(wordID,comment,newAddres);
            newComment.UpdatepictureProfile(profilePIC,userName);
            this.comments.add(newComment);
        }
        else {
            var commentX = this.comments.get(address.get(0));
            int dimension = 0;

            for (int i = 1; i < address.size(); i++) {
                commentX = commentX.getAnswers().get(address.get(i));
            }
            if (!commentX.getAnswers().isEmpty()){
                dimension = commentX.getAnswers().size();
            }
            address.add(dimension);
            var newComment = new Comment(wordID, comment, address);
            newComment.UpdatepictureProfile(profilePIC,userName);
            commentX.AnswerAdd(newComment);
        }*/
    }


    public  void DeleteComment (List<Integer> address){

        if (address.size() == 1){
            var commentX = this.comments;
            commentX.remove((int) address.get(0));
            if (!(commentX.size() == address.get(0)) && !(commentX.isEmpty())){
                updateAdrresCommetns(commentX,address.get(0));
            }
        }
        else {
             var commentX = this.comments.get(address.get(0));
             for (int i = 1; i < address.size()-1; i++) {
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
            var sunAddres = neighbor.getAddress();
            sunAddres.set(sunAddres.size()-1,i);
            neighbor.setAddress(sunAddres);
        }
    }

    //* ===== Getters & Setters ====== *//

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getnClass() {
        return nClass;
    }

    public void setnClass(int nClass) {
        this.nClass = nClass;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<adjectives> getAdjectivesElements() {
        return adjectivesElements;
    }

    public void setAdjectivesElements(List<adjectives> adjetives) {
        this.adjectivesElements = adjectivesElements;
    }

    public List<modulo> getModulos() {
        return modulos;
    }

    public void setModulos(List<modulo> modulos) {
        this.modulos = modulos;
    }

    public List<Comment> getComments() {
        return this.comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Comment> getForum() {
        return forum;
    }

    public void setForum(List<Comment> forum) {
        this.forum = forum;
    }


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public ObjectId getIdAuthor() {
        return idAuthor;
    }
}

