package Group3.dto;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class DeleteListCartItemDTO {
    private List<UUID> list;
}
