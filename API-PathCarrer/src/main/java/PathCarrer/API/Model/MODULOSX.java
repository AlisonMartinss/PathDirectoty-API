package PathCarrer.API.Model;

import PathCarrer.API.DTO.AulasDTO;
import java.util.ArrayList;
import java.util.List;

public class MODULOSX {
    private List<Aulas> modulosH; //Lista Aula

    public MODULOSX(List<AulasDTO> modulosA) {
        this.modulosH = new ArrayList<>();
        fillset(2,modulosA,modulosH);
    }



   private List<Aulas> fillset (int n, List<AulasDTO> JSON, List<Aulas> List){
        for (int i = 0; i < n;i++){
            List.add(new Aulas(JSON.get(i)));
        }

        return List;}


}
