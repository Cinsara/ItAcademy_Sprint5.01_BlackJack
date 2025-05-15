package com.example.demo.services;

import com.example.demo.dto.NameUpdateRequest;
import com.example.demo.exceptions.PlayerNotFoundException;
import com.example.demo.model.Player;
import com.example.demo.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Mono<Player> createPlayer(Player player) {
        return playerRepository.save(player);
    }

    public Mono<Player> getPlayerById(Integer id) {
        return playerRepository.findById(id)
                .switchIfEmpty(Mono.error(new PlayerNotFoundException("The player with ID " + id + " doesn't exist.")));
    }

    public Flux<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Mono<Void> deletePlayer(Integer id) {
        return playerRepository.deleteById(id);
    }

    public Mono<Player> updatePlayerName(Integer playerId, NameUpdateRequest newName) {
        return playerRepository.findById(playerId)
                .flatMap(player -> {
                    player.setName(newName.getName());
                    return playerRepository.save(player);
                });
    }

    public Flux<Player> getRanking() {
        return playerRepository.findAllByOrderByBalanceDesc();
    }
}

