package com.trzewik.blackjack.deck;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class DeckTest {

    private Deck deck;

    @Before
    public void initialize() {
        deck = new Deck();
    }

    @Test
    public void getCard() {
        List<Card> cards = new ArrayList<>();
        IntStream.range(0, 52)
                .forEach(i -> cards.add(deck.getCard()));
        assertEquals(52, cards.size());
    }
}