package mrwaitandsee.userdomainapi.repository;

import mrwaitandsee.userdomainapi.entity.ProjectUserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProjectUserRepository extends ReactiveCrudRepository<ProjectUserEntity, UUID> {

}
