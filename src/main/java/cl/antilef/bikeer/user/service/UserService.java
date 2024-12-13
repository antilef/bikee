package cl.antilef.bikeer.user.service;

import cl.antilef.bikeer.auth.exception.UserAlreadyExistException;
import cl.antilef.bikeer.user.dto.CreateUserDTO;
import cl.antilef.bikeer.user.dto.UpdateUserDTO;
import cl.antilef.bikeer.user.entity.User;
import cl.antilef.bikeer.user.exceptions.UserNotFoundException;
import cl.antilef.bikeer.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private final UserRepository repository;


    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User createUser(CreateUserDTO userDTO){

        User user = new User(userDTO.firstname(),userDTO.lastname(),userDTO.email(),userDTO.phone());

        if (repository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistException("User already exists with email: " + user.getEmail());
        }

        return repository.save(user);

    }

    public User editUser(UpdateUserDTO userDTO){
        Optional<User> userOptional = repository.findById(Integer.parseInt(userDTO.id()));

        if(userOptional.isEmpty()){
            throw new UserNotFoundException("User not found, cannot update");
        }

        User user = userOptional.get();


        user.update(userDTO);

        return this.repository.save(user);

    }
}
