package com.biblioteca.biblioteca.service;


import com.biblioteca.biblioteca.dto.EmprestimoDTO;
import com.biblioteca.biblioteca.enums.StatusCopia;
import com.biblioteca.biblioteca.enums.StatusEmprestimo;
import com.biblioteca.biblioteca.enums.StatusReserva;
import com.biblioteca.biblioteca.exception.BusinessException;
import com.biblioteca.biblioteca.exception.NotFoundException;
import com.biblioteca.biblioteca.model.*;
import com.biblioteca.biblioteca.repository.CopiaRepository;
import com.biblioteca.biblioteca.repository.EmprestimoRepository;
import com.biblioteca.biblioteca.repository.FuncionarioRepository;
import com.biblioteca.biblioteca.repository.LeitorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.IllegalFormatCodePointException;
import java.util.List;

@Service
public class EmprestimoService {
    private final EmprestimoRepository emprestimoRepository;
    private final CopiaRepository copiaRepository;
    private final LeitorRepository leitorRepository;
    private final FuncionarioRepository funcionarioRepository;

    public EmprestimoService(EmprestimoRepository emprestimoRepository, CopiaRepository copiaRepository, LeitorRepository leitorRepository, FuncionarioRepository funcionarioRepository) {
        this.emprestimoRepository = emprestimoRepository;
        this.copiaRepository = copiaRepository;
        this.leitorRepository = leitorRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    public List<Emprestimo> listar(){
        return emprestimoRepository.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public Emprestimo realizarEmprestimo(EmprestimoDTO emprestimoDto) throws NotFoundException, BusinessException {

        Copia copia = copiaRepository.findByIdAndStatus(emprestimoDto.getIdCopia(), StatusCopia.DISPONIVEL)
                .orElseThrow(() -> new NotFoundException("Copia não encontrada ou indisponivel"));

        Leitor leitor = leitorRepository.findById(emprestimoDto.getIdLeitor())
                .orElseThrow(() -> new NotFoundException("Leitor não encontrado"));

        Funcionario funcionario = funcionarioRepository.findById(emprestimoDto.getIdFuncionario())
                .orElseThrow(() -> new NotFoundException("Funcionario não encontrado"));

        if(emprestimoRepository.countByLeitorIdAndStatus(leitor.getId(), StatusEmprestimo.ATIVO) >= 2) {
            throw new BusinessException("Limite de emprestimos atingidos para este usuário.");
        }


        copia.emprestar();

        Emprestimo emprestimo = Emprestimo.builder()
                .dataSaida(LocalDate.now())
                .copia(copia)
                .leitor(leitor)
                .funcionario(funcionario)
                .build();
        emprestimo.ativar();

        return emprestimoRepository.save(emprestimo);
    }

    @Transactional(rollbackFor = Exception.class)
    public Emprestimo finalizarEmprestimo(Long id) throws NotFoundException, BusinessException {

        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Emprestimo não encontrado"));

        if (emprestimo.getStatus() == StatusEmprestimo.FINALIZADO) {
            throw new BusinessException("Empréstimo já está finalizado.");
        }
        emprestimo.finalizar();
        emprestimo.setDataDevolucao(LocalDate.now());

        Copia copia = emprestimo.getCopia();
        copia.devolver();

        return emprestimoRepository.save(emprestimo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deletar(Long id) throws NotFoundException, BusinessException {
        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Emprestimo não encontrado"));

        if (emprestimo.estaAtivo()) {
            throw new BusinessException("Emprestimo não finalizado");
        }
        emprestimoRepository.deleteById(id);
    }
}
