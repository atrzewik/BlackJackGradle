package com.trzewik.blackjack;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<String> listOfCards = new ArrayList<>();

    public void setListOfCards(ArrayList<String> listOfCards) {
        this.listOfCards = listOfCards;
    }

    {
        for (Sign s : Sign.values()){
            for (Color c : Color.values()){
                String card = new Card(s, c).getCard();
                this.listOfCards.add(card);
            }
        }
        shuffleDeck();
    }

    private ArrayList<String> shuffleDeck(){
        Collections.shuffle(this.listOfCards);
        return listOfCards;
    }

    public String getCard(){
        String card = this.listOfCards.get(0);
        deleteCardFromDeck(card);
        return card;
    }

    private void deleteCardFromDeck(String card){
        this.listOfCards.remove(card);
    }
}
