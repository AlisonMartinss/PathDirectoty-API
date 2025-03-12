package PathCarrer.API.DTO.Update;

import PathCarrer.API.DTO.CreatePathStep.threePath;

import java.util.List;

 /**
 - DTO: Destinado a principio para:

     (a). Adicionar um novo modulo no path;
     (b). Substituir um modulo;
     (c). Exluir um modulo

     - ID: PathID do path a qual contem o modulo que pretendemos mudar.
     - Title: Titulo do novo modulo.
     - Desc: Descrição do novo modulo.
     - ClassList: Lista de aulas que irá compor o novo modulo.
     - indexMoudulo: Indice do modulo que pretendemos substituir/excluir.(b);(c)
 **/

public record ModuloUpdateDTO(String PathID, String title, String desc, List<threePath> ClassList, int indexMoudulo) {
}
