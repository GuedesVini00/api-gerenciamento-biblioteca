package com.biblioteca.biblioteca.controller;


import com.biblioteca.biblioteca.dto.ReservaDTO;
import com.biblioteca.biblioteca.exception.BusinessException;
import com.biblioteca.biblioteca.exception.NotFoundException;
import com.biblioteca.biblioteca.model.Reserva;
import com.biblioteca.biblioteca.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reserva")
public class ReservaController {
    private final ReservaService service;

    public ReservaController(ReservaService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Reserva> listar(){
        return service.listar();
    }

    @GetMapping("/ativas")
    @ResponseStatus(HttpStatus.OK)
    public List<Reserva> ativas(){
        return service.listarAtivas();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void realizarReserva(@Valid @RequestBody ReservaDTO reservaDto) throws BusinessException, NotFoundException {
        service.realizarReserva(reservaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Reserva> deletar(@PathVariable Long id) throws BusinessException, NotFoundException {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/cancelar/{id}")
    public ResponseEntity<Reserva> cancelarReserva(@PathVariable Long id) throws NotFoundException {
        Reserva reserva = service.cancelarReserva(id);
        return ResponseEntity.ok(reserva);
    }

    @PatchMapping("/finalizar/{id}")
    public ResponseEntity<Reserva> finalizarReserva(@PathVariable Long id) throws NotFoundException {
        Reserva reserva = service.finalizarReserva(id);
        return ResponseEntity.ok(reserva);
    }


}
