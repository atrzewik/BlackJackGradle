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
    public void shouldNotDrawCards() {
        croupier.addCardToHand(new Card(Sign.KING, Color.HEART));
        croupier.addCardToHand(new Card(Sign.FOUR, Color.CLUB));
        assertFalse(croupier.shouldDrawCards());
    }

    @Test
    public void getMoneyFromPlayerIfDoubleDown() {
        Player player = new Player("Aga", 50);
        player.setBetValue(20);
        croupier.getMoneyFromPlayerIfDoubleDown(player);
        assertEquals(20, croupier.getCasino());
        assertEquals(40, player.getBetValue());
        assertEquals(30, player.getCash());
    }

    @Test
    public void getMoneyFromPlayer() {
        Player player = new Player("Aga", 50);
        player.setBetValue(20);
        croupier.getMoneyFromPlayer(player);
        assertEquals(20, croupier.getCasino());
        assertEquals(20, player.getBetValue());
        assertEquals(30, player.getCash());
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
    public void cardsToPrint() {
        assertEquals("[ACE HEART]", croupier.cardsToPrint().toString());
    }

    @Test
    public void countScore() {
        assertEquals(13, croupier.countScore());
    }

    @Test
    public void checkIfNotBuster() {
        assertFalse(croupier.checkIfBuster());
    }

    @Test
    public void checkIfBuster() {
        croupier.addCardToHand(new Card(Sign.KING, Color.HEART));
        croupier.addCardToHand(new Card(Sign.QUEEN, Color.CLUB));
        assertTrue(croupier.checkIfBuster());
    }
}