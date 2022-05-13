package mrwaitandsee.userdomainapi.repository;

import mrwaitandsee.userdomainapi.entity.UserRoleEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRoleRepository extends ReactiveCrudRepository<UserRoleEntity, UUID> {

}
