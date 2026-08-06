package com.biblioteca.biblioteca.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
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

    @OneToOne(mappedBy = "funcionario")
    private UsuarioEntity usuario;

    @Override
    public String toString() {

        return getNome()+"-"+ getCargo();
    }
}
