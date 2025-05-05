package cat.itacademy.s05.t01.exceptions;

public class GameNotFoundException extends RuntimeException{
    public GameNotFoundException(String gameId) {
        super("Game not found with the ID " + gameId);
    }
}
