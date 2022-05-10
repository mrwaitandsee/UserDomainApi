package mrwaitandsee.userdomainapi.entity;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserEntity {
    private UUID id;
    private String name;
    private String password;
}
