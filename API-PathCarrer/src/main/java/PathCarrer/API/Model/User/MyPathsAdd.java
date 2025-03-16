package PathCarrer.API.Model.User;

import PathCarrer.API.Model.Path.Path;

import java.util.List;

/**

 MOTIVAÇÃO: TER EM MÃOS OS PATH'S ADICIONADO PELOP USUARIO.

   - img: LOGO DO PATH.

   - moduleSeenList: SABER QUAIS MODULOS/AULAS JÁ FORAM ASSISTIDAS. ESSENCIALMENTE É UMA
     LISTA DE UM MODEL DE MODULOS.(MODEL DE MODULO DIFERENTE DO QUE EM 'MODEL.modulos').
     ESSECIALMENTE, SE O MODULO ESTÁ NA LISTA, É PQ JÁ FOI OU ESTÁ SENDO ASSISTIDO.

 **/
public class MyPathsAdd {
    private String pathID;
    private String title;
    private String category;
    private String img;
    private List<ModuleSeen> moduleSeenList;

    public void AddPath (Path newPath){
        this.pathID = newPath.getId();
        this.title = newPath.getTitle();
        this.category = newPath.getCategory();
    }

    /* ==== Getters & Setters ==== */

    public String getPathID() {
        return pathID;
    }

    public void setPathID(String pathID) {
        this.pathID = pathID;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<ModuleSeen> getModuleSeenList() {
        return moduleSeenList;
    }

    public void setModuleSeenList(List<ModuleSeen> moduleSeenList) {
        this.moduleSeenList = moduleSeenList;
    }
}
