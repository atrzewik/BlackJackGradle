package com.trzewik.blackjack.deck;

import org.junit.Test;

import static org.junit.Assert.*;

public class DeckTest {
    private Deck deck = new Deck();
    private Card firstCardInDeck = deck.cards.get(0);

    @Test
    public void getCard() {
        assertEquals(firstCardInDeck,deck.getCard());
    }

    @Test
    public void checkIfCardNotInDeckAfterGetCard(){
        deck.getCard();
        assertFalse(deck.cards.contains(firstCardInDeck));
    }
}