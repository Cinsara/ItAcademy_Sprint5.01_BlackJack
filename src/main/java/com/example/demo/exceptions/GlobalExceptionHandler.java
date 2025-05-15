package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handGeneralException(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error: " + e.getMessage());
    }

    @ExceptionHandler(PlayerNotFoundException.class)
    public ResponseEntity<String> handlePlayerNotFound(PlayerNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Player not found: " + e.getMessage());
    }

    @ExceptionHandler(GameNotFoundException.class)
    public ResponseEntity<String> handleGameNotFound(GameNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Game not found: " + e.getMessage());
    }

    @ExceptionHandler(InvalidGameActionException.class)
    public ResponseEntity<String> handleInvalidGame(InvalidGameActionException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Invalid game action: " + e.getMessage());
    }
}
