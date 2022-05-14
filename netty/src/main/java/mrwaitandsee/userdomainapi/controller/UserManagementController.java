package mrwaitandsee.userdomainapi.controller;

import mrwaitandsee.userdomainapi.dto.*;
import mrwaitandsee.userdomainapi.entity.UserEntity;
import mrwaitandsee.userdomainapi.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user-management")
public class UserManagementController {
    private final UserManagementService userManagementService;

    @Autowired
    public UserManagementController(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    @CrossOrigin({ "*" })
    @PostMapping(value = "/test", produces = "application/stream+json")
    public Flux<UserEntity> test() {
        return userManagementService.test();
    }

    @PostMapping("/registration")
    public Mono<RegistrationResponseDto> registration(
            @Valid @RequestBody RegistrationRequestDto body,
            @Autowired ServerWebExchange exchange
    ) {
        exchange.getResponse().setStatusCode(HttpStatus.OK);
        Mono<UserRegistrationResponseDto> userRegistrationResponseDtoMono = userManagementService.registration(body);
        return userRegistrationResponseDtoMono.flatMap(userRegistrationResponseDto ->
                Mono.just(new RegistrationResponseDto(true, userRegistrationResponseDto)));
    }

    @PostMapping("validate-username")
    public Mono<ValidateUsernameResponseDto> validateUsername(
            @Valid @RequestBody ValidateUsernameRequestDto body,
            @Autowired ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.OK);
        Mono<Boolean> has = userManagementService.validateUsername(body.getName());
        return has.flatMap(bool -> Mono.just(new ValidateUsernameResponseDto(true, !bool)));
    }
}
