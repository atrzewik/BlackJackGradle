package com.trzewik.blackjack;

public class Player extends Contestant {

    int betValue;

    public Player(){
        this.betValue = 0;
    }

    public Player(String name, int cash){
        super(name, cash);
    }
}
