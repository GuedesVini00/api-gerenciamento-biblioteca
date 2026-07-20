package com.biblioteca.biblioteca.dto;

import com.biblioteca.biblioteca.enums.StatusCopia;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CopiaDTO {

    private String codigoPatrimonio;
    private StatusCopia status;
    private Long idObra;

}
