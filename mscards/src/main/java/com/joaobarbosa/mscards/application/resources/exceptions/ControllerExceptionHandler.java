package com.joaobarbosa.mscards.application.resources.exceptions;

import com.joaobarbosa.mscards.application.services.exceptions.ControllerNotFoundException;
import com.joaobarbosa.mscards.model.exception.StanderError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ControllerNotFoundException.class)
    public ResponseEntity<StanderError> entityNotFound(ControllerNotFoundException ex, HttpServletRequest request) {
        StanderError error = new StanderError();
        HttpStatus status = HttpStatus.NOT_FOUND;
        error.setMessage(ex.getMessage());
        error.setError("Recurso não encontrado");
        error.setPath(request.getRequestURI());
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        return ResponseEntity.status(status).body(error);
    }


}
