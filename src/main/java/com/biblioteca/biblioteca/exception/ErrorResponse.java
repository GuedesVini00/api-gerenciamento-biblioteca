package com.biblioteca.biblioteca.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime tempo;
    private String mensagem;
    private Integer status;
}
