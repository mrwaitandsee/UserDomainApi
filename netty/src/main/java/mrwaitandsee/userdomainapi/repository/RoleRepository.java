package mrwaitandsee.userdomainapi.repository;

import mrwaitandsee.userdomainapi.entity.RoleEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface RoleRepository extends ReactiveCrudRepository<RoleEntity, UUID> {
    @Query("SELECT * FROM \"role\" WHERE name = 'user'")
    Mono<RoleEntity> findUserRole();
}
