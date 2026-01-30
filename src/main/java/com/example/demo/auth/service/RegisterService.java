package com.example.demo.auth.service;

import com.example.demo.auth.dto.AuthMapper;
import com.example.demo.auth.dto.AuthResponse;
import com.example.demo.auth.dto.RegisterRequest;
import com.example.demo.user.UserRepository;
import com.example.demo.user.entity.User;
import com.example.demo.user.exception.DuplicateMailException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class RegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtEncoder jwtEncoder;

    public RegisterService(UserRepository userRepository, PasswordEncoder encoder, JwtEncoder jwtEncoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.jwtEncoder = jwtEncoder;
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByMail(request.mail())) throw new DuplicateMailException(request.mail());

        User user = AuthMapper.toUser(request);
        user.setPassword(encoder.encode(user.getPassword()));
        User saved = userRepository.save(user);

        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .subject(saved.getName())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(15 * 60))
                .build();

        JwsHeader header = JwsHeader.with(MacAlgorithm.HS256).build();
        String token = jwtEncoder.encode(JwtEncoderParameters.from(header, claims)).getTokenValue();
        return AuthMapper.toResponse(token);

    }
}
