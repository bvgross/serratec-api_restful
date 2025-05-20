package org.serratec.exercicio_01.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculadora")
public class Calculadora {

    @RequestMapping
    public double calc(@RequestParam double numero1, @RequestParam double numero2, @RequestParam String operacao) {
        double total = 0;
        String operacaoMatematica = operacao.toUpperCase();
        switch (operacaoMatematica) {
            case "SOMA" ->  total = numero1 + numero2;
            case "SUBTRACAO" -> total = numero1 - numero2;
            case "MULTIPLICACAO" -> total = numero1 * numero2;
            case "DIVISAO" -> total = numero1 / numero2;
        }
        return total;
    }
}
