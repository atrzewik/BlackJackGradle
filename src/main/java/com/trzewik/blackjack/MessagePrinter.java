package com.trzewik.blackjack;

import java.util.MissingFormatArgumentException;

public class MessagePrinter {

    public static void printMessage(String message, String ... formats){
        try {
            System.out.println(String.format(message, formats));
        }
        catch (MissingFormatArgumentException ex){
            throw new MissingFormatArgumentException(missingFormatArgument);
        }
    }

    private static String missingFormatArgument = "You specified too less format arguments!";
}
