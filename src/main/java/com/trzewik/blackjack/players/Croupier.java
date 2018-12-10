package com.trzewik.blackjack.players;

import com.trzewik.blackjack.deck.Card;

import java.util.Collections;
import java.util.List;

public class Croupier extends Contestant {

    private int casino;

    public Croupier() {
        super("Croupier", 0);
    }

    public int getCasino() {
        return this.casino;
    }

    public boolean shouldDrawCards() {
        return super.countScore() <= 16;
    }

    public void getMoneyFromPlayerIfDoubleDown(Player player) {
        player.payBetValue();
        this.casino += player.getBetValue();
        player.doubleBetValue();
    }

    @Override
    public List<Card> cardsToPrint(){
        return Collections.singletonList(super.getHand().get(0));
    }

    public void getMoneyFromPlayer(Player player) {
        player.payBetValue();
        this.casino += player.getBetValue();
    }
}
