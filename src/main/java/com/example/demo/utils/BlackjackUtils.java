package com.example.demo.utils;

import com.example.demo.model.Card;
import com.example.demo.model.Game;

import java.util.List;

public class BlackjackUtils {
    public static int calculateHandValue(List<Card> hand){
        int total = 0;
        int aceCount = 0;

        for(Card card : hand){
            switch (card.getRank()){
                case "J":
                case "Q":
                case "K":
                    total += 10;
                    break;
                case "A":
                    aceCount++;
                    total += 11;
                    break;
                default:
                    total += Integer.parseInt(card.getRank());
            }
        }
        while(total > 21 && aceCount > 0){
            total -= 10;
            aceCount--;
        }
        return total;
    }

    public static String checkGameResult(Game game){
        int playerScore = calculateHandValue(game.getPlayerHand());
        int dealerScore = calculateHandValue(game.getDealerHand());

        if (playerScore > 21) return "PLAYER_BUST";
        if (dealerScore > 21) return "DEALER_BUST";
        if (playerScore == 21 && game.getPlayerHand().size() == 2) return "BLACKJACK";
        if (playerScore > dealerScore) return "PLAYER_WINS";
        if (playerScore < dealerScore) return "DEALER_WINS";

        return "DRAW";
    }
}
