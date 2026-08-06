package com.biblioteca.biblioteca.service;



import com.biblioteca.biblioteca.config.TokenProvider;
import com.biblioteca.biblioteca.dto.LoginRequestDTO;
import com.biblioteca.biblioteca.dto.RegisterRequestDTO;
import com.biblioteca.biblioteca.dto.TokenResponseDTO;
import com.biblioteca.biblioteca.enums.RoleType;
import com.biblioteca.biblioteca.exception.BusinessException;
import com.biblioteca.biblioteca.model.Funcionario;
import com.biblioteca.biblioteca.model.RolesEntity;
import com.biblioteca.biblioteca.model.UsuarioEntity;
import com.biblioteca.biblioteca.repository.FuncionarioRepository;
import com.biblioteca.biblioteca.repository.RolesRepository;
import com.biblioteca.biblioteca.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UsuarioRepository usuarioRepository;
    private final RolesRepository rolesRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    @Value("${jwt.expiration}")
    private long expirationTime;

    @Transactional
    public void register(RegisterRequestDTO dto) throws BusinessException {

        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new BusinessException("Usuário já cadastrado com este e-mail.");
        }

        Funcionario funcionario = funcionarioRepository.findById(dto.getFuncionarioId())
                .orElseThrow(() ->
                        new BusinessException("Funcionário não encontrado."));

        if (usuarioRepository.existsByFuncionarioId(dto.getFuncionarioId())) {
            throw new BusinessException("Este funcionário já possui usuário.");
        }



        RolesEntity role = rolesRepository.findByNome(RoleType.ROLE_FUNCIONARIO.name())
                        .orElseGet(() -> rolesRepository.save(RolesEntity.builder()
                        .nome(RoleType.ROLE_FUNCIONARIO.name())
                        .build()));

        usuarioRepository.save(UsuarioEntity.builder()
                .email(dto.getEmail())
                .roles(Set.of(role))
                .senha(passwordEncoder.encode(dto.getSenha()))
                .funcionario(funcionario)
                .build());
    }

    public TokenResponseDTO login(LoginRequestDTO dto) throws BusinessException {
        try{
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getSenha()));
            String token = tokenProvider.gerarToken(auth);

            return  new TokenResponseDTO(token, expirationTime);

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Credenciais Inválidas");
        } catch (Exception e) {
            throw e;
        }
    }
}
