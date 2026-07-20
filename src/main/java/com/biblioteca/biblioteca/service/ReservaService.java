package com.biblioteca.biblioteca.service;


import com.biblioteca.biblioteca.dto.ReservaDTO;
import com.biblioteca.biblioteca.enums.StatusCopia;
import com.biblioteca.biblioteca.enums.StatusReserva;
import com.biblioteca.biblioteca.exception.BusinessException;
import com.biblioteca.biblioteca.exception.NotFoundException;
import com.biblioteca.biblioteca.model.Copia;
import com.biblioteca.biblioteca.model.Leitor;
import com.biblioteca.biblioteca.model.Obra;
import com.biblioteca.biblioteca.model.Reserva;
import com.biblioteca.biblioteca.repository.CopiaRepository;
import com.biblioteca.biblioteca.repository.LeitorRepository;
import com.biblioteca.biblioteca.repository.ObraRepository;
import com.biblioteca.biblioteca.repository.ReservaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final ObraRepository obraRepository;
    private final LeitorRepository leitorRepository;
    private final CopiaRepository copiaRepository;


    public ReservaService(ReservaRepository reservaRepository, ObraRepository obraRepository, LeitorRepository leitorRepository, CopiaRepository copiaRepository) {
        this.reservaRepository = reservaRepository;
        this.obraRepository = obraRepository;
        this.leitorRepository = leitorRepository;
        this.copiaRepository = copiaRepository;
    }

    public List<Reserva> listar(){
        return reservaRepository.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public Reserva realizarReserva(ReservaDTO reservaDto) throws NotFoundException, BusinessException {


        Obra obra = obraRepository.findById(reservaDto.getIdObra()).
                orElseThrow(() -> new NotFoundException("Obra não encontrada"));

        Copia copia = copiaRepository.findFirstByObra_IdAndStatus(obra.getId(), StatusCopia.DISPONIVEL)
                .orElseThrow(() -> new NotFoundException("Copia não encontradap ou insdisponível  para reserva"));

        Leitor leitor = leitorRepository.findById(reservaDto.getIdLeitor())
                .orElseThrow(() -> new NotFoundException("Leitor não encontrado"));

        if(reservaRepository.countByLeitorIdAndStatus(leitor.getId(), StatusReserva.ATIVA) >= 2) {
            throw new BusinessException("Limite de reservas atingido para este usuário.");
        }

        copia.reservar();
        copiaRepository.save(copia);

        Reserva reserva = Reserva.builder()
                .dataReserva(LocalDate.now())
                .leitor(leitor)
                .copia(copia)
                .status((StatusReserva.ATIVA))
                .build();

        return reservaRepository.save(reserva);
    }

    @Transactional(rollbackFor = Exception.class)
    public Reserva cancelarReserva (Long id) throws NotFoundException {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Reserva não encontrada"));

        reserva.cancelar();

        Copia copia = reserva.getCopia();

        copia.devolver();

        copiaRepository.save(copia);

        return reservaRepository.save(reserva);
    }

    @Transactional(rollbackFor = Exception.class)
    public Reserva finalizarReserva (Long id) throws NotFoundException {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Reserva não encontrada"));

        reserva.finalizar();

        Copia copia = reserva.getCopia();

        copia.devolver();

        copiaRepository.save(copia);

        return reservaRepository.save(reserva);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deletar (Long id) throws NotFoundException, BusinessException {

        Reserva reserva = reservaRepository.findById(id).orElseThrow(() -> new NotFoundException("Reserva não encontrada"));

        if(reserva.estaAtiva()){
            throw new BusinessException("Não é possível excluir uma reserva ativa.");
        }
        reservaRepository.deleteById(id);
    }

    public List<Reserva> listarAtivas() {
        return reservaRepository.findByStatus(StatusReserva.ATIVA);
    }


}
