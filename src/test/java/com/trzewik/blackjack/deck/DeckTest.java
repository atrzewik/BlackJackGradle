package com.trzewik.blackjack.deck;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
    public void shouldGetAllCards() {
        IntStream.range(0, 52)
                .forEach(i -> deck.getCard());
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldNotGetAllCards(){
        thrown.expect(Exception.class);
        IntStream.range(0, 53)
                .forEach(i -> deck.getCard());
    }

}