package org.serratec.exercicios.exercicio05.controller;

import org.serratec.exercicios.exercicio05.domain.Proprietario;
import org.serratec.exercicios.exercicio05.respositories.ProprietarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proprietarios")
public class ProprietarioController {

    @Autowired
    ProprietarioRepository proprietarioRepository;

    @GetMapping
    public ResponseEntity<List<Proprietario>> listar() {
        List<Proprietario> proprietarios = proprietarioRepository.findAll();
        return ResponseEntity.ok(proprietarios);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Proprietario inserir(@RequestBody Proprietario proprietario) {
        return proprietarioRepository.save(proprietario);
    }

    @PostMapping("/lista")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Proprietario> inserirLista(@RequestBody List<Proprietario> proprietario) {
        return proprietarioRepository.saveAll(proprietario);
    }
}
