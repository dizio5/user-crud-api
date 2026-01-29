package com.example.demo.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank String name,
        @NotBlank String password
) {
}
