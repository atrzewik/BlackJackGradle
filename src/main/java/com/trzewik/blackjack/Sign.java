package com.trzewik.blackjack;

import java.util.ArrayList;
import java.util.Arrays;

public enum  Sign {
    ACE (1),
    TWO (2),
    THREE (3),
    FOUR (4),
    FIVE (5),
    SIX (6),
    SEVEN (7),
    EIGHT (8),
    NINE (9),
    TEN (10),
    JACK (10),
    QUEEN (10),
    KING (10);

    private final int value;

    Sign(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }

    ArrayList<Enum> listOfSings = new ArrayList<>();

    public ArrayList<Enum> getListOfSings(){
        listOfSings.addAll(Arrays.asList(Sign.values()));
        return listOfSings;
    }
}
