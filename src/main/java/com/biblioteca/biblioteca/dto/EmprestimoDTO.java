package com.biblioteca.biblioteca.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class EmprestimoDTO {

    @NotNull
    private Long idCopia;
    @NotNull
    private Long idLeitor;
    @NotNull
    private Long idFuncionario;
}
