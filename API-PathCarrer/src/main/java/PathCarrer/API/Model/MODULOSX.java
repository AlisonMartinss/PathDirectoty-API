package PathCarrer.API.Model;

import PathCarrer.API.DTO.AulasDTO;
import java.util.ArrayList;
import java.util.List;

public class MODULOSX {
    private List<Aulas> modulosH; //Lista Aula

    public MODULOSX(List<AulasDTO> modulosA) {
        this.modulosH = new ArrayList<>();
        fillset(3,modulosA,modulosH);
    }



//   private void fillset (int n, List<AulasDTO> JSON, List<Aulas> List){
//        for (int i = 1; i < n;i++){
//            List.add(new Aulas(JSON.get(i)));
//        }
//    }

    private void fillset(int n, List<AulasDTO> JSON, List<Aulas> List) {
        for (int i = 1; i < Math.min(n, JSON.size()); i++) { // Garante que o loop não exceda JSON.size()
            List.add(new Aulas(JSON.get(i)));
            System.out.println("GPT");
        }
    }



}
