package com.biblioteca.biblioteca.service;

import com.biblioteca.biblioteca.dto.ObraDTO;
import com.biblioteca.biblioteca.exception.NotFoundException;
import com.biblioteca.biblioteca.model.Obra;
import com.biblioteca.biblioteca.repository.ObraRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ObraService {

    private final ObraRepository repository;

    public ObraService(ObraRepository repository) {
        this.repository = repository;
    }

    public List<Obra> listar() {
        return repository.findAll();
    }

    public Obra salvar(ObraDTO obraDto) {

        if (obraDto.getTitulo() == null) {
            throw new RuntimeException("Título obrigatório.");
        }
        Obra obra = Obra.builder()
                .titulo(obraDto.getTitulo())
                .autor(obraDto.getAutor())
                .dataPublicacao(obraDto.getDataPublicacao())
                .categoria(obraDto.getCategoria())
                .tipo(obraDto.getTipo())
                .build();
        return repository.save(obra);
    }


    public Obra atualizar(Long id, ObraDTO obra) throws NotFoundException {
        Obra obraBanco = repository.findById(id).orElseThrow(() -> new NotFoundException("Obra não encontrada."));
        if (obra.getTitulo() != null){obraBanco.setTitulo(obra.getTitulo());}
        if (obra.getAutor() != null){obraBanco.setAutor(obra.getAutor());}
        if (obra.getDataPublicacao()!= null){obraBanco.setDataPublicacao(obra.getDataPublicacao());}
        if (obra.getCategoria()!= null){obraBanco.setCategoria(obra.getCategoria());}
        if (obra.getTipo()!= null){obraBanco.setTipo(obra.getTipo());}
        return repository.save(obraBanco);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deletar(Long id) throws NotFoundException {
        repository.findById(id).orElseThrow(() -> new NotFoundException("Obra não encontrada."));
        repository.deleteById(id);
    }
}   