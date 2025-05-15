package com.example.demo.repositories;

import com.example.demo.model.Player;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface PlayerRepository extends ReactiveCrudRepository<Player, Integer> {
    Flux<Player> findAllByOrderByBalanceDesc();

    Mono<Player> findByName(String name);

    default Mono<Player> findOrCreatePlayer(String name) {
        return findByName(name)
                .switchIfEmpty(Mono.defer(() -> {
                    Player newPlayer = new Player();
                    newPlayer.setName(name);
                    newPlayer.setBalance(100.00);
                    newPlayer.setGamesPlayed(0);
                    newPlayer.setGamesWon(0);
                    return save(newPlayer);
                }));
    }
}
