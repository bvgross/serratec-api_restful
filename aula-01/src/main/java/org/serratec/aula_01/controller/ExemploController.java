package org.serratec.aula_01.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ExemploController {

    @GetMapping
    public String teste() {
        return "Teste Serratec";
    }

    @GetMapping("/oi") //tem que inserir outro endereço para não ficar ambíguo
    public String oi() {
        return "Oi!";
    }

    @GetMapping("/maiusculo")
    public String maiusculo(@RequestParam String valor, @RequestParam String sobrenome) {

        return valor.toUpperCase() + " " + sobrenome.toUpperCase();
    }
}
