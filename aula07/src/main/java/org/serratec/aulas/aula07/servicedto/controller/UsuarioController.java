package org.serratec.aulas.aula07.servicedto.controller;

import org.serratec.aulas.aula07.servicedto.dto.UsuarioDTO;
import org.serratec.aulas.aula07.servicedto.dto.UsuarioInserirDTO;
import org.serratec.aulas.aula07.servicedto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listar(/*@AuthenticationPrincipal UserDetails details*/) {
//        System.out.println("login do usuário: " + details.getUsername());
        List<UsuarioDTO> usuarios = usuarioService.buscarTodos();
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> inserir(@RequestBody UsuarioInserirDTO usuarioInsDto) {
        UsuarioDTO usuarioDto = usuarioService.inserir(usuarioInsDto);
        URI uri = ServletUriComponentsBuilder
            .fromCurrentRequest() //localhost:8080/usuarios
            .path("/{id}") //localhost:8080/usuarios/{id}
            .buildAndExpand(usuarioDto.getId()) //localhost:8080/usuarios/1
            .toUri();
        return ResponseEntity.created(uri).body(usuarioDto);
    }
}