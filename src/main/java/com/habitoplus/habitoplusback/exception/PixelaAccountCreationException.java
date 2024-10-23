package com.habitoplus.habitoplusback.exception;

public class PixelaAccountCreationException extends RuntimeException{
    public PixelaAccountCreationException(String message) {
        super(message);
    }

    public PixelaAccountCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
