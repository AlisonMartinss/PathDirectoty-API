package PathCarrer.API.DTO.InteractionsDTO;

import PathCarrer.API.Model.Path.Comments.Comment;

import java.util.List;

public record InteractionDTO(String PathID, String userName, String comment, int Gen, String fatherID, int indexModule, String commentID) {
}
