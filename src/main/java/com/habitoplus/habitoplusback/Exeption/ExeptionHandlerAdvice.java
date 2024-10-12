package com.habitoplus.habitoplusback.Exeption;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExeptionHandlerAdvice {
    // Maneja la excepción genérica NoSuchElementException (404 Not Found)
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
        return new ResponseEntity<>("The requested item is not registered", HttpStatus.NOT_FOUND);
    }

    // Maneja la excepción IllegalStateException (204 No Content)
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<?> handleNoContentException(IllegalStateException e) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // Maneja solicitudes incorrectas (400 Bad Request)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleBadRequest(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad request");
    }

    // Maneja errores de validación (400 Bad Request)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        String errorMessage = "Data integrity violation: "
                + (e.getRootCause() != null ? e.getRootCause().getMessage() : e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }    

    // Maneja errores de validación (400 Bad Request)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException e) {
        // Extrae los mensajes de error de cada campo con problemas
        List<String> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    // Maneja errores internos del servidor (500 Internal Server Error)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllExceptions(Exception e) {
        System.out.println("Exception type: " + e.getClass().getName());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
    }

    

}
