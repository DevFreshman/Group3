package Group3.dto;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class OrderRequestDTO {
    private List<UUID> list;
}
