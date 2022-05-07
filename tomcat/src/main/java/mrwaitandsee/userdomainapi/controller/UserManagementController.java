package mrwaitandsee.userdomainapi.controller;

import mrwaitandsee.userdomainapi.dto.LoginRequestDto;
import mrwaitandsee.userdomainapi.dto.RegistrationRequestDto;
import mrwaitandsee.userdomainapi.dto.ValidateUsernameRequestDto;
import mrwaitandsee.userdomainapi.dto.ValidateUsernameResponseDto;
import mrwaitandsee.userdomainapi.service.UserManagementService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user-management")
public class UserManagementController {
    private final UserManagementService userManagementService;

    @Autowired
    public UserManagementController(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    @PostMapping("/login")
    public void login(@RequestBody LoginRequestDto body) {

    }

    @PostMapping("/registration")
    public void registration(@RequestBody RegistrationRequestDto body) {

    }

    @PostMapping("validate-username")
    public ResponseEntity<ValidateUsernameResponseDto> validateUsername(@Valid @RequestBody ValidateUsernameRequestDto body) {
        Boolean isValid = userManagementService.validateUsername(body.getName());
        return ResponseEntity.ok(new ValidateUsernameResponseDto(true, isValid));
    }
}
