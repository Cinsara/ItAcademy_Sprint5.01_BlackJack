package com.example.demo.services;

import com.example.demo.dto.GameRequest;
import com.example.demo.model.Deck;

import com.example.demo.model.Game;
import com.example.demo.repositories.GameRepository;
import com.example.demo.repositories.PlayerRepository;
import com.example.demo.utils.BlackjackUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;

    @Autowired
    public GameService(GameRepository gameRepository, PlayerRepository playerRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }

    public Mono<Game> newGame(GameRequest request) {
        return playerRepository.findOrCreatePlayer(request.getPlayerName())
                .flatMap(player -> {
                    player.setGamesPlayed(player.getGamesPlayed() + 1);
                    return playerRepository.save(player);
                })
                .flatMap(player -> {
                    Game game = new Game();
                    game.setPlayerId(String.valueOf(player.getId()));
                    game.setBetAmount(10.0);

                    Deck deck = new Deck();
                    game.setPlayerHand(List.of(deck.drawCard(), deck.drawCard()));
                    game.setDealerHand(List.of(deck.drawCard(), deck.drawCard()));
                    game.setStatus("IN_PROGRESS");

                    return gameRepository.save(game);
                });
    }

    public Mono<Game> getGameById(String gameId) {
        return gameRepository.findById(gameId);
    }

    public Mono<Void> deleteGame(String gameId) {
        return gameRepository.deleteById(gameId);
    }

    public Mono<Game> playGame(String gameId, GameAction action) {
        return gameRepository.findById(gameId)
                .flatMap(game -> {
                    Deck deck = new Deck();
                    switch (action.getActionType()) {
                        case "HIT":
                          if(!"IN_PROGRESS".equals(game.getStatus())){
                              return Mono.error(new IllegalStateException("The game is over"));
                            }
                            game.getPlayerHand().add(deck.drawCard());
                            break;
                        case "STAND":
                            while (BlackjackUtils.calculateHandValue(game.getDealerHand()) < 17) {
                                game.getDealerHand().add(deck.drawCard());
                            }
                            break;
                    }
                    game.setStatus(BlackjackUtils.checkGameResult(game));
                    return gameRepository.save(game)
                            .flatMap(savedGame -> {
                                if ("PLAYER_WINS".equals(savedGame.getStatus())) {
                                    return playerRepository.findById(Integer.valueOf(savedGame.getPlayerId()))
                                            .flatMap(player -> {
                                                player.setBalance(player.getBalance() + (2 * savedGame.getBetAmount()));
                                                player.setGamesWon(player.getGamesWon() + 1);
                                                return playerRepository.save(player);
                                            })
                                            .thenReturn(savedGame);
                                }
                                return Mono.just(savedGame);
                            });
                });
    }
}