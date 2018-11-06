package com.trzewik.blackjack.deck;

import com.trzewik.blackjack.deck.enums.Color;
import com.trzewik.blackjack.deck.enums.Sign;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> Cards = new ArrayList<>();

    public Deck(){
        for (Sign s : Sign.values()){
            for (Color c : Color.values()){
                Card card = new Card(s, c);
                this.Cards.add(card);
            }
        }
        shuffleDeck();
    }

    private ArrayList<Card> shuffleDeck(){
        Collections.shuffle(this.Cards);
        return Cards;
    }

    public Card getCard(){
        Card card = this.Cards.get(0);
        deleteCardFromDeck(card);
        return card;
    }

    private void deleteCardFromDeck(Card card){
        this.Cards.remove(card);
    }
}
