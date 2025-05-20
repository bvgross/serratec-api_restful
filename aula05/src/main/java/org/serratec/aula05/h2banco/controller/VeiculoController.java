package org.serratec.aula05.h2banco.controller;

import org.hibernate.engine.spi.Resolution;
import org.serratec.aula05.h2banco.domain.Veiculo;
import org.serratec.aula05.h2banco.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/veiculos")
@RestController
public class VeiculoController {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @GetMapping
    public ResponseEntity<List<Veiculo>> listar() {
        List<Veiculo> veiculos = veiculoRepository.findAll();
        return ResponseEntity.ok(veiculos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> buscar(@PathVariable Long id) {
        Optional<Veiculo> veiculosOpt = veiculoRepository.findById(id);
        if (veiculosOpt.isPresent()) {
            return ResponseEntity.ok(veiculosOpt.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public Veiculo inserir(@RequestBody Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }
}
