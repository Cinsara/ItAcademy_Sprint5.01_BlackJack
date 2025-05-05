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
    private List<Player> playerList;
    private List<Card> deck;

    public Game() {}

    public Game(String id, String playerId, String dealerId, GameStatus status, List<Player> playerList, List<Card> deck) {
        this.id = id;
        this.playerId = playerId;
        this.dealerId = dealerId;
        this.status = status;
        this.playerList = playerList;
        this.deck = deck;
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

    public List<Player> getPlayerList() {
        return playerList;
    }

    public List<Card> getDeck() {
        return deck;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public void setDeck(List<Card> deck) {
        this.deck = deck;
    }
}
