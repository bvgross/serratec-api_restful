package org.serratec.aula_01.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @RequestMapping("/ola")
    public String olaMundo() {
        return "Olá mundo!";
    }

    @RequestMapping(value = "/resposta", method = RequestMethod.GET, produces = {"application/json"}) //default
    public String msg() {
        return "Putz grila!";
    }
}
