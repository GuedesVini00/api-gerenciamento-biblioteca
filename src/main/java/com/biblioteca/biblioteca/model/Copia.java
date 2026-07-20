package com.biblioteca.biblioteca.model;


import com.biblioteca.biblioteca.enums.StatusCopia;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Copia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String codigoPatrimonio;

    @Enumerated(EnumType.STRING)
    private StatusCopia status;

    @ManyToOne
    @JoinColumn(name = "id_obra")
    private Obra obra;


    @Override
    public String toString() {
        return codigoPatrimonio;
    }

    public boolean estaDisponivel() {
        return status == StatusCopia.DISPONIVEL;
    }

    public boolean estaReservada() {
        return status == StatusCopia.RESERVADA;
    }

    public void reservar() {
        status = StatusCopia.RESERVADA;
    }

    public void cancelarReserva() {
        status = StatusCopia.DISPONIVEL;
    }

    public void emprestar() {
        status = StatusCopia.EMPRESTADA;
    }

    public void devolver() {
        status = StatusCopia.DISPONIVEL;
    }

}
