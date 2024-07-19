package com.joaobarbosa.msavaliadorCredito.model;

import com.joaobarbosa.msavaliadorCredito.application.exception.ClientDataNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {


    @ExceptionHandler(ClientDataNotFoundException.class)
    public ResponseEntity<StanderError> clientNotFound(ClientDataNotFoundException e, HttpServletRequest request) {
        StanderError standerError = new StanderError();
        standerError.setMessage(e.getMessage());
        standerError.setStatus(HttpStatus.NOT_FOUND.value());
        standerError.setError("Recurso nao encontrado");
        standerError.setTimestamp(Instant.now());
        standerError.setPath(request.getRequestURI());
        return new ResponseEntity<>(standerError, HttpStatus.NOT_FOUND);
    }
}
