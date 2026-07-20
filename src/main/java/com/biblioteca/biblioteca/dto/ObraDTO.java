package com.biblioteca.biblioteca.dto;

import com.biblioteca.biblioteca.enums.TipoObra;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ObraDTO {
    
    private String titulo;
    private String autor;
    private LocalDate dataPublicacao;
    private String categoria;
    private TipoObra tipo;
}
