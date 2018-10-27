package com.trzewik.blackjack;

public class Card {

    private Enum sign;

    private final Enum color;

    Card(Enum sign, Enum color){
        this.sign = sign;
        this.color = color;
    }

    public String getCard(){
        return this.sign.name() + " " + this.color.name();
    }
}
