package mrwaitandsee.userdomainapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("user_role")
public class UserRoleEntity {
    private UUID id;
    private UUID userId;
    private UUID roleId;
}
