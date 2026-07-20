package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.dto.ObraDTO;
import com.biblioteca.biblioteca.exception.NotFoundException;
import com.biblioteca.biblioteca.model.Obra;
import com.biblioteca.biblioteca.service.ObraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/obras")
@Validated
public class ObraController {

    private final ObraService service;

    public ObraController(ObraService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Obra> listar() {
        return service.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@Valid @RequestBody ObraDTO obraDto) {
        service.salvar(obraDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Obra> atualizar( @PathVariable Long id,@Valid @RequestBody ObraDTO obra) throws NotFoundException {
        Obra obraAtualizada = service.atualizar(id,obra);
        return ResponseEntity.ok(obraAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) throws NotFoundException {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}