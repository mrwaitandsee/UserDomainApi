package mrwaitandsee.userdomainapi.repository;

import mrwaitandsee.userdomainapi.entity.ProjectEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface ProjectRepository extends ReactiveCrudRepository<ProjectEntity, UUID> {
    @Query("SELECT * FROM \"project\" WHERE name IN (:names)")
    Flux<ProjectEntity> findProjectsByNames(String[] names);
}
