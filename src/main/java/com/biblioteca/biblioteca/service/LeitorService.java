package com.biblioteca.biblioteca.service;

import com.biblioteca.biblioteca.dto.LeitorDTO;
import com.biblioteca.biblioteca.exception.NotFoundException;
import com.biblioteca.biblioteca.model.Leitor;
import com.biblioteca.biblioteca.repository.LeitorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LeitorService {
    private final LeitorRepository repository;

    public LeitorService(LeitorRepository repository) {
        this.repository = repository;
    }

    public List<Leitor> listar(){
        return repository.findAll();
    }

    public void salvar(LeitorDTO leitordto){
        Leitor leitor = Leitor.builder()
                .nome(leitordto.getNome())
                .cpf(leitordto.getCpf())
                .dataNascimento(leitordto.getDataNascimento())
                .telefone(leitordto.getTelefone())
                .email(leitordto.getEmail())
                .build();
        repository.save(leitor);
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
