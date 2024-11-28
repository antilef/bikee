package cl.antilef.bikeer.user.repository;

import cl.antilef.bikeer.user.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface MongoUserRepository extends MongoRepository<User,String> {
    Optional<User> findByEmail(String email);

    List<User> findByLastName(String lastName);
}
