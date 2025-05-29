package Group3.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ChangeCartItemRequestDTO{
    @NotNull
    private UUID cartItemId;

    private int quantity;
}
