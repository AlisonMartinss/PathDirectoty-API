package PathCarrer.API.Model.User;

import java.util.List;
 /**

 MOTIVAÇÃO: SABER QUAIS MODULOS/AULAS JÁ FORAM ASSISTIDAS. ESSENCIALMENTE É UMA
 LISTA DE UM MODEL DE MODULOS.(MODEL DE MODULO DIFERENTE DO QUE EM 'MODEL.modulos')

   - classSeens: LISTA DE AULAS JÁ ASSISTIDAS DO MODULO EM QUESTÃO. ESSECIALMENTE, SE JÁ
   ESTÁ PRESENTE NA LISTA É PORQUE JÁ FOI ASSISTIDO.

 **/

public class ModuleSeen {
    String name;
    List<ClassSeen> classSeens;
}
