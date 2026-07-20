package com.biblioteca.biblioteca.model;

import com.biblioteca.biblioteca.enums.StatusEmprestimo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataSaida;

    private LocalDate dataDevolucao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_copia", nullable = false)
    private Copia copia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_leitor", nullable = false)
    private Leitor leitor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_funcionario", nullable = false)
    private Funcionario funcionario;

    @Enumerated(EnumType.STRING)
    private StatusEmprestimo status;

    @Override
    public String toString() {
        return leitor.getNome() + " - " + copia.getCodigoPatrimonio();
    }

    public boolean estaAtivo(){
        return status == StatusEmprestimo.ATIVO;
    }

    public boolean estaFinalizado(){
        return status == StatusEmprestimo.FINALIZADO;
    }

    public void ativar(){
        status = StatusEmprestimo.ATIVO;
    }

    public void finalizar(){
        status = StatusEmprestimo.FINALIZADO;
    }
}
