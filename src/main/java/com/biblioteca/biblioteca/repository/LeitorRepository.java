package com.biblioteca.biblioteca.repository;

import com.biblioteca.biblioteca.model.Leitor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeitorRepository extends JpaRepository<Leitor, Long> {
}
