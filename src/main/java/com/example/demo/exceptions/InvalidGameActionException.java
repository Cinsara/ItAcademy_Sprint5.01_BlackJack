package com.example.demo.exceptions;

public class InvalidGameActionException extends RuntimeException {
    public InvalidGameActionException(String message) {
        super(message);
    }
}
