package org.serratec.exercicios.exercicio05.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<String> erros = new ArrayList<>();
        for (FieldError erro: ex.getBindingResult().getFieldErrors()) {
            erros.add(erro.getField() + ": " + erro.getDefaultMessage());
        }

        return super.handleExceptionInternal(ex,
            new ExceptionResposta(status.value(), "Campos inválidos, confira o preenchimento dos dados", LocalDateTime.now(), erros),
            headers,
            status,
            request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
        HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<String> erros = new ArrayList<>();
        erros.add("Valor de enumeração inválido: " + ex.getMostSpecificCause().getMessage());

        ExceptionResposta exceptionResposta = new ExceptionResposta(status.value(), "Existem campos inválidos, confira o preenchimento,", LocalDateTime.now(), erros);

        return super.handleExceptionInternal(ex, exceptionResposta, headers, status, request);
    }
}
