package com.trzewik.blackjack.deck;

import com.trzewik.blackjack.deck.enums.Color;
import com.trzewik.blackjack.deck.enums.Sign;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private List<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>();
        for (Sign s : Sign.values()) {
            for (Color c : Color.values()) {
                Card card = new Card(s, c);
                this.cards.add(card);
            }
        }
        shuffleDeck();
    }

    private void shuffleDeck() {
        Collections.shuffle(this.cards);
    }

    public Card getCard() {
        Card card = this.cards.get(0);
        deleteCardFromDeck(card);
        return card;
    }

    private void deleteCardFromDeck(Card card) {
        this.cards.remove(card);
    }
}