package Group3.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfoResponseDTO {
    private String name;
    private String email;
    private LocalDate dateOfBirth;
    private String address;
    private String phone;
    private String avatar;
}
