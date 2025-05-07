package cat.itacademy.s05.t01.services;

import cat.itacademy.s05.t01.dto.PlayRequest;
import cat.itacademy.s05.t01.model.cards.Card;
import cat.itacademy.s05.t01.model.cards.Ranks;
import cat.itacademy.s05.t01.model.cards.Suits;
import cat.itacademy.s05.t01.model.game.Game;
import cat.itacademy.s05.t01.model.game.GameStatus;
import cat.itacademy.s05.t01.repository.GameRepository;
import cat.itacademy.s05.t01.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class MoveHandler {
    @Autowired
    GameRepository gameRepository;
    PlayerRepository playerRepository;

    public List<Card> shuffleDeck(){
        List<Card> shuffledDeck = new ArrayList<>();
        for(Suits suits : Suits.values()){
            for(Ranks ranks : Ranks.values()){
                shuffledDeck.add(new Card(ranks,suits));
            }
        }
        Collections.shuffle(shuffledDeck);
        return shuffledDeck;
    }

    public Card drawCardFromDeck(List<Card> deck) {
        if (deck == null || deck.isEmpty()) {
            throw new RuntimeException("There are no cards in the deck");
        }
        return deck.removeFirst();
    }

    public Mono<Game> hit(Game game, PlayRequest playRequest) {
        return playerRepository.findById(game.getPlayerId())
                .switchIfEmpty(Mono.error(new RuntimeException("Player not found")))
                .flatMap(player -> {
                    List<Card> deck = game.getDeck();
                    if (deck.isEmpty()) {
                        return Mono.error(new RuntimeException("Deck is empty"));
                    }

                    Card drawnCard = drawCardFromDeck(deck);
                    player.getHand().addCard(drawnCard);

                    if (player.getHand().isBust()) {
                        game.setStatus(GameStatus.valueOf("PLAYER_BUST"));
                        game.setWinner("DEALER");
                    }

                    return playerRepository.save(player)
                            .then(gameRepository.save(game));
                });
    }

    public Mono<Game> stand(Game game, PlayRequest playRequest) {
        return Mono.just(game);
    }
}
