package org.serratec.aulas.aula07.servicedto.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

//    @ExceptionHandler(EmailException.class)
//    protected ResponseEntity<Object> handleEmailException(EmailException ex) {
//        return ResponseEntity.unprocessableEntity().body(ex.getMessage());
//    }
//
//    @ExceptionHandler(SenhaException.class)
//    protected ResponseEntity<Object> handleEmailException(SenhaException ex) {
//        return ResponseEntity.unprocessableEntity().body(ex.getMessage());
//    }

    @ExceptionHandler({EmailException.class, SenhaException.class})
    protected ResponseEntity<Object> handleEmailESenhaException(RuntimeException ex) {
        return ResponseEntity.unprocessableEntity().body(ex.getMessage());
    }
}
