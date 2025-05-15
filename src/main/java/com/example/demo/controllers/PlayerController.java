package com.example.demo.controllers;

import com.example.demo.dto.NameUpdateRequest;
import com.example.demo.model.Player;
import com.example.demo.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/players")
public class PlayerController {
    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping("/newPlayer")
    public Mono<ResponseEntity<Player>> createPlayer(@RequestBody Player player) {
        return playerService.createPlayer(player)
                .map(savedPlayer -> ResponseEntity.status(HttpStatus.CREATED).body(savedPlayer));
    }

    @GetMapping("/{playerId}")
    public Mono<ResponseEntity<Player>> getPlayerById(@PathVariable Integer playerId) {
        return playerService.getPlayerById(playerId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/allPlayers")
    public Flux<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @DeleteMapping("/{playerId}")
    public Mono<Void> deletePlayer(@PathVariable Integer playerId) {
        return playerService.deletePlayer(playerId);
    }

    @PutMapping("/{playerId}")
    public Mono<ResponseEntity<Player>> updateName(@PathVariable Integer playerId, @RequestBody NameUpdateRequest newName) {
        return playerService.updatePlayerName(playerId, newName)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/ranking")
    public Flux<Player> getRanking() {
        return playerService.getRanking();
    }
}
