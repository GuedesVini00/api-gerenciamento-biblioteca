package com.biblioteca.biblioteca.service;

import com.biblioteca.biblioteca.dto.CopiaDTO;
import com.biblioteca.biblioteca.enums.StatusCopia;
import com.biblioteca.biblioteca.exception.NotFoundException;
import com.biblioteca.biblioteca.model.Copia;
import com.biblioteca.biblioteca.model.Obra;
import com.biblioteca.biblioteca.repository.CopiaRepository;
import com.biblioteca.biblioteca.repository.ObraRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CopiaService {

    private final CopiaRepository repository;
    private final ObraRepository obraRepository;

    public CopiaService(CopiaRepository repository, ObraRepository obraRepository) {
        this.repository = repository;
        this.obraRepository = obraRepository;
    }

    public List<Copia> listar() {
        return repository.findAll();
    }

    public Copia salvar(CopiaDTO dto) throws Exception {

        Obra obra = obraRepository.findById(dto.getIdObra())
                .orElseThrow(() -> new NotFoundException("Obra não encontrada."));

        Copia copia = Copia.builder()
                .codigoPatrimonio(dto.getCodigoPatrimonio())
                .status(StatusCopia.DISPONIVEL)
                .obra(obra)
                .build();
        return repository.save(copia);
    }

    public Copia atualizar(Long id, CopiaDTO copia) throws NotFoundException {
        Copia copiaBanco = repository.findById(id).orElseThrow(() -> new NotFoundException("Cópia não encontrada."));
        if (copia.getCodigoPatrimonio() != null){copiaBanco.setCodigoPatrimonio(copia.getCodigoPatrimonio());}
        if (copia.getStatus() != null){copiaBanco.setStatus(copia.getStatus());}
        return repository.save(copiaBanco);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deletar(Long id) throws NotFoundException {
        repository.findById(id).orElseThrow(() -> new NotFoundException("Cópia não encontrada."));
        repository.deleteById(id);
    }

}
