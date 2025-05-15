package com.example.demo.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter

public class Deck {
    private final List<Card> cards;

    public Deck() {
        this.cards = generateDeck();
        shuffleDeck();
    }

    private List<Card> generateDeck(){
        List<Card> deck = new ArrayList<>();
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        for(String suit : suits){
            for(String rank : ranks){
                deck.add(new Card(suit,rank));
            }
        }
        return deck;
    }

    private void shuffleDeck(){
        Collections.shuffle(cards);
    }

    public Card drawCard(){
        return cards.isEmpty() ? null : cards.remove(0);
    }
}
