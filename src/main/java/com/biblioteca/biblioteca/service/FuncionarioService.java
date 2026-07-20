package com.biblioteca.biblioteca.service;

import com.biblioteca.biblioteca.dto.FuncionarioDTO;
import com.biblioteca.biblioteca.exception.BusinessException;
import com.biblioteca.biblioteca.exception.NotFoundException;
import com.biblioteca.biblioteca.model.Funcionario;
import com.biblioteca.biblioteca.repository.FuncionarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    private final FuncionarioRepository repository;

    public FuncionarioService(FuncionarioRepository repository) {
        this.repository = repository;
    }

    public List<Funcionario> listar(){
        return repository.findAll();
    }

    @Transactional
    public Funcionario salvar(FuncionarioDTO funcionarioDto) throws BusinessException {

        if(funcionarioDto.getNome()==null || funcionarioDto.getCargo()==null){
            throw new BusinessException("Nome e cargo são obrigatórios!");
        }

        if(repository.findByCpf(funcionarioDto.getCpf()).isPresent()){
            throw new BusinessException("CPF ja cadastrado!");
        }

        Funcionario funcionario = Funcionario.builder()
                .nome(funcionarioDto.getNome())
                .cpf(funcionarioDto.getCpf())
                .dataNascimento(funcionarioDto.getDataNascimento())
                .telefone(funcionarioDto.getTelefone())
                .telefone(funcionarioDto.getTelefone())
                .email(funcionarioDto.getEmail())
                .cargo(funcionarioDto.getCargo())
                .build();

        return repository.save(funcionario);
    }

    public Funcionario atualizar(Long id, FuncionarioDTO funcionarioDTO) throws NotFoundException {

        Funcionario funcionario = repository.findById(id).orElseThrow(()-> new NotFoundException("Funcionário não encontrado"));

        if (funcionarioDTO.getNome() != null)funcionario.setNome(funcionarioDTO.getNome());
        if (funcionarioDTO.getCpf() != null)funcionario.setCpf(funcionarioDTO.getCpf());
        if (funcionarioDTO.getDataNascimento() != null)funcionario.setDataNascimento(funcionarioDTO.getDataNascimento());
        if (funcionarioDTO.getTelefone() != null)funcionario.setTelefone(funcionarioDTO.getTelefone());
        if (funcionarioDTO.getEmail() != null)funcionario.setEmail(funcionarioDTO.getEmail());
        if (funcionarioDTO.getCargo() != null)funcionario.setCargo(funcionarioDTO.getCargo());

        return repository.save(funcionario);

    }

    @org.springframework.transaction.annotation.Transactional(rollbackFor = Exception.class)
    public void deletar(Long id) throws NotFoundException {
        repository.findById(id).orElseThrow(() -> new NotFoundException("Leitor não encontrado!"));
        repository.deleteById(id);
    }



}
