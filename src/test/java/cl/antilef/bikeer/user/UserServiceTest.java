package cl.antilef.bikeer.user;

import cl.antilef.bikeer.auth.exception.UserAlreadyExistException;
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
    void testCreateUser(){

        User mockUser = new User("Francisco", "Antilef","antilef@bikker.cl","345346436");

        when(userRepository.save(Mockito.any(User.class))).thenReturn(mockUser);
        when(userRepository.existsByEmail("antilef@bikeer.cl")).thenReturn(false);

        CreateUserDTO cudto = new CreateUserDTO("Francisco","Antilef","antilef@bikker.cl","345346436");
        User result = us.createUser(cudto);

        assertEquals("Francisco", result.getFirstName());
    }

    @Test
    void should_TrowAlreadyExitsUserException_When_ExistUserTest(){

        when(userRepository.existsByEmail("antilef@bikeer.cl")).thenReturn(true);

        CreateUserDTO cudto = new CreateUserDTO("Francisco","Antilef","antilef@bikeer.cl","345346436");

        assertThrows(UserAlreadyExistException.class,()-> us.createUser(cudto));

        Mockito.verify(userRepository, Mockito.never()).save(Mockito.any(User.class));
    }



    @Test
    void should_TrowUserNotFoundException_When_TryToUpdateNotExistUserTest() {

        when(userRepository.findById(Mockito.any(Integer.class))).thenReturn(Optional.empty());

        UpdateUserDTO uudto = new UpdateUserDTO("23","Francisco","Antilef","452143244");


        Exception exception = assertThrows(UserNotFoundException.class,()-> us.editUser(uudto));

        assertEquals("User not found, cannot update",exception.getMessage());
    }

    @Test
    void should_UpdateUser_When_InformationIsValidTest() {

        User mockUser = User.withId("12", "Francisco", "Antilef","antilef@bikeer.cl" ,"345346436");
        User spyUser = Mockito.spy(mockUser);

        when(userRepository.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(spyUser));
        when(userRepository.save(Mockito.any(User.class))).thenReturn(spyUser);


        UpdateUserDTO uudto = new UpdateUserDTO("24","Franco","Antilef","452143244");

        User result = us.editUser(uudto);

        assertEquals("452143244",result.getPhone());
        assertEquals("Franco",result.getFirstName());
        Mockito.verify(spyUser, Mockito.times(1)).update(uudto);

    }
}
