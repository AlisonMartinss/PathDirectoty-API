package PathCarrer.API.DTO.InteractionsDTO;

import java.util.List;

public record InteractionDTO(String PathID, String userID, String comment, int commentIndex, int answerIndex, List<Integer> address) {
}
