package com.biblioteca.biblioteca.controller;


import com.biblioteca.biblioteca.dto.LeitorDTO;
import com.biblioteca.biblioteca.exception.NotFoundException;
import com.biblioteca.biblioteca.model.Leitor;
import com.biblioteca.biblioteca.service.LeitorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leitor")
public class LeitorController {
    private final LeitorService service;


    public LeitorController(LeitorService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Leitor> listar(){
        return service.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@Valid @RequestBody LeitorDTO leitorDto){
        service.salvar(leitorDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Leitor> atualizar(@PathVariable Long id,@Valid @RequestBody LeitorDTO leitorDto) throws NotFoundException {
        Leitor leitor = service.atualizar(id, leitorDto);
        return ResponseEntity.ok(leitor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) throws NotFoundException {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }


}


