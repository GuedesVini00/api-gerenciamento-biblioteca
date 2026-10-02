package com.biblioteca.biblioteca.dto.auth;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor

public class RegisterRequestDTO {
    @NotBlank
    private String email;
    @NotBlank
    private String senha;

    @NotNull
    private long funcionarioId;
}
