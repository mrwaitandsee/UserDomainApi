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
@Table("project_user")
public class ProjectUserEntity {
    private UUID id;
    private UUID projectId;
    private UUID userId;

    @Override
    public String toString() {
        return "'" + id.toString() + "', '" + projectId + "', '" + userId + "'";
    }
}
