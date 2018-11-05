package com.trzewik.blackjack;


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
}
