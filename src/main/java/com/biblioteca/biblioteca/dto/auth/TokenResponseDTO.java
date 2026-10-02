package com.biblioteca.biblioteca.dto.auth;

public record TokenResponseDTO(String token, long expiresIn) {
}
