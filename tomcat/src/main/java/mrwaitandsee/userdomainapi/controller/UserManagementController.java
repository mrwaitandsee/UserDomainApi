package mrwaitandsee.userdomainapi.controller;

import mrwaitandsee.userdomainapi.dto.*;
import mrwaitandsee.userdomainapi.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional
    @PostMapping("/registration")
    public ResponseEntity<RegistrationResponseDto> registration(@Valid @RequestBody RegistrationRequestDto body) {
        return ResponseEntity.ok(new RegistrationResponseDto(true, userManagementService.registration(body)));
    }

    @PostMapping("validate-username")
    public ResponseEntity<ValidateUsernameResponseDto> validateUsername(@Valid @RequestBody ValidateUsernameRequestDto body) {
        Boolean isValid = userManagementService.validateUsername(body.getName());
        return ResponseEntity.ok(new ValidateUsernameResponseDto(true, isValid));
    }
}
