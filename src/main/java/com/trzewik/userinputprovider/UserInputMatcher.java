package com.trzewik.userinputprovider;

import com.trzewik.blackjack.deck.enums.MoveType;

import java.util.Arrays;

import static com.trzewik.userinputprovider.UserInputProvider.collectString;

public class UserInputMatcher {

    public static MoveType collectProperMoveType(MoveType[] expectedMoveTypes, String message, String... formats) {
        while (true) {
            try {
                MoveType moveType = collectMoveType(message, formats);
                if (Arrays.asList(expectedMoveTypes).contains(moveType)) {
                    return moveType;
                }
                throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                MessagePrinter.printErrorMessage("You put wrong MoveType");
            }
        }
    }

    private static MoveType collectMoveType(String message, String... formats) {
        while (true) {
            try {
                String userInput = collectString(message, formats);
                return MoveType.matchMove(userInput);
            } catch (IllegalArgumentException e) {
                MessagePrinter.printErrorMessage("You put wrong MoveType");
            }
        }
    }
}