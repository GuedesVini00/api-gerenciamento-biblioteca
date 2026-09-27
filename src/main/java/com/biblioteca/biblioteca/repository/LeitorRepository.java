package com.biblioteca.biblioteca.repository;

import com.biblioteca.biblioteca.model.Leitor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LeitorRepository extends JpaRepository<Leitor, Long> {

    Optional <Leitor> findByEmail(String email);

    Optional<Leitor> findByCpf(String cpf);
}
