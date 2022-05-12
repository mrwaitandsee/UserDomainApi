package mrwaitandsee.userdomainapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectUserEntity {
    @Id
    private UUID id;
    private UUID projectId;
    private UUID userId;

    @Override
    public String toString() {
        return "'" + id.toString() + "', '" + projectId + "', '" + userId + "'";
    }
}
