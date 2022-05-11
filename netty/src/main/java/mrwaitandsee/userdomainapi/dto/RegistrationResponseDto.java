package mrwaitandsee.userdomainapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegistrationResponseDto {
    private Boolean success;
    private UserRegistrationResponseDto message;
}
