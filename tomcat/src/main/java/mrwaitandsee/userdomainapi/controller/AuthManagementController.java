package mrwaitandsee.userdomainapi.controller;

import mrwaitandsee.userdomainapi.dto.AuthenticationRequestDto;
import mrwaitandsee.userdomainapi.dto.RegistrationResponseDto;
import mrwaitandsee.userdomainapi.service.AuthManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth-management")
public class AuthManagementController {
    private final AuthManagementService authManagementService;

    public AuthManagementController(AuthManagementService authManagementService) {
        this.authManagementService = authManagementService;
    }

    @PostMapping("/authentication")
    public ResponseEntity<RegistrationResponseDto> authentication(@RequestBody AuthenticationRequestDto body) {
        return ResponseEntity.ok(
                new RegistrationResponseDto(true, authManagementService.authentication(body.getAccessToken())));
    }
}
