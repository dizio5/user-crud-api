package com.example.demo.service;

import com.example.demo.dto.CreateUserRequest;
import com.example.demo.dto.PatchUserRequest;
import com.example.demo.dto.UpdateUserRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.entity.User;
import com.example.demo.exception.DuplicateMailException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    void createUser_whenMailIsNotDuplicate_returnsUserResponse() {
        CreateUserRequest request = new CreateUserRequest(
                "Facundo", "Avila", 22,
                "facundo.avila.dev@gmail.com", "Student"
        );

        when(userRepository.existsByMail(request.mail())).thenReturn(false);
        when(userRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        UserResponse response = userService.createUser(request);

        assertEquals("Facundo", response.name());
        assertEquals("Avila", response.surname());
        assertEquals(22, response.age());
        assertEquals("facundo.avila.dev@gmail.com", response.mail());
        assertEquals("Student", response.job());

        verify(userRepository).existsByMail(request.mail());
        verify(userRepository).save(any());
    }

    @Test
    void createUser_whenMailIsDuplicate_throwsUserNotFoundException() {
        CreateUserRequest request = new CreateUserRequest(
                "Facundo", "Avila", 22,
                "facundo.avila.dev@gmail.com", "Student"
        );

        when(userRepository.existsByMail(request.mail())).thenReturn(true);

        assertThrows(DuplicateMailException.class,
                () -> userService.createUser(request));

        verify(userRepository).existsByMail(request.mail());
        verify(userRepository, never()).save(any());
    }

    @Test
    void updateUser_updatesAllFields() {
        Long id = 1L;

        UpdateUserRequest request = new UpdateUserRequest(
                "Facundo", "Avila", 22,
                "facundo.avila.dev@gmail.com", "Student"
        );

        User user = new User();
        user.setName("John");
        user.setSurname("Doe");
        user.setAge(40);
        user.setJob("Computer Engineer");
        user.setMail("johndoe@hotmail.com");

        when(userRepository.findById(id))
                .thenReturn(Optional.of(user));

        when(userRepository.save(any(User.class)))
                .thenAnswer(inv -> inv.getArgument(0)); // devuelve el mismo user mutado

        userService.updateUser(request, id);

        assertEquals("Facundo", user.getName());
        assertEquals("Avila", user.getSurname());
        assertEquals(22, user.getAge());
        assertEquals("facundo.avila.dev@gmail.com", user.getMail());
        assertEquals("Student", user.getJob());

       verify(userRepository).findById(id);
       verify(userRepository).save(user);
    }

    @Test
    void updateUser_whenUserNotFound_throwsUserNotFoundException() {
        Long id = 1L;

        UpdateUserRequest request = new UpdateUserRequest(
                "John", "Doe", 40, "johndoe@outlook.com", "Engineer"
        );


        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
                () -> userService.updateUser(request, id));

        verify(userRepository).findById(1L);
        verify(userRepository, never()).save(any());
    }

    @Test
    void patchUser_UpdateOnlyNonNullFields() {
        Long id = 1L;

        PatchUserRequest request = new PatchUserRequest(
                "John", null, 40, null, null
        );

        User user = new User();
        user.setName("Facundo");
        user.setSurname("Avila");
        user.setAge(22);
        user.setMail("facundo.avila.dev@gmail.com");
        user.setJob("Student");

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class)))
                .thenAnswer(inv -> inv.getArgument(0));

        userService.patchUser(request, id);

        assertEquals("John", user.getName());
        assertEquals(40, user.getAge());
        assertEquals("Avila", user.getSurname());
        assertEquals("facundo.avila.dev@gmail.com", user.getMail());
        assertEquals("Student", user.getJob());

        verify(userRepository).findById(id);
        verify(userRepository).save(user);
    }

    @Test
    void patchUser_whenUserNotFound_throwsUserNotFoundException() {
        Long id = 1L;

        PatchUserRequest request = new PatchUserRequest(
                "John", null, 40, null, null
        );

        when(userRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
                () -> userService.patchUser(request, id));

        verify(userRepository).findById(id);
        verify(userRepository, never()).save(any());
    }
}
