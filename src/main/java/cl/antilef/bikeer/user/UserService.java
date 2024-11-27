package cl.antilef.bikeer.user;

import cl.antilef.bikeer.user.entity.User;
import cl.antilef.bikeer.user.repository.MongoUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private final MongoUserRepository repository;


    public UserService(MongoUserRepository repository) {
        this.repository = repository;
    }

    public User createUser(CreateUserDTO userDTO){
        User user = new User(userDTO.firstname(),userDTO.lastname(),userDTO.phone());

        return repository.save(user);
    }
}
