package com.example.demo.services;

import com.example.demo.dto.GameRequest;
import com.example.demo.model.Deck;
import com.example.demo.model.Game;
import com.example.demo.model.Player;
import com.example.demo.repositories.GameRepository;
import com.example.demo.repositories.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GameServiceTest {
    private final GameService gameService;
    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;

    @Autowired
    GameServiceTest(GameService gameService, PlayerRepository playerRepository,
                    GameRepository gameRepository) {
        this.gameService = gameService;
        this.playerRepository = playerRepository;
        this.gameRepository = gameRepository;
    }

    @BeforeEach
    void cleanAll(){
        playerRepository.deleteAll().block();
        gameRepository.deleteAll().block();
    }

    @Test
    void newGame() {
        GameRequest gameRequest = new GameRequest();
        gameRequest.setPlayerName("Carlos");

        gameService.newGame(gameRequest)
                .as(StepVerifier::create)
                .assertNext(game -> {
                    assertThat(game.getPlayerId()).isNotNull();
                    assertThat(game.getPlayerHand()).hasSize(2);
                    assertThat(game.getDealerHand()).hasSize(2);
                    assertThat(game.getStatus()).isEqualTo("IN_PROGRESS");
                    assertThat(game.getBetAmount()).isEqualTo(10.0);
                })
                .verifyComplete();
        playerRepository.findByName("Carlos")
                .as(StepVerifier::create)
                .assertNext(player -> {
                    assertThat(player.getGamesPlayed()).isEqualTo(1);
                })
                .verifyComplete();
    }

    @Test
    void getGameById() {
        Player newPlayer = new Player(null, "Oscar", 12, 5,790);
        playerRepository.save(newPlayer).block();

        Deck deck = new Deck();
        Game game = new Game();

        game.setPlayerId(newPlayer.getId().toString());
        game.setBetAmount(10.0);
        game.setPlayerHand(List.of(deck.drawCard(), deck.drawCard()));
        game.setDealerHand(List.of(deck.drawCard(), deck.drawCard()));
        game.setStatus("IN_PROGRESS");
        Game savedGame = gameRepository.save(game).block();
        assertThat(savedGame).isNotNull();
        assertThat(savedGame.getId()).isNotNull();

        gameService.getGameById(savedGame.getId())
                .as(StepVerifier::create)
                .assertNext(gameFound -> {
                    assertThat(gameFound.getId()).isEqualTo(game.getId());
                    assertThat(gameFound.getPlayerId()).isEqualTo(game.getPlayerId());
                    assertThat(gameFound.getStatus()).isEqualTo(game.getStatus());
                })
                .verifyComplete();
    }

    @Test
    void deleteGame() {
        Player newPlayer = new Player(null, "Oscar", 12, 5,790);
        playerRepository.save(newPlayer).block();

        Deck deck = new Deck();
        Game game = new Game();

        game.setPlayerId(newPlayer.getId().toString());
        game.setBetAmount(10.0);
        game.setPlayerHand(List.of(deck.drawCard(), deck.drawCard()));
        game.setDealerHand(List.of(deck.drawCard(), deck.drawCard()));
        game.setStatus("IN_PROGRESS");
        Game savedGame = gameRepository.save(game).block();
        assertThat(savedGame).isNotNull();
        assertThat(savedGame.getId()).isNotNull();

        gameService.deleteGame(savedGame.getId()).block();
        gameService.getGameById(savedGame.getId())
                .as(StepVerifier::create)
                .expectComplete()
                .verify();
    }

    @Test
    void playGame() {
        Player player = new Player(null, "TestPlayer", 0, 0, 100.0);
        Player savedPlayer = playerRepository.save(player).block();

        Deck deck = new Deck();
        Game game = new Game();
        game.setPlayerId(savedPlayer.getId().toString());
        game.setPlayerHand(List.of(deck.drawCard(), deck.drawCard()));
        game.setDealerHand(List.of(deck.drawCard(), deck.drawCard()));
        game.setBetAmount(10.0);
        game.setStatus("IN_PROGRESS");

        Game savedGame = gameRepository.save(game).block();

        GameAction action = new GameAction();
        action.setActionType("HIT");

        gameService.playGame(savedGame.getId(), action)
                .as(StepVerifier::create)
                .assertNext(updatedGame -> {
                    assertThat(updatedGame.getId()).isEqualTo(savedGame.getId());
                    assertThat(updatedGame.getPlayerHand().size()).isGreaterThan(2);
                    assertThat(updatedGame.getStatus()).isNotNull();
                })
                .verifyComplete();
    }
}