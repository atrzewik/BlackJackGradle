package com.trzewik.blackjack.players;

import com.trzewik.blackjack.deck.Card;

public class Croupier extends Contestant {

    private int casino;

    public Croupier(){
        super("Croupier", 0);
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
