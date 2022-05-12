package mrwaitandsee.userdomainapi.service;

import mrwaitandsee.userdomainapi.dto.RegistrationRequestDto;
import mrwaitandsee.userdomainapi.dto.UserRegistrationResponseDto;
import mrwaitandsee.userdomainapi.entity.ProjectEntity;
import mrwaitandsee.userdomainapi.entity.UserEntity;
import mrwaitandsee.userdomainapi.repository.ProjectRepository;
import mrwaitandsee.userdomainapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class UserManagementService {
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    public UserManagementService(UserRepository userRepository, ProjectRepository projectRepository) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    @Transactional
    public Mono<UserRegistrationResponseDto> registration(RegistrationRequestDto dto) {
        return validateUsername(dto.getName()).flatMap(hasUname -> {
            if (hasUname) throw new RuntimeException("User already exists!");
            List<String> projectNames = dto.getProject();
            if (projectNames.size() == 0) throw new RuntimeException("No projects!");

            Flux<ProjectEntity> projects = projectRepository.findProjectsByNames(projectNames.toArray(String[]::new));
        });
//        String query = "SELECT * FROM \"project\" WHERE name IN (" + projectNames.stream().map(item -> "'" + item + "'").collect(Collectors.joining(", ")) + ")";
//        List<String> projectIds = jdbcTemplate.query(query, (rs, rowNum) -> rs.getString(1));
//        String hash = bcrypt.hash(dto.getPassword());
//        UserEntity newUser = UserEntity.builder()
//                .id(UUID.randomUUID())
//                .name(dto.getName())
//                .password(hash)
//                .build();
//        String roleId = jdbcTemplate.query(
//                "SELECT * FROM \"role\" WHERE name = 'user'",
//                rs -> {
//                    rs.next();
//                    return rs.getString(1);
//                }
//        );
//        jdbcTemplate.update(
//                "INSERT INTO \"user\" VALUES (?, ?, ?)",
//                newUser.getId(),
//                newUser.getName(),
//                newUser.getPassword()
//        );
//        UserRoleEntity newUserRole = UserRoleEntity.builder()
//                .id(UUID.randomUUID())
//                .userId(newUser.getId())
//                .roleId(UUID.fromString(roleId))
//                .build();
//        jdbcTemplate.update("INSERT INTO \"user_role\" VALUES (?, ?, ?)",
//                newUserRole.getId(),
//                newUserRole.getUserId(),
//                newUserRole.getRoleId());
//        List<String> projectUserEntities = new ArrayList<>();
//        projectIds.forEach(projectId -> {
//            projectUserEntities.add(
//                    "(" +
//                            ProjectUserEntity.builder()
//                                    .id(UUID.randomUUID())
//                                    .projectId(UUID.fromString(projectId))
//                                    .userId(newUser.getId())
//                                    .build().toString() + ")"
//            );
//        });
//        String projectUserEntitiesString = String.join(", ", projectUserEntities);
//        jdbcTemplate.update("INSERT INTO \"project_user\" VALUES " + projectUserEntitiesString);
//        return new UserRegistrationResponseDto(newUser.getId().toString(), newUser.getName());
    }

    public Mono<Boolean> validateUsername(String name) {
        return userRepository.findUserByName(name).hasElements();
    }
}
