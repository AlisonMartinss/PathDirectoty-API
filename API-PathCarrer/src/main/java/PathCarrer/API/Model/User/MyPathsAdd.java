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
        if (X && !this.moduleSeens.get(indexModule).getClassSeens().isEmpty()){ // Adicionar aula ; modulo está presente
            this.moduleSeens.get(indexModule).UpdateClassSee(true,IDClass);
            this.ClassSee.add(IDClass);
        }
        else if (X && this.moduleSeens.get(indexModule).getClassSeens().isEmpty()){
            var newModule = new ModuleSeen();
            newModule.UpdateClassSee(true,IDClass);
            this.moduleSeens.set(indexModule,newModule);
            this.ClassSee.add(IDClass);
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


