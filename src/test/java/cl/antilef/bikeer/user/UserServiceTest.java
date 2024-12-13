package cl.antilef.bikeer.user;

import cl.antilef.bikeer.user.dto.CreateUserDTO;
import cl.antilef.bikeer.user.dto.UpdateUserDTO;
import cl.antilef.bikeer.user.entity.User;
import cl.antilef.bikeer.user.exceptions.UserNotFoundException;
import cl.antilef.bikeer.user.repository.UserRepository;
import cl.antilef.bikeer.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    private UserRepository userRepository;
    private UserService us;

    @BeforeEach
    void setUp(){
        userRepository = Mockito.mock(UserRepository.class);
        us = new UserService(userRepository);
    }

    @Test
    void createUserTest(){

        User mockUser = new User("Francisco", "Antilef", "345346436");
        when(userRepository.save(Mockito.any(User.class))).thenReturn(mockUser);

        CreateUserDTO cudto = new CreateUserDTO("Francisco","Antilef","345346436");
        User result = us.createUser(cudto);

        assertEquals("Francisco", result.getFirstName());
    }
    @Test
    void updateNotFoundUserTest() {

        when(userRepository.findById(Mockito.any(Integer.class))).thenReturn(Optional.empty());

        UpdateUserDTO uudto = new UpdateUserDTO("23","Francisco","Antilef","452143244");


        Exception exception = assertThrows(UserNotFoundException.class,()-> us.editUser(uudto));

        assertEquals("User not found, cannot update",exception.getMessage());
    }

    @Test
    void updateUserTest() throws UserNotFoundException {

        //TODO this test is fake

        User mockUser = User.withId("23489023904", "Francisco", "Antilef", "345346436");
        when(userRepository.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(mockUser));

        when(userRepository.save(Mockito.any(User.class))).thenReturn(mockUser);


        UpdateUserDTO uudto = new UpdateUserDTO("24","Franco","Antilef","452143244");

        User result = us.editUser(uudto);

        assertEquals("452143244",result.getPhone());

    }
}
