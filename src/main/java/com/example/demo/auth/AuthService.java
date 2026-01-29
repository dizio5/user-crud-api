package com.example.demo.auth;

import com.example.demo.auth.dto.AuthMapper;
import com.example.demo.auth.dto.RegisterRequest;
import com.example.demo.auth.dto.RegisterResponse;
import com.example.demo.user.UserRepository;
import com.example.demo.user.entity.User;
import com.example.demo.user.exception.DuplicateMailException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public AuthService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public RegisterResponse register(RegisterRequest request) {
        if (userRepository.existsByMail(request.mail())) throw new DuplicateMailException(request.mail());

        User user = AuthMapper.toUser(request);
        user.setPassword(encoder.encode(user.getPassword()));
        User saved = userRepository.save(user);

        return AuthMapper.toResponse(saved);
    }
}
