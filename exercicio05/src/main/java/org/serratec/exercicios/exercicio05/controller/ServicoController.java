package org.serratec.exercicios.exercicio05.controller;

import org.serratec.exercicios.exercicio05.domain.Servico;
import org.serratec.exercicios.exercicio05.respositories.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    @Autowired
    private ServicoRepository servicoRepository;

    @GetMapping
    public ResponseEntity<List<Servico>> listar() {
        List<Servico> servicos = servicoRepository.findAll();
        return ResponseEntity.ok(servicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servico> buscar(@PathVariable Long id) {
        Optional<Servico> servicoOpt = servicoRepository.findById(id);
        if (servicoOpt.isPresent()) {
            return ResponseEntity.ok(servicoOpt.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Servico inserir(@RequestBody Servico servico) {
        return servicoRepository.save(servico);
    }
}
