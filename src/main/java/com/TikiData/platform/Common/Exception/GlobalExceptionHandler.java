package com.TikiData.platform.Common.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NewsNotFoundException.class)
    public ResponseEntity<ErrorMessage> handlerForNewsNotFoundException(NewsNotFoundException ex) {
        ErrorMessage error = new ErrorMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(PlayerNotFoundException.class)
    public ResponseEntity<ErrorMessage> handlerForPlayerNotFoundException(PlayerNotFoundException ex) {
        ErrorMessage error = new ErrorMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(TeamNotFoundException.class)
    public ResponseEntity<ErrorMessage> handlerForTeamNotFoundException(TeamNotFoundException ex) {
        ErrorMessage error = new ErrorMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ErrorMessage> handlerForResourceAlreadyExistsException(ResourceAlreadyExistsException ex) {
        ErrorMessage error = new ErrorMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> handlerForResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorMessage error = new ErrorMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handlerForMethodInvalidException(MethodArgumentNotValidException ex) {
        Map<String, String> mapa = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(
                error -> mapa.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity.status(HttpStatusCode.valueOf(400)).body(mapa);
    }
}
