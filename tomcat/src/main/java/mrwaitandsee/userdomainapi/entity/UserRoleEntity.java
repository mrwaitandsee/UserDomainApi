package mrwaitandsee.userdomainapi.entity;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserRoleEntity {
    private UUID id;
    private UUID userId;
    private UUID roleId;
}
