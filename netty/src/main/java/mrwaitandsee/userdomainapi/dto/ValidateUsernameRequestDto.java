package mrwaitandsee.userdomainapi.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Validated
public class ValidateUsernameRequestDto {
    @NotNull
    @NotBlank
    private String name;
}
