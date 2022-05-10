package mrwaitandsee.userdomainapi.service;

import mrwaitandsee.userdomainapi.dto.RegistrationRequestDto;
import mrwaitandsee.userdomainapi.dto.RegistrationResponseDto;
import mrwaitandsee.userdomainapi.dto.UserRegistrationResponseDto;
import mrwaitandsee.userdomainapi.entity.ProjectUserEntity;
import mrwaitandsee.userdomainapi.entity.UserEntity;
import mrwaitandsee.userdomainapi.entity.UserRoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserManagementService {
    private final JdbcTemplate jdbcTemplate;
    private final BcryptService bcrypt;

    @Autowired
    public UserManagementService(JdbcTemplate jdbcTemplate, BcryptService bcryptService) {
        this.jdbcTemplate = jdbcTemplate;
        this.bcrypt = bcryptService;
    }

    @Transactional
    public UserRegistrationResponseDto registration(RegistrationRequestDto dto) {
        if (!validateUsername(dto.getName())) throw new RuntimeException("User already exists!");

        List<String> projectNames = dto.getProject();
        if (projectNames.size() == 0) throw new RuntimeException("No projects!");

        String query = "SELECT * FROM \"project\" WHERE name IN (" + projectNames.stream().map(item -> "'" + item + "'").collect(Collectors.joining(", ")) + ")";

        List<String> projectIds = jdbcTemplate.query(query, (rs, rowNum) -> rs.getString(1));

        String hash = bcrypt.hash(dto.getPassword());

        UserEntity newUser = UserEntity.builder()
                .id(UUID.randomUUID())
                .name(dto.getName())
                .password(hash)
                .build();


        String roleId = jdbcTemplate.query(
                "SELECT * FROM \"role\" WHERE name = 'user'",
                rs -> {
                    rs.next();
                    return rs.getString(1);
                }
        );

        jdbcTemplate.update(
                "INSERT INTO \"user\" VALUES (?, ?, ?)",
                newUser.getId(),
                newUser.getName(),
                newUser.getPassword()
        );

        UserRoleEntity newUserRole = UserRoleEntity.builder()
                .id(UUID.randomUUID())
                .userId(newUser.getId())
                .roleId(UUID.fromString(roleId))
                .build();

        jdbcTemplate.update("INSERT INTO \"user_role\" VALUES (?, ?, ?)",
                newUserRole.getId(),
                newUserRole.getUserId(),
                newUserRole.getRoleId());

        List<String> projectUserEntities = new ArrayList<>();
        projectIds.forEach(projectId -> {
            projectUserEntities.add(
                    "(" +
                    ProjectUserEntity.builder()
                            .id(UUID.randomUUID())
                            .projectId(UUID.fromString(projectId))
                            .userId(newUser.getId())
                            .build().toString() + ")"
            );
        });
        String projectUserEntitiesString = String.join(", ", projectUserEntities);
        jdbcTemplate.update("INSERT INTO \"project_user\" VALUES " + projectUserEntitiesString);

        return new UserRegistrationResponseDto(newUser.getId().toString(), newUser.getName());
    }

    public boolean validateUsername(String name) {
        String query = "SELECT * FROM \"user\" WHERE name = ?";
        Boolean result = jdbcTemplate.query(
                query,
                ps -> ps.setString(1, name),
                rs -> {
                    while (rs.next()) return false;
                    return true;
                });
        return result;
    }
}
