package mrwaitandsee.userdomainapi.service;

import ch.qos.logback.core.util.FixedDelay;
import mrwaitandsee.userdomainapi.dto.RegistrationRequestDto;
import mrwaitandsee.userdomainapi.dto.UserRegistrationResponseDto;
import mrwaitandsee.userdomainapi.entity.*;
import mrwaitandsee.userdomainapi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.r2dbc.core.PreparedOperation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserManagementService {
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final RoleRepository roleRepository;
    private final BcryptService bcryptService;
    private final UserRoleRepository userRoleRepository;
    private final ProjectUserRepository projectUserRepository;

    @Autowired
    public UserManagementService(UserRepository userRepository,
                                 ProjectRepository projectRepository,
                                 BcryptService bcryptService,
                                 RoleRepository roleRepository,
                                 UserRoleRepository userRoleRepository,
                                 ProjectUserRepository projectUserRepository,
                                 R2dbcEntityTemplate r2dbcEntityTemplate) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.bcryptService = bcryptService;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
        this.projectUserRepository = projectUserRepository;
        this.r2dbcEntityTemplate = r2dbcEntityTemplate;
    }

    public Flux<UserEntity> test() {
        return r2dbcEntityTemplate
                .getDatabaseClient()
                .sql("SELECT * FROM \"user\"")
                .fetch()
                .all()
                .flatMap(data ->
                        Mono.just(UserEntity.builder()
                                .id(UUID.fromString(data.get("id").toString()))
                                .name(data.get("name").toString())
                                .password(null)
                                .build()
                        )
                );
    }

    @Transactional
    public Mono<UserRegistrationResponseDto> registration(RegistrationRequestDto dto) {
        return validateUsername(dto.getName()).flatMap(hasUname -> {
            if (hasUname) throw new RuntimeException("User already exists!");
            List<String> projectNames = dto.getProject();
            if (projectNames.size() == 0) throw new RuntimeException("No projects!");

            String query = "SELECT * FROM \"project\" WHERE name IN (" + projectNames.stream().map(item -> "'" + item + "'").collect(Collectors.joining(", ")) + ")";
            Flux<ProjectEntity> projects = r2dbcEntityTemplate
                    .getDatabaseClient()
                    .sql(query)
                    .fetch()
                    .all()
                    .flatMap(data -> Mono.just(ProjectEntity.builder()
                            .id(UUID.fromString(data.get("id").toString()))
                            .name(data.get("name").toString())
                            .description(data.get("description").toString())
                            .build()
                    ));

            Flux<UUID> projectIds = projects.flatMap(projectEntity -> Mono.just(projectEntity.getId()));

            String hash = bcryptService.hash(dto.getPassword());
            UserEntity newUser = UserEntity.builder()
                    .id(UUID.randomUUID())
                    .name(dto.getName())
                    .password(hash)
                    .build();
            Mono<UserEntity> userEntityMono = userRepository.save(newUser);
            return userEntityMono.flatMap(userEntity -> {
                Mono<RoleEntity> roleEntityMono = roleRepository.findUserRole().flatMap(Mono::just);

                return roleEntityMono.flatMap(roleEntity -> {
                    UserRoleEntity newUserRole = UserRoleEntity.builder()
                            .id(UUID.randomUUID())
                            .userId(newUser.getId())
                            .roleId(roleEntity.getId())
                            .build();
                    userRoleRepository.save(newUserRole).subscribe();
                    Flux<ProjectUserEntity> projectUserEntityFlux = projectIds.flatMap(projectId ->
                            Mono.just(ProjectUserEntity.builder()
                                    .id(UUID.randomUUID())
                                    .projectId(projectId)
                                    .userId(newUser.getId())
                                    .build()
                            )
                    );
                    projectUserRepository.saveAll(projectUserEntityFlux).subscribe();
                    return Mono.just(new UserRegistrationResponseDto(newUser.getId().toString(), newUser.getName()));
                });
            });
        });
    }

    public Mono<Boolean> validateUsername(String name) {
        return userRepository.findUserByName(name).hasElements();
    }
}
