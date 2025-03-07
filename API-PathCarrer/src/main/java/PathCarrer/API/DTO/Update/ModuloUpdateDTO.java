package PathCarrer.API.DTO.Update;

import PathCarrer.API.DTO.CreatePathStep.threePath;

import java.util.List;

 /**
 - DTO: Destinado a principio para:

     (a). Adicionar um novo modulo no path;
     (b). Substituir um modulo;

     - ID: PathID do path a qual contem o modulo que pretendemos mudar.
     - Title: Titulo do novo modulo.
     - Desc: Descrição do novo modulo.
     - ClassList: Lista de aulas que irá compor o novo modulo.
     - nameModulo: Titulo do modulo que pretendemos substituir.(b)
 **/

public record ModuloUpdateDTO(String id, String title, String desc, List<threePath> ClassList, String nameModulo) {
}
