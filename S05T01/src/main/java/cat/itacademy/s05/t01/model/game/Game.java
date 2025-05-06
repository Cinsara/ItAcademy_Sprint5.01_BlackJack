package cat.itacademy.s05.t01.model.game;

import cat.itacademy.s05.t01.model.cards.Card;
import cat.itacademy.s05.t01.model.persons.Player;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "games")
@Table(name = "games")
public class Game {
    @Id
    private String id;
    private String playerId;
    private String dealerId;
    private GameStatus status;
    private List<Card> deck;
    private String winner;

    public Game() {}

    public Game(String id, String playerId, String dealerId, GameStatus status, List<Card> deck, String winner) {
        this.id = id;
        this.playerId = playerId;
        this.dealerId = dealerId;
        this.status = status;
        this.deck = deck;
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

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
