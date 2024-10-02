package com.habitoplus.habitoplusback.Exeption;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExeptionHandlerAdvice {
    @ExceptionHandler(NotFoundException.class)
    public  String handleNotFound(NotFoundException e){
        return e.getMessage();
    }

}
