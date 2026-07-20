package com.biblioteca.biblioteca.repository;

import com.biblioteca.biblioteca.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository <Funcionario,Long>{

    Optional<Funcionario> findByCpf(String cpf);
}
