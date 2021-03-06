package com.trzewik.blackjack.players;

import com.trzewik.blackjack.deck.enums.MoveType;

public class Player extends Contestant {

    private int betValue;
    private MoveType lastMove;

    public Player(String name, int cash) {
        super(name, cash);
    }

    public int getBetValue() {
        return this.betValue;
    }

    public void setBetValue(int betValue) {
        this.betValue = betValue;
    }

    public MoveType getLastMove() {
        return this.lastMove;
    }

    public void setLastMove(MoveType lastMove) {
        this.lastMove = lastMove;
    }

    public boolean sameCardsValue() {
        return super.getHand().get(0).getValue() == super.getHand().get(1).getValue();
    }

    protected void payBetValue() {
        super.setCash(super.getCash() - this.betValue);
    }

    protected void doubleBetValue() {
        this.betValue *= 2;
    }

    public boolean hasPossibleMove() {
        return getLastMove() == MoveType.HIT || getLastMove() == null;
    }

}
