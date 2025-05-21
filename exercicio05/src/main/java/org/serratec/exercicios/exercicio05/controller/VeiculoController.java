package org.serratec.exercicios.exercicio05.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    /*
    @Operation(summary = "Lista todos os veiculos",
        description = "A resposta lista os dados dos veiculos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
            content = {@Content(schema = @Schema(implementation = Veiculo.class),
                mediaType = "application/json")}, description = "Retorna todos os Veiculos"),
        @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
        @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
        @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
        @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação")
    })
    */
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
            v.setMarca(veiculo.getMarca());
            v.setModelo(veiculo.getModelo());
            v.setPlaca(veiculo.getPlaca());
            v.setCaracteristica(veiculo.getCaracteristica());
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
