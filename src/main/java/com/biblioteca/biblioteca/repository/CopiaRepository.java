package com.biblioteca.biblioteca.repository;

import com.biblioteca.biblioteca.enums.StatusCopia;
import com.biblioteca.biblioteca.model.Copia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CopiaRepository extends JpaRepository<Copia,Long> {

    long countByObraIdAndStatus(Long idObra, StatusCopia status);

    Optional<Copia> findFirstByObra_IdAndStatus( Long idObra, StatusCopia status);
    Optional<Copia> findByIdAndStatus(Long id, StatusCopia status);
}
