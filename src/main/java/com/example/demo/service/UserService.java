package com.example.demo.service;

import com.example.demo.dto.CountResponse;
import com.example.demo.dto.CreateUserRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.dto.mapper.UserMapper;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(CreateUserRequest request) {
        // DTO -> Entity
        UserEntity userEntity = UserMapper.toEntity(request);

        // Guardar en DB (JPA)
        // save inserta si id == null, actualiza si id != null
        UserEntity saved = userRepository.save(userEntity);

        // Entity -> Response DTO
        return UserMapper.toResponse(saved);
    }

    public UserResponse getUserById(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return UserMapper.toResponse(user);
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> UserMapper.toResponse(user))
                .toList();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public CountResponse count() {
        return new CountResponse(userRepository.count());
    }

    public UserResponse findByMail(String mail) {
        UserEntity userEntity = userRepository.findByMail(mail)
                .orElseThrow(() -> new RuntimeException("No existe."));

        return UserMapper.toResponse(userEntity);
    }
}
