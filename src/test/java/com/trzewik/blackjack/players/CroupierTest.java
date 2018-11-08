package com.trzewik.blackjack.players;

import com.trzewik.blackjack.deck.Card;
import com.trzewik.blackjack.deck.enums.Color;
import com.trzewik.blackjack.deck.enums.Sign;
import org.junit.Test;


import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CroupierTest {
    private Croupier croupier = new Croupier();
    {croupier.addCardToHand(new Card(Sign.ACE, Color.HEART));
    croupier.addCardToHand(new Card(Sign.TWO,Color.CLUB));}

    @Test
    public void getCasino() {
        assertEquals(0,croupier.getCasino());
    }

    @Test
    public void shouldDrawCards() {
        assertTrue(croupier.shouldDrawCards());
    }

    @Test
    public void getSingleCard() {
        assertSame(new Card(Sign.ACE,Color.HEART),croupier.getSingleCard());
    }

    @Test
    public void getMoneyFromPlayerIfDoubleDown() {
    }

    @Test
    public void getMoneyFromPlayer() {
    }

    @Test
    public void getName() {
        assertEquals("Croupier", croupier.getName());
    }

    @Test
    public void getCash() {
        assertEquals(0, croupier.getCash());
    }

    @Test
    public void setCash() {
        croupier.setCash(20);
        assertEquals(20, croupier.getCash());
    }

    @Test
    public void getPosition() {
        assertNull(croupier.getPosition());
    }

    @Test
    public void setPosition() {
        croupier.setPosition(1);
        assertEquals((Integer)1, croupier.getPosition());
    }

    @Test
    public void getHand() {
        List<Card> cards = Arrays.asList(new Card(Sign.ACE, Color.HEART),
                        new Card(Sign.TWO, Color.CLUB));
        assertSame(cards,croupier.getHand());
    }

    @Test
    public void countScore() {
        assertEquals(13,croupier.countScore());
    }

    @Test
    public void getBuster() {
        assertFalse(croupier.getBuster());
    }

    @Test
    public void setBuster() {
        croupier.setBuster(true);
        assertTrue(croupier.getBuster());
    }
}