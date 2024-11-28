package cl.antilef.bikeer.user.service;

import cl.antilef.bikeer.user.dto.CreateUserDTO;
import cl.antilef.bikeer.user.dto.UpdateUserDTO;
import cl.antilef.bikeer.user.entity.User;
import cl.antilef.bikeer.user.exceptions.UserNotFoundException;
import cl.antilef.bikeer.user.repository.MongoUserRepository;
import com.jayway.jsonpath.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;


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

    public User editUser(UpdateUserDTO userDTO) throws UserNotFoundException {
        Optional<User> userOptional = repository.findById(userDTO.id());

        if(userOptional.isEmpty()){
            throw new UserNotFoundException("User not found, cannot update");
        }

        User user = userOptional.get();

        user.setFirstName(userDTO.firstname());
        user.setLastName(userDTO.lastname());
        user.setPhone(userDTO.phone());

        return this.repository.save(user);

    }
}
