package mrwaitandsee.userdomainapi.repository;

import mrwaitandsee.userdomainapi.entity.UserEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Repository
public interface UserRepository extends ReactiveCrudRepository<UserEntity, UUID> {
    @Query("SELECT * FROM \"user\" WHERE name = :name")
    Flux<UserEntity> findUserByName(String name);
}
