package com.trzewik.blackjack.deck;

import com.trzewik.blackjack.deck.enums.Color;
import com.trzewik.blackjack.deck.enums.Sign;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CardTest {
    private Card card;

    @Before
    public void initialize() {
        card = new Card(Sign.ACE, Color.HEART);
    }

    @Test
    public void getValue() {
        assertEquals(1, card.getValue());
    }

    @Test
    public void getSign() {
        assertEquals(Sign.ACE, card.getSign());
    }
}