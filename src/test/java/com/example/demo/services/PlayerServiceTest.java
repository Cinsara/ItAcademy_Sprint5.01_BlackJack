package com.example.demo.services;

import com.example.demo.exceptions.PlayerNotFoundException;
import com.example.demo.model.Player;
import com.example.demo.repositories.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.bson.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class PlayerServiceTest {
    private final PlayerRepository playerRepository;
    private final PlayerService playerService;

    @Autowired
    PlayerServiceTest(PlayerRepository playerRepository, PlayerService playerService) {
        this.playerRepository = playerRepository;
        this.playerService = playerService;
    }

    @Test
    void createPlayer() {
        playerRepository.deleteAll();

        Player player = new Player(null, "Alice", 0, 0,450);
        Player savedPlayer = playerService.createPlayer(player).block();

        assertNotNull(savedPlayer.getId());
        assertEquals("Alice", savedPlayer.getName());
        assertEquals(0, savedPlayer.getGamesPlayed());
        assertEquals(0, savedPlayer.getGamesWon());

        Player retrievedPlayer = playerService.getPlayerById(Integer.valueOf(savedPlayer.getId())).block();
        assertNotNull(retrievedPlayer);
        assertEquals("Alice", retrievedPlayer.getName());
    }

    @Test
    void getPlayerById() {
        playerRepository.deleteAll();

        Player player = playerService.createPlayer(new Player(null, "Grace", 7, 4,1002)).block();

        Player retrievedPlayer = playerService.getPlayerById(player.getId()).block();

        assertNotNull(retrievedPlayer);
        assertEquals("Grace", retrievedPlayer.getName());
        assertEquals(7, retrievedPlayer.getGamesPlayed());
        assertEquals(4, retrievedPlayer.getGamesWon());

    }

    @Test
    void getAllPlayers() {
        playerRepository.deleteAll().block();

        Player player1 = playerService.createPlayer(new Player(null, "Alice", 5, 2,500)).block();
        Player player2 = playerService.createPlayer(new Player(null, "Bob", 3, 1,320)).block();
        Player player3 = playerService.createPlayer(new Player(null, "Charlie", 8, 5,234)).block();

        List<Player> players = playerService.getAllPlayers().collectList().block();

        assertEquals(3, players.size());
        assertEquals("Alice", players.get(0).getName());
        assertEquals("Bob", players.get(1).getName());
        assertEquals("Charlie", players.get(2).getName());

    }

    @Test
    void deletePlayer() {
        playerRepository.deleteAll().block();
        Player player = playerService.createPlayer(new Player(null, "Dave", 4, 2,234)).block();
        playerService.deletePlayer(Integer.valueOf(player.getId())).block();
        assertThrows(PlayerNotFoundException.class, () -> playerService.getPlayerById(Integer.valueOf(player.getId())).block());
    }

    @Test
    void updatePlayerName() {
        playerRepository.deleteAll();
        Player player = playerService.createPlayer(new Player(null, "Charlie", 5,
                2,456)).block();

        player.setGamesPlayed(player.getGamesPlayed() + 1);
        player.setGamesWon(player.getGamesWon() + 1);
        Player updatedPlayer = playerService.createPlayer(player).block();

        assertEquals(6, updatedPlayer.getGamesPlayed());
        assertEquals(3, updatedPlayer.getGamesWon());
    }

    @Test
    void getRanking() {
        playerRepository.deleteAll().block();

        playerService.createPlayer(new Player(null, "Dave", 10, 8,785)).block();
        playerService.createPlayer(new Player(null, "Eve", 15, 12,123)).block();
        playerService.createPlayer(new Player(null, "Frank", 5, 3,45)).block();

        List<Player> ranking = playerService.getRanking().collectList().block();

        assertNotNull(ranking);
        assertEquals(3, ranking.size());
        assertEquals("Eve", ranking.get(0).getName());
        assertEquals("Dave", ranking.get(1).getName());
        assertEquals("Frank", ranking.get(2).getName());

    }
}