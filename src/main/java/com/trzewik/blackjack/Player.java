package com.trzewik.blackjack;

public class Player extends Contestant {

    private int betValue;

    public Player(){
        this.betValue = 0;
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
}
