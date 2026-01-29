package com.example.demo.user.dto;

import java.time.LocalDateTime;

public record UserResponse (
        Long id,
        String name,
        String surname,
        Integer age,
        String mail,
        String job,
        LocalDateTime created
) {

}
