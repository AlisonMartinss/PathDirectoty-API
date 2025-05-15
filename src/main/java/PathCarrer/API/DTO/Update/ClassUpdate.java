package PathCarrer.API.DTO.Update;
import PathCarrer.API.DTO.CreatePathStep.threePath;
/**
 - DTO: Destinado a principio para:

     (a). Atualizar uma aula;
     (b). Adicionar uma nova aula;

     - ID: PathID do path a qual contem a aula que pretendemos mudar.
     - indexModule: indice do mudo que pretendemos mudar
     - nameModulo: Titulo do moduloCreate da aula que pretendemos mudar.
     - indexClass: indice da aula que pretendemos mudar.
     - threePath:  objedo contendo: titulo, link, desc;  Que farão a substituição.

 **/
public record ClassUpdate(String PathID, int indexModule, String nameModulo, int indexClass, threePath threePath) {
}
