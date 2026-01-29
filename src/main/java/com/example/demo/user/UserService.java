package com.example.demo.user;

import com.example.demo.user.dto.*;
import com.example.demo.user.dto.mapper.UserMapper;
import com.example.demo.user.entity.User;
import com.example.demo.user.exception.DuplicateMailException;
import com.example.demo.user.exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

import static com.example.demo.user.dto.mapper.UserMapper.applyUpdate;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(CreateUserRequest request) {
        if (userRepository.existsByMail(request.mail())) throw new DuplicateMailException(request.mail());

        User user = UserMapper.toEntity(request);
        User saved = userRepository.save(user);
        return UserMapper.toResponse(saved);
    }

    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return UserMapper.toResponse(user);
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toResponse)
                .toList();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserResponse updateUser(UpdateUserRequest request, Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id) );

        applyUpdate(user, request);
        User saved = userRepository.save(user);
        return UserMapper.toResponse(saved);
    }

    public UserResponse patchUser(PatchUserRequest request, Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        UserMapper.applyPatch(user, request);
        User saved = userRepository.save(user);
        return UserMapper.toResponse(saved);
    }

    public CountResponse count() {
        return new CountResponse(userRepository.count());
    }

    public UserResponse findByMail(String mail) {
        User user = userRepository.findByMail(mail)
                .orElseThrow(() -> new RuntimeException("No existe."));

        return UserMapper.toResponse(user);
    }
}
