package com.example.demo.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
        @NotBlank String name,
        String surname,
        @NotBlank String password,
        @Email @NotBlank String mail
) {
}
