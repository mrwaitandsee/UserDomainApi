package mrwaitandsee.userdomainapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {
    private String name;
    private String password;
}
