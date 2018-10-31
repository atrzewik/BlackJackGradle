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

    public boolean shouldDrawCards(){
        return super.countScore() <= 16;
    }

    public Card getSingleCard(){
        return super.getHand().get(0);
    }

    public void getMoneyFromPlayerIfDoubleDown(Player player){
        player.payBetValue();
        this.casino += player.getBetValue();
        player.doubleBetValue();
    }

    public void getMoneyFromPlayer(Player player){
        player.payBetValue();
        this.casino += player.getBetValue();
    }
}
