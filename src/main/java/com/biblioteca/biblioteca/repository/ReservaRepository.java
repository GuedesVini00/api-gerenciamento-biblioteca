package com.biblioteca.biblioteca.repository;

import com.biblioteca.biblioteca.enums.StatusCopia;
import com.biblioteca.biblioteca.enums.StatusReserva;
import com.biblioteca.biblioteca.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    Integer countByLeitorIdAndStatus(Long idLeitor, StatusReserva status);

    List<Reserva> findByStatus(StatusReserva status);
}
