package com.example.demo.service;

import com.example.demo.dto.CreateUserRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.dto.mapper.UserMapper;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

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

    public void getAllUsers() {

    }

    public void deleteUser() {

    }
}
