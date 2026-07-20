package com.biblioteca.biblioteca.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Funcionario extends Pessoa{
    @Column(nullable = false)
    private String cargo;

    @Override
    public String toString() {

        return getNome()+"-"+ getCargo();
    }
}
