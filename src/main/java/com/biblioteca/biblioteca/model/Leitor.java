package com.biblioteca.biblioteca.model;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter

public class Leitor extends Pessoa {
    @OneToOne(mappedBy = "leitor")
    private UsuarioEntity usuario;

    @Override
    public String toString() {
        return getNome();
    }
}
