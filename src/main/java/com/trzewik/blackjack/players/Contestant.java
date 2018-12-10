package com.trzewik.blackjack.players;

import com.trzewik.blackjack.deck.Card;
import com.trzewik.blackjack.deck.enums.Sign;

import java.util.ArrayList;
import java.util.List;

public abstract class Contestant {

    private List<Card> hand;
    private Integer position;
    private int cash;
    private String name;

    public Contestant(String name, int cash) {
        this.cash = cash;
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public int getCash() {
        return this.cash;
    }

    public void setCash(Integer cash) {
        this.cash = cash;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public List<Card> getHand() {
        return this.hand;
    }

    public void addCardToHand(Card card) {
        this.hand.add(card);
    }

    public Boolean checkIfBuster() {
        return this.countScore() > 21;
    }

    private boolean aceInHand() {
        ArrayList<Sign> listOfSigns = new ArrayList<>();
        for (Card card : this.hand) {
            listOfSigns.add(card.getSign());
        }
        return listOfSigns.contains(Sign.ACE);
    }

    public List<Card> cardsToPrint(){
        return this.hand;
    }

    public int countScore() {
        int score = 0;
        for (Card card : this.hand) {
            score += card.getValue();
        }
        if (this.aceInHand() & score <= 11) {
            score += 10;
        }
        return score;
    }
}
