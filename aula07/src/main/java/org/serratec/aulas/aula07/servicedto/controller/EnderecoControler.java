package org.serratec.aulas.aula07.servicedto.controller;

import org.serratec.aulas.aula07.servicedto.dto.EnderecoDTO;
import org.serratec.aulas.aula07.servicedto.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/endereco")
public class EnderecoControler {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping("/{cep}")
    public ResponseEntity<EnderecoDTO> buscar(@PathVariable String cep) {
        EnderecoDTO enderecoDTO = enderecoService.buscar(cep);
        if (enderecoDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(enderecoDTO);
    }
}
