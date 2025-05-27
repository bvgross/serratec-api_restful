package org.serratec.aulas.aula07.servicedto.controller;

import org.serratec.aulas.aula07.servicedto.domain.Foto;
import org.serratec.aulas.aula07.servicedto.domain.Funcionario;
import org.serratec.aulas.aula07.servicedto.dto.FuncionarioDTO;
import org.serratec.aulas.aula07.servicedto.dto.FuncionarioSalarioDTO;
import org.serratec.aulas.aula07.servicedto.repository.FuncionarioRepository;
import org.serratec.aulas.aula07.servicedto.service.FotoService;
import org.serratec.aulas.aula07.servicedto.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    public FotoService fotoService;

    @GetMapping
    public ResponseEntity<List<FuncionarioDTO>> listar() {
        return ResponseEntity.ok(funcionarioService.listar());
    }

    @GetMapping("/{id}/foto")
    public ResponseEntity<byte[]> buscarFoto(@PathVariable Long id) {
        Foto foto = fotoService.buscarPorIdFuncionario(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, foto.getTipo());
        headers.add(HttpHeaders.CONTENT_LENGTH, String.valueOf(foto.getDados().length));
        return new ResponseEntity<>(foto.getDados(), headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> buscar(@PathVariable Long id) {
        FuncionarioDTO dto = funcionarioService.buscar(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public FuncionarioDTO inserir(@RequestPart MultipartFile file, @RequestPart Funcionario funcionario) throws IOException {
        return funcionarioService.inserir(funcionario, file);
    }

//    @GetMapping
//    public ResponseEntity<List<Funcionario>> listar() {
//        List<Funcionario> funcionarios = funcionarioRepository.findAll();
//        return ResponseEntity.ok(funcionarios);
//    }

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

