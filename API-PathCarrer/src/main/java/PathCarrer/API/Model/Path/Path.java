package PathCarrer.API.Model.Path;

import PathCarrer.API.DTO.PathDTO;
import PathCarrer.API.DTO.Update.ModuloUpdateDTO;
import PathCarrer.API.DTO.Update.PathUpdate;
import PathCarrer.API.ExeptionsClasses.PathAspectsUnexpected;
import PathCarrer.API.Model.Path.Comments.Comment;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@Document(collection = "Paths")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Path {
    @Id
    private String id;

    private  boolean active;

    private int currentAdd;

    private int everAdd;

    private String idAuthor;

    private  String title;

    private  String category;

    private String banner;

    private  String description;

    private List<String> tags;

    private List<modulo> modulos;

    private HashSet<String> ClassPresent;



    public void CreateNewPath(PathDTO JSON,String worldID) {
        this.idAuthor = worldID;
        this.title = JSON.onePathDTO().title();
        this.category = JSON.onePathDTO().category();
        this.description = JSON.onePathDTO().descPathOver();
        this.banner = JSON.onePathDTO().banner();
        this.tags = new ArrayList<>();
        this.modulos = new ArrayList<>();
        this.ClassPresent = new HashSet<>();
        this.currentAdd = 0;
        this.everAdd = 0;


        if (!JSON.onePathDTO().tags().isEmpty()){
            fillSetTags(JSON.onePathDTO().tags(), tags);
        }

        if (!JSON.twoPathDTO().ClassList().isEmpty()){
            var novo = new modulo();
            novo.moduloCreate(JSON.twoPathDTO().title(),JSON.twoPathDTO().desc(),JSON.twoPathDTO().ClassList());
            modulos.add(novo);
            for (int i = 0; i < novo.getModulocontent().size();i++){
                this.ClassPresent.add(novo.getModulocontent().get(i).getID());
            }
        }
        else {
            throw new PathAspectsUnexpected("Erro ao tentar criar Path. Lista de aulas vazia!");
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

    public void AddNewModulo (ModuloUpdateDTO updateDTO){
        var novo = new modulo();
        novo.moduloCreate(updateDTO.title(),updateDTO.desc(),updateDTO.ClassList());
        modulos.add(novo);
        for (int i = 0; i < novo.getModulocontent().size();i++){
            this.ClassPresent.add(novo.getModulocontent().get(i).getID());
        }
    }

    public void UpdatePathStats(PathUpdate path){
        this.title = path.onePathDTO().title();
        this.category = path.onePathDTO().category();
        this.description = path.onePathDTO().descPathOver();
        this.banner = path.onePathDTO().banner();
        UpdatefillSetTags(path.onePathDTO().tags(),this.tags);
    }

    public void deleteModule (int indexModule){
        var ClassModule = this.modulos.get(indexModule).getModulocontent();
        for (int i = 0; i < ClassModule.size(); i++){
            this.ClassPresent.remove(ClassModule.get(i).getID());
        }
        this.modulos.remove(indexModule);
    }

    public void UpdatenClass (boolean i,String IDClass){
        if (!i){
         this.ClassPresent.remove(IDClass);
        }
        else {
            this.ClassPresent.add(IDClass);
        }
    }

    public boolean UpdatePathCount(boolean x){
        if (x){
          this.currentAdd++;
          this.everAdd ++;
          return true;
        }
        else {
            this.currentAdd--;
            return false;
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


    public String getDescription() {
        return description;
    }

    public List<modulo> getModulos() {
        return modulos;
    }

    public String getIdAuthor() {
        return idAuthor;
    }

    public int getCurrentAdd() {
        return currentAdd;
    }

    public int getEverAdd() {
        return everAdd;
    }

    public String getCategory() {
        return category;
    }
}