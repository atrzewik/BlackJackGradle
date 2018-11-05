package com.trzewik.blackjack;


public enum MoveType {
    NONE ("n"),
    STAND ("st"),
    HIT ("h"),
    DOUBLEDOWN("dd");

    private final String expectedPlayerChoice;

    MoveType(String value){this.expectedPlayerChoice = value;}

    public String getExpectedPlayerChoice(){return this.expectedPlayerChoice;}

    public static MoveType matchMove(String playerChoice){
        for (MoveType moveType: MoveType.values()){
            if (moveType.getExpectedPlayerChoice().equals((playerChoice).toLowerCase())){
                return moveType;
            }
        }
        throw new IllegalArgumentException("You specified wrong playerChoice!");
    }
}
