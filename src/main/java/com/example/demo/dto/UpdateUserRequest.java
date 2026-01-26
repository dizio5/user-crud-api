package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record UpdateUserRequest (

        @NotBlank String name,
        String surname,
        @Positive Integer age,
        @NotBlank String mail,
        String job
){
}
