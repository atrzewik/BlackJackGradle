package com.trzewik.blackjack.game;

import com.trzewik.blackjack.deck.Card;
import com.trzewik.blackjack.deck.enums.Color;
import com.trzewik.blackjack.deck.enums.MoveType;
import com.trzewik.blackjack.deck.enums.Sign;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    private Game game;

    @Before
    public void initialize() {
        game = new Game();
        game.createPlayer("Aga", 50);
        game.createPlayer("Tom", 30);
        game.getPlayers().get(0).setLastMove(MoveType.DOUBLE_DOWN);
        game.getPlayers().get(1).setLastMove(null);
        game.createContestants();
        game.playerBetting(game.getPlayers().get(0), 20);
        game.playerBetting(game.getPlayers().get(1), 20);
    }

    @Test
    public void createPlayer() {
        assertEquals(2, game.getPlayers().size());
    }

    @Test
    public void getPlayersWithPossibleMove() {
        assertEquals(1, game.getPlayersWithPossibleMove().size());
    }

    @Test
    public void createContestants() {
        assertEquals(3, game.getContestants().size());
    }

    @Test
    public void dealCards() {
        game.dealCards();
        assertEquals(2, game.getContestants().get(0).getHand().size());
    }

    @Test
    public void playerBetting() {
        assertEquals(20, game.getPlayers().get(0).getBetValue());
    }

    @Test
    public void playerWithToMuchCardsAuction() {
        for (int i = 0; i < 11; i++) {
            game.getPlayers().get(1).addCardToHand(new Card(Sign.TEN, Color.CLUB));
        }
        game.playerAuction(game.getPlayers().get(1), MoveType.HIT);
        assertEquals(MoveType.STAND, game.getPlayers().get(1).getLastMove());
    }

    @Test
    public void playerAuction() {
        game.playerAuction(game.getPlayers().get(1), MoveType.HIT);
        assertEquals(MoveType.HIT, game.getPlayers().get(1).getLastMove());
    }

    @Test
    public void anyPlayerHaveMove() {
        assertTrue(game.anyPlayerHaveMove());
    }

    @Test
    public void anyPlayerHaveNotMove() {
        game.getPlayers().get(1).setLastMove(MoveType.STAND);
        assertFalse(game.anyPlayerHaveMove());
    }

    @Test
    public void sortContestants() {
        game.getPlayers().get(1).addCardToHand(new Card(Sign.ACE, Color.CLUB));
        game.getPlayers().get(1).addCardToHand(new Card(Sign.TEN, Color.CLUB));
        game.setContestants(game.sortContestants());
        assertEquals(21, game.getContestants().get(0).countScore());
    }

    @Test
    public void setContestantsPositions() {
        game.getPlayers().get(1).addCardToHand(new Card(Sign.ACE, Color.CLUB));
        game.getPlayers().get(1).addCardToHand(new Card(Sign.TEN, Color.CLUB));
        game.setContestants(game.sortContestants());
        game.setContestantsPositions();
        assertEquals((Integer) 1, game.getContestants().get(0).getPosition());
    }

    @Test
    public void setWinnersCash() {
        game.getPlayers().get(1).addCardToHand(new Card(Sign.ACE, Color.CLUB));
        game.getPlayers().get(1).addCardToHand(new Card(Sign.TEN, Color.CLUB));
        game.setContestants(game.sortContestants());
        game.setContestantsPositions();
        game.getWinPrize();
        game.setWinnersCash();
        assertEquals(50, game.getContestants().get(0).getCash());
    }

    @Test
    public void getWinPrize() {
        game.getPlayers().get(1).addCardToHand(new Card(Sign.ACE, Color.CLUB));
        game.getPlayers().get(1).addCardToHand(new Card(Sign.TEN, Color.CLUB));
        game.setContestants(game.sortContestants());
        game.setContestantsPositions();
        assertEquals(40, game.getWinPrize());
    }
}