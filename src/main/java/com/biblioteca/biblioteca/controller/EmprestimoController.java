package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.dto.EmprestimoDTO;
import com.biblioteca.biblioteca.exception.BusinessException;
import com.biblioteca.biblioteca.exception.NotFoundException;
import com.biblioteca.biblioteca.model.Emprestimo;
import com.biblioteca.biblioteca.service.EmprestimoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimo")
@Validated
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Emprestimo> listar() {
        return emprestimoService.listar();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void realizarEmprestimo(@Valid @RequestBody EmprestimoDTO emprestimoDto) throws NotFoundException, BusinessException {
        emprestimoService.realizarEmprestimo(emprestimoDto);
    }


    @PatchMapping("/finalizar/{id}")
    public ResponseEntity<Emprestimo> finzalizarEmprestimo(@PathVariable Long id) throws BusinessException, NotFoundException {
       Emprestimo emprestimo = emprestimoService.finalizarEmprestimo(id);
        return ResponseEntity.ok(emprestimo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Emprestimo> deletarEmprestimo(@PathVariable Long id) throws BusinessException, NotFoundException {
        emprestimoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
