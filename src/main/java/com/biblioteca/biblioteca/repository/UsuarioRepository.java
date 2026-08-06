package com.biblioteca.biblioteca.repository;

import com.biblioteca.biblioteca.model.UsuarioEntity;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    Optional<UsuarioEntity> findByEmail(@NotBlank String email);

    boolean existsByFuncionarioId(Long funcionarioId);
}
