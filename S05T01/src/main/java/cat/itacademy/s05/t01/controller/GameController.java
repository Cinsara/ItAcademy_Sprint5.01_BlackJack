package cat.itacademy.s05.t01.controller;

import cat.itacademy.s05.t01.dto.PlayRequest;
import cat.itacademy.s05.t01.dto.StartGameRequest;
import cat.itacademy.s05.t01.model.game.Game;
import cat.itacademy.s05.t01.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/blackjack")
public class GameController {
    @Autowired
    GameService gameService;

    @PostMapping("/game/new")
    public Mono<Game> startGame(@RequestBody StartGameRequest startGameRequest){
        return gameService.newGame(startGameRequest);
    }

    @GetMapping("/game/{id}")
    public Mono<ResponseEntity<Game>> findOneGame(@PathVariable("id") String id){
        return gameService.getOneGame(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/game/{id}/play")
    public Mono<Game> play(@RequestBody PlayRequest playRequest, String id){
        return gameService.play(id,playRequest);
    }

    @DeleteMapping("/game/{id}/delete")
    public Mono<Void> deleteGame(@PathVariable("id") String id){
        return gameService.delete(id);
    }
}
