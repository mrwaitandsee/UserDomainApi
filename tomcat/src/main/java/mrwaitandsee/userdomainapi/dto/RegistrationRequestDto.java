package mrwaitandsee.userdomainapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationRequestDto {
    private String name;
    private String password;
    private String[] project;
}
