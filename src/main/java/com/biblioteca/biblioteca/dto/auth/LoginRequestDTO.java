package com.biblioteca.biblioteca.dto.auth;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor

public class LoginRequestDTO {
    @NotBlank
    private String email;
    @NotBlank
    private String senha;
    
}
