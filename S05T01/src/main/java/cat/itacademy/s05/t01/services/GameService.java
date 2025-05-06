package cat.itacademy.s05.t01.services;

import cat.itacademy.s05.t01.dto.PlayRequest;
import cat.itacademy.s05.t01.model.cards.Card;
import cat.itacademy.s05.t01.model.cards.Hand;
import cat.itacademy.s05.t01.model.game.Game;
import cat.itacademy.s05.t01.model.persons.Dealer;
import cat.itacademy.s05.t01.model.persons.Player;
import cat.itacademy.s05.t01.repository.GameRepository;
import cat.itacademy.s05.t01.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {
    @Autowired
    GameRepository gameRepository;
    PlayerRepository playerRepository;
    PlayRequest playRequest;
    MoveHandler moveHandler;

    public Mono<Game> newGame(Game game){
        List<Card> deck = moveHandler.shuffleDeck();

        Player player = new Player();
        Dealer dealer = new Dealer();

        List<Card> playerCards = List.of(deck.removeFirst(), deck.removeFirst());
        List<Card> dealerCards = List.of(deck.removeFirst(), deck.removeFirst());

        player.setHand(new Hand(playerCards));
        dealer.setHand(new Hand(dealerCards));

        game.setDealerId(player.getId());
        game.setPlayerId(dealer.getId());



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
}
