package mrwaitandsee.userdomainapi.controller;

import mrwaitandsee.userdomainapi.dto.RegistrationRequestDto;
import mrwaitandsee.userdomainapi.dto.RegistrationResponseDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user-management")
public class UserManagementController {
//    @PostMapping("/registration")
//    public Mono<RegistrationResponseDto> registration(@Valid @RequestBody RegistrationRequestDto body) {
////        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
////                .body(new RegistrationResponseDto(true, null)).;
//    }
}
