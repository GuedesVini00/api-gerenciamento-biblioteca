package com.biblioteca.biblioteca.service;

import com.biblioteca.biblioteca.dto.funcionario.FuncionarioRequestDTO;
import com.biblioteca.biblioteca.dto.funcionario.FuncionarioResponseDTO;
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

    public List<FuncionarioResponseDTO> listar() {

        return repository.findAll()
                .stream()
                .map(funcionario -> new FuncionarioResponseDTO(
                        funcionario.getId(),
                        funcionario.getNome(),
                        funcionario.getCpf(),
                        funcionario.getDataNascimento(),
                        funcionario.getTelefone(),
                        funcionario.getEmail(),
                        funcionario.getCargo()
                ))
                .toList();
    }

    @Transactional
    public Funcionario salvar(FuncionarioRequestDTO funcionarioRequestDto) throws BusinessException {

        if(funcionarioRequestDto.getNome()==null || funcionarioRequestDto.getCargo()==null){
            throw new BusinessException("Nome e cargo são obrigatórios!");
        }

        if(repository.findByCpf(funcionarioRequestDto.getCpf()).isPresent()){
            throw new BusinessException("CPF ja cadastrado!");
        }

        Funcionario funcionario = Funcionario.builder()
                .nome(funcionarioRequestDto.getNome())
                .cpf(funcionarioRequestDto.getCpf())
                .dataNascimento(funcionarioRequestDto.getDataNascimento())
                .telefone(funcionarioRequestDto.getTelefone())
                .telefone(funcionarioRequestDto.getTelefone())
                .email(funcionarioRequestDto.getEmail())
                .cargo(funcionarioRequestDto.getCargo())
                .build();

        return repository.save(funcionario);
    }

    public Funcionario atualizar(Long id, FuncionarioRequestDTO funcionarioRequestDTO) throws NotFoundException {

        Funcionario funcionario = repository.findById(id).orElseThrow(()-> new NotFoundException("Funcionário não encontrado"));

        if (funcionarioRequestDTO.getNome() != null)funcionario.setNome(funcionarioRequestDTO.getNome());
        if (funcionarioRequestDTO.getCpf() != null)funcionario.setCpf(funcionarioRequestDTO.getCpf());
        if (funcionarioRequestDTO.getDataNascimento() != null)funcionario.setDataNascimento(funcionarioRequestDTO.getDataNascimento());
        if (funcionarioRequestDTO.getTelefone() != null)funcionario.setTelefone(funcionarioRequestDTO.getTelefone());
        if (funcionarioRequestDTO.getEmail() != null)funcionario.setEmail(funcionarioRequestDTO.getEmail());
        if (funcionarioRequestDTO.getCargo() != null)funcionario.setCargo(funcionarioRequestDTO.getCargo());

        return repository.save(funcionario);

    }

    @org.springframework.transaction.annotation.Transactional(rollbackFor = Exception.class)
    public void deletar(Long id) throws NotFoundException {
        repository.findById(id).orElseThrow(() -> new NotFoundException("Leitor não encontrado!"));
        repository.deleteById(id);
    }



}
