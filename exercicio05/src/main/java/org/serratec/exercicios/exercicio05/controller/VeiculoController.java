package org.serratec.exercicios.exercicio05.controller;

import jakarta.validation.Valid;
import org.serratec.exercicios.exercicio05.domain.Veiculo;
import org.serratec.exercicios.exercicio05.respositories.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    VeiculoRepository veiculoRepository;

    @GetMapping
    public ResponseEntity<List<Veiculo>> exibirLista() {
        return ResponseEntity.ok(veiculoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> exibirItem(@PathVariable Long id) {
        Optional<Veiculo> veiculoOptional = veiculoRepository.findById(id);
        if (veiculoOptional.isPresent()) {
            Veiculo veiculo = veiculoOptional.get();
            return ResponseEntity.ok(veiculo);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Veiculo incluirItem(@Valid @RequestBody Veiculo veiculo) {
        veiculoRepository.save(veiculo);
        return veiculo;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Veiculo> editarItem(@PathVariable Long id, @RequestBody Veiculo veiculo) {
        Optional<Veiculo> veiculoOptional = veiculoRepository.findById(id);
        if (veiculoOptional.isPresent()) {
            Veiculo v = veiculoOptional.get();
            if (!veiculo.getMarca().isBlank()) {
                v.setMarca(veiculo.getMarca());
            }
            if (!veiculo.getModelo().isBlank()) {
                v.setModelo(veiculo.getModelo());
            }
            if (!veiculo.getPlaca().isBlank()) {
                v.setPlaca(veiculo.getPlaca());
            }
            if (veiculo.getCaracteristica() != null) {
                v.setCaracteristica(veiculo.getCaracteristica());
            }
            veiculoRepository.save(v);
            return ResponseEntity.ok(v);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarItem(@PathVariable Long id) {
        Optional<Veiculo> veiculoOptional = veiculoRepository.findById(id);
        if (veiculoOptional.isPresent()) {
            veiculoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
