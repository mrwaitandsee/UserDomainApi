package mrwaitandsee.userdomainapi.service;

import mrwaitandsee.userdomainapi.dto.UserRegistrationResponseDto;
import mrwaitandsee.userdomainapi.entity.UserEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthManagementService {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final JwtService jwtService;

    public AuthManagementService(JwtService jwtService, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jwtService = jwtService;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public UserRegistrationResponseDto authentication(String token) {
        String userId = jwtService.getUserId(token);
        SqlParameterSource params = new MapSqlParameterSource("id", UUID.fromString(userId));
        UserEntity result = namedParameterJdbcTemplate.query(
                "SELECT * FROM \"user\" WHERE id = :id",
                params,
                rs -> {
                    rs.next();
                    return UserEntity.builder()
                            .id(UUID.fromString(rs.getString("id")))
                            .name(rs.getString("name"))
                            .build();
                });

        return new UserRegistrationResponseDto(result.getId().toString(), result.getName());
    }
}
