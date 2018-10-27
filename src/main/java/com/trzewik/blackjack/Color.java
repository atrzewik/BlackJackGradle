package com.trzewik.blackjack;

import java.util.ArrayList;
import java.util.Arrays;

public enum  Color {
    HEART,
    DIAMOND,
    SPADE,
    CLUB;

    ArrayList<Enum> listOfColors = new ArrayList<>();

    public ArrayList<Enum> getListOfColors(){
        listOfColors.addAll(Arrays.asList(Color.values()));
        return listOfColors;
    }
}
