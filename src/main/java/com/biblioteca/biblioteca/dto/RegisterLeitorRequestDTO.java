package com.biblioteca.biblioteca.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RegisterLeitorRequestDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String cpf;

    private LocalDate dataNascimento;

    private String telefone;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String senha;
}