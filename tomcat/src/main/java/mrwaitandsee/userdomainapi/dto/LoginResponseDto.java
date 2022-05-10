package mrwaitandsee.userdomainapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponseDto {
    private Boolean success;
    private LoginEntityResponseDto message;
}
