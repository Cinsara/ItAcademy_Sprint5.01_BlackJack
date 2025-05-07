package cat.itacademy.s05.t01.model.persons;

import org.springframework.data.relational.core.mapping.Table;

@Table(name = "players")
public class Player extends Person {
    private int score;
    private boolean connected;

    public Player(){
    }

    public Player(String id, String name, String email, int score, boolean connected){
        super(id, name, email);
        this.score = score;
        this.connected = connected;
    }

    public int getScore() {
        return score;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    @Override
    public String toString(){
        return "\n--- PLAYER ----" +
                "\n- ID: " + getId() +
                "\n- Name: " + getName() +
                "\n- Email: " + getEmail() +
                "\n- Score: " + this.score +
                "\n- Active: " + this.connected;
    }
}
