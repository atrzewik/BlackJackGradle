package com.trzewik.blackjack.deck.enums;


public enum MoveType {
    STAND ("st"),
    HIT ("h"),
    DOUBLE_DOWN("dd");

    private final String expectedPlayerChoice;

    MoveType(String value){this.expectedPlayerChoice = value;}

    public static MoveType matchMove(String playerChoice){
        for (MoveType moveType: values()){
            if (moveType.expectedPlayerChoice.equals((playerChoice).toLowerCase())){
                return moveType;
            }
        }
        throw new IllegalArgumentException("You specified wrong playerChoice!");
    }
}
