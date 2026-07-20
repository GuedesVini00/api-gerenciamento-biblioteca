package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.dto.FuncionarioDTO;
import com.biblioteca.biblioteca.exception.BusinessException;
import com.biblioteca.biblioteca.exception.NotFoundException;
import com.biblioteca.biblioteca.model.Funcionario;
import com.biblioteca.biblioteca.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/funcionario")
@Validated
public class FuncionarioController {

    private final FuncionarioService service;

    public FuncionarioController(FuncionarioService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Funcionario> listar() {
        return service.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@Valid @RequestBody FuncionarioDTO funcionarioDto) throws BusinessException {
        service.salvar(funcionarioDto);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Funcionario> atualizar(@PathVariable Long id,@Valid @RequestBody FuncionarioDTO funcionarioDTO) throws NotFoundException {
        Funcionario funcionario = service.atualizar(id, funcionarioDTO);
        return ResponseEntity.ok(funcionario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) throws NotFoundException {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
