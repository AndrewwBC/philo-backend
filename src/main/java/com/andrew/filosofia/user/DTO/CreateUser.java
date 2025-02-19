package com.andrew.filosofia.user.DTO;

import jakarta.validation.constraints.*;

public record CreateUser (
        @NotNull
        @NotEmpty
        String username,
        @NotNull
        @NotEmpty
        String fullname,
        @Email
        String email,
        @NotNull
        @NotEmpty
        @Size(min = 8, max = 32)
        @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).+$\n", message = "Senha não está no formato esperado.")
        String password
)
{}
