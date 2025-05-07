package cat.itacademy.s05.t01.services;

import cat.itacademy.s05.t01.dto.PlayRequest;
import cat.itacademy.s05.t01.dto.StartGameRequest;
import cat.itacademy.s05.t01.model.cards.Card;
import cat.itacademy.s05.t01.model.cards.Hand;
import cat.itacademy.s05.t01.model.game.Game;
import cat.itacademy.s05.t01.model.game.GameStatus;
import cat.itacademy.s05.t01.model.persons.Dealer;
import cat.itacademy.s05.t01.model.persons.Player;
import cat.itacademy.s05.t01.repository.GameRepository;
import cat.itacademy.s05.t01.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final MoveHandler moveHandler;
    private final PlayerRepository playerRepository;
    private final DealerService dealerService;

    @Autowired
    public GameService(GameRepository gameRepository, MoveHandler moveHandler,
                       PlayerRepository playerRepository, DealerService dealerService) {
        this.gameRepository = gameRepository;
        this.moveHandler = moveHandler;
        this.playerRepository = playerRepository;
        this.dealerService = dealerService;
    }

  public Mono<Game> newGame(StartGameRequest startGameRequest){
        List<Card> deck = moveHandler.shuffleDeck();

        Mono<Player> player = savePlayer(startGameRequest);
        Dealer dealer = dealerService.getDefaultDealer();

        List<Card> dealerCards = List.of(deck.removeFirst(), deck.removeFirst());
        dealer.setHand(new Hand(dealerCards));

        Game newGame = new Game();

        newGame.setDealerId(dealer.getId());
        newGame.setPlayerId(String.valueOf(player.retry()));
        newGame.setInitialBet(startGameRequest.getInitialBet());
        newGame.setStatus(GameStatus.ONGOING);
        newGame.setDeck(deck);

        return gameRepository.save(newGame);
    }

    private Mono<Player> savePlayer(StartGameRequest startGameRequest){
        List<Card> deck = moveHandler.shuffleDeck();

        Player player = new Player();
        player.setHand(new Hand(List.of(deck.removeFirst(), deck.removeFirst())));
        player.setName(startGameRequest.getPlayerName());

        return playerRepository.save(player);
    }

    private Mono<Game> saveGame(StartGameRequest request, Player player, Dealer dealer) {
        List<Card> deck = moveHandler.shuffleDeck();

        deck.removeFirst(); deck.removeFirst();
        deck.removeFirst(); deck.removeFirst();

        Game game = new Game();
        game.setPlayerId(player.getId());
        game.setDealerId(dealer.getId());
        game.setInitialBet(request.getInitialBet());
        game.setStatus(GameStatus.ONGOING);
        game.setDeck(deck);

        return gameRepository.save(game);
    }

    public Mono<Game> getOneGame(String id){
        return gameRepository.findById(id);
    }

    public Mono<Game> play(String gameId, PlayRequest playRequest){
        return gameRepository.findById(gameId)
                .switchIfEmpty(Mono.error(new RuntimeException("Game not found")))
                .flatMap(game -> {
                    String move = playRequest.getMoveType().toLowerCase();
                    return switch (move) {
                        case "hit" -> moveHandler.hit(game,playRequest);
                        case "stand" -> moveHandler.stand(game,playRequest);
                        default -> Mono.error(new RuntimeException("Unknown move type: " + move));
                    };
                });
    }

    public Mono<Void> delete(String id){
        return gameRepository.deleteById(id);
    }
}
