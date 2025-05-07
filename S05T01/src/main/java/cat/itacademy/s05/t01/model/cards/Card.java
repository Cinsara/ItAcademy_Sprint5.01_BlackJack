package cat.itacademy.s05.t01.model.cards;

public class Card {
    private Ranks rank;
    private  Suits suit;

    public Card(){
    }

    public Card( Ranks rank, Suits suit){
        this.rank = rank;
        this.suit = suit;
    }

    public Ranks getRank() {
        return rank;
    }

    public Suits getSuit() {
        return suit;
    }

    public int getValue(){
        return rank.getValue();
    }

    @Override
    public String toString(){
        return "The card is: " + rank.getValue() + " " + suit.name();
    }
}
