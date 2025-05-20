package org.serratec.exercicios.exercicio05.controller;

import org.serratec.exercicios.exercicio05.domain.Manutencao;
import org.serratec.exercicios.exercicio05.respositories.ManutencaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/manutencoes")
public class ManutencaoController {

    @Autowired
    private ManutencaoRepository manutencaoRepository;

    @GetMapping
    public ResponseEntity<List<Manutencao>> listar() {
        List<Manutencao> manutencoes = manutencaoRepository.findAll();
        return ResponseEntity.ok(manutencoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manutencao> buscar(@PathVariable Long id) {
        Optional<Manutencao> manutencaoOpt = manutencaoRepository.findById(id);
        if (manutencaoOpt.isPresent()) {
            return ResponseEntity.ok(manutencaoOpt.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Manutencao inserir(@RequestBody Manutencao manutencao) {
        return manutencaoRepository.save(manutencao);
    }
}
