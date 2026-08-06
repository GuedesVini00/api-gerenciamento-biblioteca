package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.dto.LoginRequestDTO;
import com.biblioteca.biblioteca.dto.RegisterRequestDTO;
import com.biblioteca.biblioteca.dto.TokenResponseDTO;
import com.biblioteca.biblioteca.exception.BusinessException;
import com.biblioteca.biblioteca.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public void register (@RequestBody @Valid RegisterRequestDTO registerRequestDTO) throws BusinessException {
        authenticationService.register(registerRequestDTO);
    }

    @PostMapping("/login")
    public TokenResponseDTO login (@RequestBody @Valid LoginRequestDTO loginRequestDTO) throws BusinessException {
        return authenticationService.login(loginRequestDTO);
    }
}
