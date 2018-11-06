package com.trzewik.blackjack.deck;

import com.trzewik.blackjack.deck.enums.Color;
import com.trzewik.blackjack.deck.enums.Sign;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> cards = new ArrayList<>();

    public Deck(){
        for (Sign s : Sign.values()){
            for (Color c : Color.values()){
                Card card = new Card(s, c);
                this.cards.add(card);
            }
        }
        shuffleDeck();
    }

    private void shuffleDeck(){
        Collections.shuffle(this.cards);
    }

    public Card getCard(){
        Card card = this.cards.get(0);
        deleteCardFromDeck(card);
        return card;
    }

    private void deleteCardFromDeck(Card card){
        this.cards.remove(card);
    }
}
