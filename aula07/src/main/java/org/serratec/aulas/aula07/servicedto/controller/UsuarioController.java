package org.serratec.aulas.aula07.servicedto.controller;

import org.serratec.aulas.aula07.servicedto.domain.Usuario;
import org.serratec.aulas.aula07.servicedto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> usuarios = usuarioService.buscarTodos();
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping
    public ResponseEntity<Usuario> inserir(@RequestBody Usuario usuario) {
        usuario = usuarioService.inserir(usuario);
        URI uri = ServletUriComponentsBuilder
            .fromCurrentRequest() //localhost:8080/usuarios
            .path("/{id}") //localhost:8080/usuarios/{id}
            .buildAndExpand(usuario.getId()) //localhost:8080/usuarios/1
            .toUri();
        return ResponseEntity.created(uri).body(usuario);
    }
}
