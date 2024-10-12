package com.habitoplus.habitoplusback.Exeption;

import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExeptionHandlerAdvice {
    // Maneja la excepción genérica NoSuchElementException (404 Not Found)
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleNoSuchElementException(NoSuchElementException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Object not found");
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

    // Maneja errores internos del servidor (500 Internal Server Error)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleInternalServerError(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
    }

}
