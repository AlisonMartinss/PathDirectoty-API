package PathCarrer.API.Model.User;

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
    private String Category;
    private String img;
    private List<ModuleSeen> moduleSeenList;
}
