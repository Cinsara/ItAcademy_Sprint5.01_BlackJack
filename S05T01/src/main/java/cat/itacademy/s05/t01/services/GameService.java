package cat.itacademy.s05.t01.services;

import cat.itacademy.s05.t01.model.cards.Card;
import cat.itacademy.s05.t01.model.cards.Ranks;
import cat.itacademy.s05.t01.model.cards.Suits;
import cat.itacademy.s05.t01.model.game.Game;
import cat.itacademy.s05.t01.model.persons.Player;
import cat.itacademy.s05.t01.repository.GameRepository;
import cat.itacademy.s05.t01.repository.PlayerRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;

    public GameService(GameRepository gameRepository, PlayerRepository playerRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }

    public Mono<Game> createNewGame(String playerName) {
        Player player = new Player(UUID.randomUUID().toString(), playerName, "", 0, true);
        playerRepository.save(player);

        Game game = new Game();
        game.setPlayerId(player.getId());
        game.setDeck(createShuffledDeck());
        game.setStatus(GameStatus.IN_PROGRESS);
        game.setCreatedAt(Instant.now());

        return gameRepository.save(game);
    }

    private List<Card> createShuffledDeck() {
        List<Card> deck = new ArrayList<>();
        for (Suits suit : Suits.values()) {
            for (Ranks rank : Ranks.values()) {
                deck.add(new Card(rank, suit));
            }
        }
        Collections.shuffle(deck);
        return deck;
    }

    public Mono<Game> playerHit(String gameId) {
        return gameRepository.findById(gameId)
                .flatMap(game -> {
                    return gameRepository.save(game);
                });
    }
}
