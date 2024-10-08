package com.habitoplus.habitoplusback.Exeption;

import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExeptionHandlerAdvice {
    @ExceptionHandler(NoSuchElementException.class) 
    public ResponseEntity<String>handleException(NoSuchElementException e){
     return new ResponseEntity<>("The requested element does not exist", HttpStatus.NOT_FOUND);   
    }
   

}
