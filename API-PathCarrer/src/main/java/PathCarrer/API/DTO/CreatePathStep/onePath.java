package PathCarrer.API.DTO.CreatePathStep;

import java.util.List;

public record onePath (String title, String category, List<String> adjetives, List<String> tags, String descPathOver, String banner) {}
