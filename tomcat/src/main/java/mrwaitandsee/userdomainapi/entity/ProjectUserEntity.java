package mrwaitandsee.userdomainapi.entity;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ProjectUserEntity {
    private UUID id;
    private UUID projectId;
    private UUID userId;

    @Override
    public String toString() {
        return "'" + id.toString() + "', '" + projectId + "', '" + userId + "'";
    }
}
