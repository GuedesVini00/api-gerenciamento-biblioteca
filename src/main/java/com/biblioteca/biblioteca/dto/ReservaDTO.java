package com.biblioteca.biblioteca.dto;

import com.biblioteca.biblioteca.enums.StatusReserva;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor

public class ReservaDTO {
    private Long idObra;
    private Long idLeitor;

}
