package com.trzewik.blackjack;

public class Croupier extends Contestant {

    private int casino;

    public Croupier(){
        super.setName("Croupier");
        super.setCash(0);
        this.casino = 0;
    }

    public int getCasino(){
        return this.casino;
    }

    public void setCasino(int casino){
        this.casino = casino;
    }
}
