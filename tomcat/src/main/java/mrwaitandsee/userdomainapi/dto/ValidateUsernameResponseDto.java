package mrwaitandsee.userdomainapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ValidateUsernameResponseDto {
    private Boolean success;
    private Boolean message;
}
