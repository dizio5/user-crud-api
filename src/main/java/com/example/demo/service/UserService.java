package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.dto.mapper.UserMapper;
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.DuplicateMailException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

import static com.example.demo.dto.mapper.UserMapper.applyUpdate;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(CreateUserRequest request) {
        if (userRepository.existsByMail(request.mail())) throw new DuplicateMailException(request.mail());

        UserEntity userEntity = UserMapper.toEntity(request);
        UserEntity saved = userRepository.save(userEntity);
        return UserMapper.toResponse(saved);
    }

    public UserResponse getUserById(Long id) {
        UserEntity user = userRepository.findById(id)
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
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id) );

        applyUpdate(user, request);
        UserEntity saved = userRepository.save(user);
        return UserMapper.toResponse(saved);
    }

    public UserResponse patchUser(PatchUserRequest request, Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        UserMapper.applyPatch(user, request);
        UserEntity saved = userRepository.save(user);
        return UserMapper.toResponse(saved);
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
