package com.javaapp.filmorate.exeption;

public class ValidationException extends RuntimeException {
    public ValidationException(final String message) {
        super(message);
    }
}
