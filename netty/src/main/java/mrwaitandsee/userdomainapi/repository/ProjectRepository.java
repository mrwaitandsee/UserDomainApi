package mrwaitandsee.userdomainapi.repository;

import mrwaitandsee.userdomainapi.entity.ProjectEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Repository
public interface ProjectRepository extends ReactiveCrudRepository<ProjectEntity, UUID> {
    @Query("SELECT * FROM \"project\" WHERE name ANY (:names)")
    Flux<ProjectEntity> findProjectsByNames(String[] names);
}
