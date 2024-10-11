package com.habitoplus.habitoplusback.Exeption;

import java.nio.file.AccessDeniedException;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import javax.naming.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
public class ExeptionHandlerAdvice {
    @RestControllerAdvice
    public class GlobalExceptionHandler {

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

        // Maneja la excepción HttpRequestMethodNotSupportedException (405 Method Not Allowed)
        @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
        public ResponseEntity<?> handleMethodNotAllowed(HttpRequestMethodNotSupportedException e) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("Method not allowed");
        }

        // Maneja la excepción HttpMediaTypeNotSupportedException (415 Unsupported Media Type)
        @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
        public ResponseEntity<?> handleUnsupportedMediaType(HttpMediaTypeNotSupportedException e) {
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("Unsupported media type");
        }

        // Maneja la excepción AccessDeniedException (403 Forbidden)
        @ExceptionHandler(AccessDeniedException.class)
        public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
        }

        // Maneja la excepción AuthenticationException (401 Unauthorized)
        @ExceptionHandler(AuthenticationException.class)
        public ResponseEntity<?> handleUnauthorized(AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }

        // Maneja la excepción TimeoutException (408 Request Timeout)
        @ExceptionHandler(TimeoutException.class)
        public ResponseEntity<?> handleRequestTimeout(TimeoutException e) {
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("Request timeout");
        }

        // Maneja la excepción UnsupportedOperationException (501 Not Implemented)
        @ExceptionHandler(UnsupportedOperationException.class)
        public ResponseEntity<?> handleNotImplemented(UnsupportedOperationException e) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Not implemented");
        }
    }

}
