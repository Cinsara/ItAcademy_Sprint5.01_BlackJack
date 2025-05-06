package cat.itacademy.s05.t01.dto;

public class StartGameRequest {
    private int initialBet;

    public StartGameRequest(){
    }

    public StartGameRequest(int initialBet){
        this.initialBet = initialBet;
    }

    public int getInitialBet() {
        return initialBet;
    }

    public void setInitialBet(int initialBet) {
        this.initialBet = initialBet;
    }
}
