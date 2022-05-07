package mrwaitandsee.userdomainapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Service;

@Service
public class UserManagementService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserManagementService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean validateUsername(String name) {
        String query = "SELECT * FROM \"user\" WHERE name = ?";
        Boolean result = jdbcTemplate.query(
                query,
                ps -> ps.setString(1, name),
                rs -> {
                    int i = 0;
                    while (rs.next()) {
                        i++;
                    }
                    return i == 0;
                });
        return result;
    }
}
