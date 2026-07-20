package com.biblioteca.biblioteca.model;


import com.biblioteca.biblioteca.enums.StatusReserva;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate dataReserva;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_copia", nullable = false)
    private Copia copia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_leitor", nullable = false)
    private Leitor leitor;

    @Enumerated(EnumType.STRING)
    private StatusReserva status;

    public boolean estaAtiva(){
        return status == StatusReserva.ATIVA;
    }

    public boolean estaCancelada(){
        return status == StatusReserva.CANCELADA;
    }

    public void cancelar(){
        status = StatusReserva.CANCELADA;
    }

    public void finalizar(){
        status = StatusReserva.FINALIZADA;
    }
}
