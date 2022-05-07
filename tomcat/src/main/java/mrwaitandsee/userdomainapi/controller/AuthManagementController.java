package mrwaitandsee.userdomainapi.controller;

import mrwaitandsee.userdomainapi.dto.AuthenticationRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth-management")
public class AuthManagementController {
    @PostMapping("/authentication")
    public void authentication(@RequestBody AuthenticationRequestDto body) {

    }
}
