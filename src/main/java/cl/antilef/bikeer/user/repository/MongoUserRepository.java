package cl.antilef.bikeer.user.repository;

import cl.antilef.bikeer.user.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MongoUserRepository extends MongoRepository<User,String> {
    User findByFirstName(String firstName);
    List<User> findByLastName(String lastName);
}
