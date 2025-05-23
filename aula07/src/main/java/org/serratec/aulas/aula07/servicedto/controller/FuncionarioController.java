package org.serratec.aulas.aula07.servicedto.controller;

import org.serratec.aulas.aula07.servicedto.domain.Funcionario;
import org.serratec.aulas.aula07.servicedto.dto.FuncionarioSalarioDTO;
import org.serratec.aulas.aula07.servicedto.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @GetMapping
    public ResponseEntity<List<Funcionario>> listar() {
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        return ResponseEntity.ok(funcionarios);
    }

    @GetMapping("/paginado")
    public ResponseEntity<Page<Funcionario>> listarPaginado(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Funcionario> funcionarios = funcionarioRepository.findAll(pageable);
        return ResponseEntity.ok(funcionarios);
    }

    @GetMapping("/salario")
    public ResponseEntity<Page<Funcionario>> listarSalarios(
        @RequestParam(defaultValue = "0") Double valorMinimo,
        @RequestParam(defaultValue = "20000") Double valorMaximo,
        Pageable pageable) {
        Page<Funcionario> func = funcionarioRepository.buscarSalarioJPQL(
            valorMinimo, valorMaximo, pageable);
        return ResponseEntity.ok(func);
    }

    @GetMapping("/nome")
    public ResponseEntity<Page<Funcionario>> buscarPorNome(
        @RequestParam(defaultValue = "") String nome,
        Pageable pageable) {
        Page<Funcionario> funcionarios = funcionarioRepository.buscarPorNome(nome, pageable);
        return ResponseEntity.ok(funcionarios);
    }


    @GetMapping("/salarios-por-idade")
    public ResponseEntity<List<FuncionarioSalarioDTO>> buscaSalariosPorIdade() {
        List<FuncionarioSalarioDTO> salarios = funcionarioRepository.buscaSalariosPorIdade();
        return ResponseEntity.ok(salarios);
    }

}

