package com.trzewik.blackjack.deck;

import com.trzewik.blackjack.deck.enums.Color;
import com.trzewik.blackjack.deck.enums.Sign;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> listOfCards = new ArrayList<>();

    public Deck(){
        for (Sign s : Sign.values()){
            for (Color c : Color.values()){
                Card card = new Card(s, c);
                this.listOfCards.add(card);
            }
        }
        shuffleDeck();
    }

    private ArrayList<Card> shuffleDeck(){
        Collections.shuffle(this.listOfCards);
        return listOfCards;
    }

    public Card getCard(){
        Card card = this.listOfCards.get(0);
        deleteCardFromDeck(card);
        return card;
    }

    private void deleteCardFromDeck(Card card){
        this.listOfCards.remove(card);
    }
}
