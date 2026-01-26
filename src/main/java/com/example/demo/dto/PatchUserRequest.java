package com.example.demo.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PatchUserRequest (

        String name,
        String surname,
        @Positive Integer age,
        String mail,
        String job
){
}
