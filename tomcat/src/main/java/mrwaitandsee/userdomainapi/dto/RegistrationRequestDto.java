package mrwaitandsee.userdomainapi.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Validated
public class RegistrationRequestDto {
    @NotBlank
    @NotNull
    private String name;
    @NotBlank
    @NotNull
    private String password;

    @NotNull
    private List<String> project;
}
