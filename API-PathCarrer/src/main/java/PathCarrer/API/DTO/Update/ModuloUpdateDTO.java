package PathCarrer.API.DTO.Update;

import PathCarrer.API.DTO.CreatePathStep.threePath;

import java.util.List;

 /**
 - DTO: Destinado a principio para:

     (a). Adicionar um novo moduloCreate no path;
     (b). Substituir um moduloCreate;
     (c). Exluir um moduloCreate

     - ID: PathID do path a qual contem o moduloCreate que pretendemos mudar.
     - Title: Titulo do novo moduloCreate.
     - Desc: Descrição do novo moduloCreate.
     - ClassList: Lista de aulas que irá compor o novo moduloCreate.
     - indexMoudulo: Indice do moduloCreate que pretendemos substituir/excluir.(b);(c)
 **/

public record ModuloUpdateDTO(String PathID, String title, String desc, List<threePath> ClassList, int indexMoudulo) {
}
