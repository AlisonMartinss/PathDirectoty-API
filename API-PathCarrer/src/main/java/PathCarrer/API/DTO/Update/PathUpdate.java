package PathCarrer.API.DTO.Update;

import PathCarrer.API.DTO.CreatePathStep.onePath;
 /**
 - DTO: Destinado a principio para:

 (a). Fazer atualizações no path;


 - ID: PathID do path a qual pretende-se atualizar.
 - OnePathDTO: Dados da atualização.

 **/

public record PathUpdate(String PathID, onePath onePathDTO) {
}
