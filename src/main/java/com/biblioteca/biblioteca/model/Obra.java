package com.biblioteca.biblioteca.model;

import com.biblioteca.biblioteca.enums.TipoObra;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Obra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String titulo;
    private String autor;
    private LocalDate dataPublicacao;
    private String categoria;
    @Enumerated(EnumType.STRING)
    private TipoObra tipo;

    @Override
    public String toString() {
        if (titulo == null) {
            return "Obra ID: " + id;
        }

        return titulo + " - " + autor;
    }
}
