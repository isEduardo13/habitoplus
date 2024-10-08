package com.habitoplus.habitoplusback.Exeption;

public class UserAlreadyExistsException extends Exception {
        
        public UserAlreadyExistsException(String message) {
            super(message);
        }
}
