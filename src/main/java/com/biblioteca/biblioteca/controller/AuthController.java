package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.dto.auth.LoginRequestDTO;
import com.biblioteca.biblioteca.dto.auth.RegisterLeitorRequestDTO;
import com.biblioteca.biblioteca.dto.auth.RegisterRequestDTO;
import com.biblioteca.biblioteca.dto.auth.TokenResponseDTO;
import com.biblioteca.biblioteca.exception.BusinessException;
import com.biblioteca.biblioteca.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public void register(
            @RequestBody @Valid RegisterRequestDTO registerRequestDTO
    ) throws BusinessException {

        authenticationService.register(registerRequestDTO);
    }

    @PostMapping("/register/leitor")
    public void registerLeitor(
            @RequestBody @Valid RegisterLeitorRequestDTO registerLeitorRequestDTO
    ) throws BusinessException {

        authenticationService.registerLeitor(registerLeitorRequestDTO);
    }

    @PostMapping("/login")
    public TokenResponseDTO login(
            @RequestBody @Valid LoginRequestDTO loginRequestDTO
    ) throws BusinessException {

        return authenticationService.login(loginRequestDTO);
    }
}
