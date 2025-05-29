package Group3.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import Group3.Enum.PayMethod;
import Group3.Enum.StateTransaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseDTO {
    private UUID id;
    private BigDecimal amount;
    private StateTransaction state;
    private PayMethod payMethod;
    private LocalDateTime time;
    private UUID order;
}
