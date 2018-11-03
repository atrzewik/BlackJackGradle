package com.trzewik.blackjack;

import java.util.*;

public enum MoveType {
    NONE ("n"),
    STAND ("st"),
    HIT ("h"),
    DOUBLEDOWN("dd");

    private final String value;

    MoveType(String value){this.value = value;}

    public String getValue(){return this.value;}

    public static MoveType matchMove(String playerMove){
        MoveType lastMove = NONE;
        for (MoveType moveType: MoveType.values()){
            if (moveType.getValue().equals(playerMove)){
                lastMove = moveType;
            }
        }
        return lastMove;
    }

    public  static List<MoveType> keysWithoutNone(){
        return  new ArrayList<>(Arrays.asList(MoveType.STAND, MoveType.HIT, MoveType.DOUBLEDOWN));
    }

    public static List<MoveType> keysWithoutDouble(){
        return new ArrayList<>(Arrays.asList(MoveType.STAND, MoveType.HIT));
    }

    public static List<String> valuesWithoutNoneUpLow(){
        return new ArrayList<>(Arrays.asList(MoveType.STAND.getValue(), MoveType.STAND.getValue().toUpperCase(), MoveType.HIT.getValue(), MoveType.HIT.getValue().toUpperCase(), MoveType.DOUBLEDOWN.getValue(), MoveType.DOUBLEDOWN.getValue().toUpperCase()));
}

    public static List<String> valuesWithoutDoubleUpLow(){
        return new ArrayList<>(Arrays.asList(MoveType.STAND.getValue(), MoveType.STAND.getValue().toUpperCase(), MoveType.HIT.getValue(), MoveType.HIT.getValue().toUpperCase()));
    }

    public static void main(String[] args){
        System.out.println(MoveType.valuesWithoutNoneUpLow());
    }
}
