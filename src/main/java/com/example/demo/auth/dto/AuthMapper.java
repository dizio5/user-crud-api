package com.example.demo.auth.dto;

import com.example.demo.user.entity.User;

public class AuthMapper {

    private AuthMapper() {

    }

    public static User toUser(RegisterRequest request) {
        User user = new User();
        user.setName(request.name());
        user.setMail(request.mail());
        user.setPassword(request.password());
        user.setSurname(request.surname());

        return user;
    }

    public static AuthResponse toResponse(String token) {
        return new AuthResponse(
                token, "Bearer", 900
        );
    }
}
