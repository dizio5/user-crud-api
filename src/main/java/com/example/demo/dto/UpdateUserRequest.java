package com.example.demo.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UpdateUserRequest (

        @NotBlank String name,
        String surname,
        @Positive Integer age,
        @NotBlank String mail,
        String job,
        @Column(name = "company_id") Long companyId
){
}
