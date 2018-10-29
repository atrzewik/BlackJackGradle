package com.trzewik.blackjack;

import java.util.ArrayList;

public class Contestant {

    ArrayList<Card> hand;
    int position;
    boolean buster;
    int cash;
    String name;

    public Contestant(){
        this.hand = new ArrayList<>();
        this.position = 0;
        this.buster = false;
    }

    public Contestant(String name, int cash){
        this.cash = cash;
        this.name = name;
    }

    public void addCardToHand(Card card){
        this.hand.add(card);
    }

    public int countScore(){
        int score = 0;
//        for(Card card : this.hand) {
//            card.;
//        }
    return score;}
}
