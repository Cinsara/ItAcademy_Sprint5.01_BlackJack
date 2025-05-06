package cat.itacademy.s05.t01.controller;

import cat.itacademy.s05.t01.model.persons.Player;
import cat.itacademy.s05.t01.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/blackjack")
public class PlayerController {
    @Autowired
    PlayerService playerService;

    @GetMapping("/ranking")
    public Flux<Player> playersRanking(){
        return playerService.getRanking();
    }

    @PutMapping("/player/{playerId}")
    public Mono<Player> changePlayerName(@PathVariable("id") String id, @RequestBody String name){
        return playerService.changeName(id,name);
    }
}
