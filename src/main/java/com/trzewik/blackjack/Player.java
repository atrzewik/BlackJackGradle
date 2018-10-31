package com.trzewik.blackjack;

public class Player extends Contestant {

    private int betValue;
    private MoveType lastMove;

    public Player(){
        this.betValue = 0;
        this.lastMove = MoveType.NONE;
    }

    public Player(String name, int cash){
        super(name, cash);
    }

    public int getBetValue(){
        return this.betValue;
    }

    public void setBetValue(int betValue){
        this.betValue = betValue;
    }

    public MoveType getLastMove(){
        return this.lastMove;
    }

    public void setLastMove(MoveType lastMove){
        this.lastMove = lastMove;
    }

    public boolean sameCardsValue(){
        return super.getHand().get(0).getValue() == super.getHand().get(1).getValue();
    }

    public void payBetValue(){
        super.setCash(super.getCash()- this.betValue);
    }

    public void doubleBetValue(){
        this.betValue *= 2;
    }
}
