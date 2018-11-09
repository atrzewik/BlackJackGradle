package com.trzewik.blackjack.players;

import com.trzewik.blackjack.deck.Card;
import com.trzewik.blackjack.deck.enums.Color;
import com.trzewik.blackjack.deck.enums.Sign;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CroupierTest {
    private Croupier croupier;

    @Before
    public void initialize() {
        croupier = new Croupier();
        croupier.addCardToHand(new Card(Sign.ACE, Color.HEART));
        croupier.addCardToHand(new Card(Sign.TWO, Color.CLUB));
    }

    @Test
    public void shouldDrawCards() {
        assertTrue(croupier.shouldDrawCards());
    }

    @Test
    public void getMoneyFromPlayerIfDoubleDown() {
    }

    @Test
    public void getMoneyFromPlayer() {
    }

    @Test
    public void setCash() {
        croupier.setCash(20);
        assertEquals(20, croupier.getCash());
    }

    @Test
    public void setPosition() {
        croupier.setPosition(1);
        assertEquals((Integer) 1, croupier.getPosition());
    }

    @Test
    public void countScore() {
        assertEquals(13, croupier.countScore());
    }

    @Test
    public void checkIfBuster() {
        assertFalse(croupier.checkIfBuster());
    }
}