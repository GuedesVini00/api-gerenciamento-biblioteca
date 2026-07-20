package com.biblioteca.biblioteca.model;


import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@AllArgsConstructor
@Getter
@Setter

public class Leitor extends Pessoa {

    @Override
    public String toString() {
        return getNome();
    }
}
