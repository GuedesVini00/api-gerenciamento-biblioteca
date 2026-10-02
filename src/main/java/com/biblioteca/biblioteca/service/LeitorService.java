package com.biblioteca.biblioteca.service;

import com.biblioteca.biblioteca.dto.LeitorDTO;
import com.biblioteca.biblioteca.dto.funcionario.FuncionarioResponseDTO;
import com.biblioteca.biblioteca.dto.leitor.LeitorResponseDTO;
import com.biblioteca.biblioteca.exception.NotFoundException;
import com.biblioteca.biblioteca.model.Leitor;
import com.biblioteca.biblioteca.repository.LeitorRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class LeitorService {
    private final LeitorRepository repository;

    public LeitorService(LeitorRepository repository) {
        this.repository = repository;
    }

    public List<LeitorResponseDTO> listar(){

        return repository.findAll()
                .stream()
                .map(leitor -> new LeitorResponseDTO(
                        leitor.getId(),
                        leitor.getNome(),
                        leitor.getCpf(),
                        leitor.getDataNascimento(),
                        leitor.getTelefone(),
                        leitor.getEmail())
                )
                .toList();
    }

    public void salvar(LeitorDTO leitordto) throws BadRequestException {
        Leitor leitor = repository.findByEmail(leitordto.getEmail())
                .orElse(null);

        if(leitor != null){
            throw new BadRequestException("Leitor ja cadastrado!");
        }

        repository.save(Leitor.builder()
                .nome(leitordto.getNome())
                .cpf(leitordto.getCpf())
                .dataNascimento(leitordto.getDataNascimento())
                .telefone(leitordto.getTelefone())
                .email(leitordto.getEmail())
                .build());
    }

    public Leitor atualizar(Long id, LeitorDTO leitordto) throws NotFoundException {
        Leitor leitor = repository.findById(id).orElseThrow(() -> new NotFoundException("Leitor não encontrado!"));
        if(leitordto.getNome() != null){ leitor.setNome(leitordto.getNome());}
        if(leitordto.getCpf() != null){ leitor.setCpf(leitordto.getCpf());}
        if(leitordto.getDataNascimento() != null){leitor.setDataNascimento(leitordto.getDataNascimento());}
        if(leitordto.getTelefone() != null){leitor.setTelefone(leitordto.getTelefone());}
        if(leitordto.getEmail() != null){leitor.setEmail(leitordto.getEmail());}
        return repository.save(leitor);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deletar(Long id) throws NotFoundException {
        repository.findById(id).orElseThrow(() -> new NotFoundException("Leitor não encontrado!"));
        repository.deleteById(id);
    }


}
