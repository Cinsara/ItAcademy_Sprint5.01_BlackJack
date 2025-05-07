package cat.itacademy.s05.t01.dto;

public class StartGameRequest {
    private int initialBet;
    private String playerName;

    public StartGameRequest(){
    }

    public StartGameRequest(int initialBet, String playerName){
        this.initialBet = initialBet;
        this.playerName = playerName;
    }

    public int getInitialBet() {
        return initialBet;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setInitialBet(int initialBet) {
        this.initialBet = initialBet;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
