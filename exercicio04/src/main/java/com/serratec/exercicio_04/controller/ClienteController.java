package com.serratec.exercicio_04.controller;

import com.serratec.exercicio_04.domain.Cliente;
import com.serratec.exercicio_04.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> pesquisar(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public Cliente inserir(@Valid @RequestBody Cliente cliente) {
        cliente = clienteRepository.save(cliente);
        return cliente;
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<Cliente> editar(@PathVariable Long id, @Valid @RequestBody Cliente c) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            Cliente clienteExistente = cliente.get();
            clienteExistente.setNome(c.getNome());
            clienteExistente.setCpf(c.getCpf());
            clienteExistente.setEmail(c.getEmail());
            clienteExistente.setDataNascimento(c.getDataNascimento());
            clienteRepository.save(clienteExistente);
            return ResponseEntity.ok(clienteExistente);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            clienteRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
