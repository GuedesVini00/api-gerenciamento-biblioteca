package com.biblioteca.biblioteca.repository;

import com.biblioteca.biblioteca.enums.StatusEmprestimo;
import com.biblioteca.biblioteca.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    Integer countByLeitorIdAndStatus(Long leitorId, StatusEmprestimo status);
}
