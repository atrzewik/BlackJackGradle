package com.trzewik.blackjack.players;

import com.trzewik.blackjack.deck.Card;
import com.trzewik.blackjack.deck.enums.Color;
import com.trzewik.blackjack.deck.enums.MoveType;
import com.trzewik.blackjack.deck.enums.Sign;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    private Player player;

    @Before
    public void initialize() {
        player = new Player("Aga", 50);
        player.setBetValue(20);
        player.addCardToHand(new Card(Sign.KING, Color.CLUB));
        player.addCardToHand(new Card(Sign.QUEEN, Color.CLUB));
    }

    @Test
    public void setBetValue() {
        assertEquals(20, player.getBetValue());
    }

    @Test
    public void setLastMove() {
        player.setLastMove(MoveType.DOUBLE_DOWN);
        assertEquals(MoveType.DOUBLE_DOWN, player.getLastMove());
    }

    @Test
    public void payBetValue() {
        player.payBetValue();
        assertEquals(30, player.getCash());
    }

    @Test
    public void doubleBetValue() {
        player.doubleBetValue();
        assertEquals(40, player.getBetValue());
    }

    @Test
    public void hasPossibleMove() {
        player.setLastMove(MoveType.HIT);
        assertTrue(player.hasPossibleMove());
    }

    @Test
    public void hasNotPossibleMove() {
        player.setLastMove(MoveType.DOUBLE_DOWN);
        assertFalse(player.hasPossibleMove());
    }

    @Test
    public void setCash() {
        player.setCash(60);
        assertEquals(60, player.getCash());
    }

    @Test
    public void setPosition() {
        player.setPosition(1);
        assertEquals((Integer) 1, player.getPosition());
    }


    @Test
    public void addCardToHand() {
        assertEquals("[KING CLUB, QUEEN CLUB]", player.getHand().toString());
    }


    @Test
    public void cardsToPrint() {
        assertEquals("[KING CLUB, QUEEN CLUB]", player.cardsToPrint().toString());
    }

    @Test
    public void countScore() {
        assertEquals(20, player.countScore());
    }

    @Test
    public void countScoreWithAce() {
        player.addCardToHand(new Card(Sign.ACE, Color.CLUB));
        assertEquals(21, player.countScore());
    }
}