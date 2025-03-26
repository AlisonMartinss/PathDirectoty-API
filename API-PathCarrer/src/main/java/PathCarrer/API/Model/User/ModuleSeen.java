package PathCarrer.API.Model.User;

import java.util.HashSet;

/**

 MOTIVAÇÃO: SABER QUAIS MODULOS/AULAS JÁ FORAM ASSISTIDAS. ESSENCIALMENTE É UMA
 LISTA DE UM MODEL DE MODULOS.(MODEL DE MODULO DIFERENTE DO QUE EM 'MODEL.modulos')

   - classSeens: LISTA DE AULAS JÁ ASSISTIDAS DO MODULO EM QUESTÃO. ESSECIALMENTE, SE JÁ
   ESTÁ PRESENTE NA LISTA É PORQUE JÁ FOI ASSISTIDO.

 **/

public class ModuleSeen {

    private HashSet<String> classSeens;

    public ModuleSeen() {
        this.classSeens = new HashSet<>();
    }

    public void UpdateClassSee(boolean X , String classTitle){
        if (X){
            classSeens.add(classTitle);
        }
        else if (classSeens.contains(classTitle)){
            this.classSeens.remove(classTitle);
        }
    }

    // ==== Getters & Setters ==== //

    public HashSet<String> getClassSeens() {
        return classSeens;
    }
}
