package com.trzewik.userinputprovider;

import com.trzewik.blackjack.deck.enums.MoveType;

import java.util.List;

import static com.trzewik.userinputprovider.UserInputProvider.collectString;

public class UserInputMatcher {

    public static MoveType collectProperMoveType(List<MoveType> moveTypes, String message, String ... formats){
        while (true) {
            try {
                MoveType enumUserInput = collectMoveType(message,formats);
                if (moveTypes.contains(enumUserInput)) {
                    return  enumUserInput;
                }
                throw new IllegalArgumentException();
            } catch (Exception e) {
                MessagePrinter.printErrorMessage("You put wrong MoveType");
            }
        }
    }

    private static MoveType collectMoveType(String message, String ... formats) {
        while (true) {
            try {
                String userInput = collectString(message, formats);
                return MoveType.matchMove(userInput);
            } catch (Exception e) {
                MessagePrinter.printErrorMessage("You put wrong MoveType");
            }
        }
    }
}
