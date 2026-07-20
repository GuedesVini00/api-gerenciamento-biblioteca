package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.dto.CopiaDTO;
import com.biblioteca.biblioteca.exception.NotFoundException;
import com.biblioteca.biblioteca.model.Copia;
import com.biblioteca.biblioteca.service.CopiaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/copias")
@Validated
public class CopiaController {

    private final CopiaService service;


    public CopiaController(CopiaService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Copia> listar() {
        return service.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@Valid @RequestBody CopiaDTO dto) throws Exception {
         service.salvar(dto);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Copia> atualizar(@PathVariable Long id,@Valid @RequestBody CopiaDTO copia) throws NotFoundException {
        Copia copiaAtualizada = service.atualizar(id, copia);
        return ResponseEntity.ok(copiaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) throws NotFoundException {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
