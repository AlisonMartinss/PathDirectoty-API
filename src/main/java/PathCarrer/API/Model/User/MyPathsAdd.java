package PathCarrer.API.Model.User;

import java.util.*;

public class MyPathsAdd {
    private HashSet<String> ClassSee;
    private List<ModuleSeen> moduleSeens;

    public void MyPathsAddC(int nModules){
        this.ClassSee = new HashSet<>();
        this.moduleSeens = new ArrayList<>();
        for (int i = 0; i < nModules; i++){
            var newModuleSee = new ModuleSeen();
            this.moduleSeens.add(newModuleSee);
        }
    }

    public void AddSeeClass (boolean X,String IDClass, int indexModule){
        if (X  && indexModule >= 0 && this.moduleSeens.size() - 1 >= indexModule && !this.ClassSee.contains(IDClass)){

            this.moduleSeens.get(indexModule).UpdateClassSee(true,IDClass);
            this.ClassSee.add(IDClass);
        }
        else if (X && this.moduleSeens.size() - 1 < indexModule){

            for (int i = 0; i <= indexModule; i++){
                try{
                    this.moduleSeens.get(i);
                }catch (IndexOutOfBoundsException error) {
                    if (i == indexModule ){
                        var newModule = new ModuleSeen();
                        newModule.UpdateClassSee(true,IDClass);
                        this.moduleSeens.add(newModule);
                        this.ClassSee.add(IDClass);
                    }
                    else {
                        var newModule = new ModuleSeen();
                        this.moduleSeens.add(newModule);
                    }
                }
            }
        }
        else if (ClassSee.contains(IDClass) && !X){
            this.moduleSeens.get(indexModule).UpdateClassSee(false,IDClass);
            this.ClassSee.remove(IDClass);
        }
    }


    public HashSet<String> getClassSee() {
        return ClassSee;
    }
    public List<ModuleSeen> getModuleSeens() {
        return moduleSeens;
    }
}


