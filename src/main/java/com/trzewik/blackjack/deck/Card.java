package com.trzewik.blackjack.deck;

import com.trzewik.blackjack.deck.enums.Color;
import com.trzewik.blackjack.deck.enums.Sign;

public class Card {

    private Sign sign;
    private Color color;

    public Card(Sign sign, Color color) {
        this.sign = sign;
        this.color = color;
    }

    @Override
    public String toString() {
        return this.sign.name() + " " + this.color.name();
    }

    public int getValue() {
        return this.sign.getValue();
    }

    public Sign getSign() {
        return this.sign;
    }

}
