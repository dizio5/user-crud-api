package com.example.demo.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateUserRequest (
        @NotBlank String name,
        String surname,
        @Positive @NotNull Integer age,
        @NotBlank @Email String mail,
        @NotBlank String job
) {

}
