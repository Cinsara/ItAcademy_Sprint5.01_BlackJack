package cat.itacademy.s05.t01.model.game;

import cat.itacademy.s05.t01.model.cards.Card;
import cat.itacademy.s05.t01.model.cards.Hand;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "games")
public class Game {
    @Id
    private String id;
    private String playerId;
    private String dealerId;
    private Hand dealerHand;
    private Hand playerHand;
    private GameStatus status;
    private List<Card> deck;
    private int initialBet;
    private String winner;

    public Game() {}

    public Game(String id, String playerId, String dealerId, GameStatus status, List<Card> deck,
                int initialBet, String winner) {
        this.id = id;
        this.playerId = playerId;
        this.dealerId = dealerId;
        this.status = status;
        this.deck = deck;
        this.initialBet = initialBet;
        this.winner = winner;
    }

    public String getId() {
        return id;
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getDealerId() {
        return dealerId;
    }

    public GameStatus getStatus() {
        return status;
    }

    public List<Card> getDeck() {
        return deck;
    }

    public String getWinner() {
        return winner;
    }

    public int getInitialBet() {
        return initialBet;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public void setDeck(List<Card> deck) {
        this.deck = deck;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDealerId(String dealerId) {
        this.dealerId = dealerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public void setInitialBet(int initialBet) {
        this.initialBet = initialBet;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
