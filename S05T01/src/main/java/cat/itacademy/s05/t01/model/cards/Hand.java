package cat.itacademy.s05.t01.model.cards;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private final List<Card> cards;

    public Hand(){
        this.cards = new ArrayList<>();
    }

    public List<Card> getCards() {
        return cards;
    }

    public void addCard(Card card){
        cards.add(card);
    }

    public int getValue(){
        int total = 0;
        int aces = 0;
        for(Card c : cards){
            total+= c.getValue();
            if(c.getRank() == Ranks.A) aces++;
        }
        while(total > 21 && aces > 0){
            total -= 10;
            aces--;
        }
        return total;
    }

    public boolean isBust(){
        return getValue() > 21;
    }

    public void clear(){
        cards.clear();
    }

    @Override
    public String toString(){
        return "Hand: " + cards + ".";
    }
}
