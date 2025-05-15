package com.example.demo.controllers;

import com.example.demo.dto.GameRequest;
import com.example.demo.model.Game;
import com.example.demo.services.GameAction;
import com.example.demo.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/new")
    public Mono<ResponseEntity<Game>> newGame(@RequestBody GameRequest request) {
        if(request.getPlayerName() == null || request.getPlayerName().isBlank()) {
            return Mono.just(ResponseEntity.badRequest().build());
        }
        return gameService.newGame(request)
                .map(game -> ResponseEntity.status(HttpStatus.CREATED).body(game));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Game>> getGameById(@PathVariable String id) {
        return gameService.getGameById(id)
                .map(game -> ResponseEntity.ok(game))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/play")
    public Mono<ResponseEntity<Game>> playMove(@PathVariable String id, @RequestBody GameAction action) {
        return gameService.playGame(id, action)
                .map(updatedGame -> ResponseEntity.ok(updatedGame))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}/delete")
    public Mono<ResponseEntity<Void>> deleteGame(@PathVariable String id) {
        return gameService.deleteGame(id)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
