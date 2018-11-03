package com.trzewik.blackjack.players;

import com.trzewik.blackjack.deck.Card;
import com.trzewik.blackjack.deck.enums.Sign;

import java.util.ArrayList;
import java.util.List;

public abstract class Contestant {

    private List<Card> hand;
    private int position;
    private boolean buster;
    private int cash;
    private String name;

    protected Contestant(){
        this.hand = new ArrayList<>();
        this.position = 0;
        this.buster = false;
    }

    protected Contestant(String name, int cash){
        this.cash = cash;
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getCash(){
        return this.cash;
    }

    public void setCash(int cash){
        this.cash = cash;
    }

    public boolean getBuster(){
        return this.buster;
    }

    public void setBuster(boolean buster){
        this.buster = buster;
    }

    public int getPosition(){
        return this.position;
    }

    public void setPosition(int position){
        this.position =position;
    }

    public List<Card> getHand(){
        return this.hand;
    }

    public void addCardToHand(Card card){
        this.hand.add(card);
    }

    private boolean aceInHand(){
        ArrayList<Sign> listOfSigns = new ArrayList<>();
        for (Card card : this.hand){
            listOfSigns.add(card.getSign());
        }
        return listOfSigns.contains(Sign.ACE);
    }

    public int countScore() {
        int score = 0;
        for (Card card : this.hand) {
            score += card.getValue();
        }
        if (this.aceInHand() & score <= 11){
            score += 10;
        }
        return score;
    }
}
