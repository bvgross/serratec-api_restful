package org.serratec.exercicio_03.veiculo.controller;

import org.apache.el.lang.ELArithmetic;
import org.serratec.exercicio_03.veiculo.domain.Produto;
import org.serratec.exercicio_03.veiculo.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> pesquisar(@PathVariable Long id) {
        Optional<Produto> produtoOpt = produtoRepository.findById(id);
        if (produtoOpt.isPresent()) {
            return ResponseEntity.ok(produtoOpt.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto inserir(@RequestBody Produto produto) {
       produto = produtoRepository.save(produto);
       return produto;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> modificar(@PathVariable Long id, @RequestBody Produto produto) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if (produtoOptional.isPresent()) {
            Produto existente = produtoOptional.get();
            existente.setDescricao(produto.getDescricao());
            existente.setValor(produto.getValor());
            existente.setDataCadastro(produto.getDataCadastro());
            Produto atualizado = produtoRepository.save(existente);
            return ResponseEntity.ok(atualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if (produtoOptional.isPresent()) {
            produtoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
