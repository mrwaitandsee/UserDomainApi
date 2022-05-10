package mrwaitandsee.userdomainapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginEntityResponseDto {
    private String accessToken;
}
