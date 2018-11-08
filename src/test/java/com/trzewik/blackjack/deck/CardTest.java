package com.trzewik.blackjack.deck;

import com.trzewik.blackjack.deck.enums.Color;
import com.trzewik.blackjack.deck.enums.Sign;
import org.junit.Test;

import static org.junit.Assert.*;

public class CardTest {
    private Card card = new Card(Sign.ACE, Color.HEART);

    
    @Test
    public void getValue() {
        assertEquals(1, card.getValue());
    }

    @Test
    public void getSign() {
        assertEquals(Sign.ACE, card.getSign());
    }
}