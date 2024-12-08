package cl.antilef.bikeer.auth.service;

import cl.antilef.bikeer.auth.dto.SignInRequest;
import cl.antilef.bikeer.auth.dto.SignUpRequest;
import cl.antilef.bikeer.auth.exception.UserAlreadyExistException;
import cl.antilef.bikeer.user.entity.User;
import cl.antilef.bikeer.user.repository.MongoUserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final MongoUserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthService(
            MongoUserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(SignUpRequest input) throws UserAlreadyExistException {

        Optional<User> userOptional = userRepository.findByEmail(input.getEmail());
        if(userOptional.isPresent()){
            throw new UserAlreadyExistException("The user is already register");
        }
        
        String password = passwordEncoder.encode(input.getPassword());
        User user = new User(input.getFirstName(),input.getLastName(),input.getEmail(),input.getPhone(),password);



        return userRepository.save(user);
    }

    public User authenticate(SignInRequest input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}
