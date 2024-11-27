package cl.antilef.bikeer.user;

import cl.antilef.bikeer.user.entity.User;
import cl.antilef.bikeer.user.repository.MongoUserRepository;
import cl.antilef.bikeer.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    private MongoUserRepository userRepository;
    private UserService us;

    @BeforeEach
    void setUp(){
        userRepository = Mockito.mock(MongoUserRepository.class);
        us = new UserService(userRepository);
    }

    @Test
    void createUser(){

        User mockUser = new User("Francisco", "Antilef", "345346436");
        when(userRepository.save(Mockito.any(User.class))).thenReturn(mockUser);

        CreateUserDTO cudto = new CreateUserDTO("Francisco","Antilef","345346436");
        User result = us.createUser(cudto);

        assertEquals("Francisco", result.getFirstName());
    }
}
