package com.trzewik.userinputprovider;

import java.util.MissingFormatArgumentException;

public class MessagePrinter {

    private static String missingFormatArgument = "You specified too less format arguments!";

    public static void printMessageInLine(String message, String... formats) {
        try {
            System.out.println(String.format(message, formats));
        } catch (MissingFormatArgumentException ex) {
            throw new MissingFormatArgumentException(missingFormatArgument);
        }
    }

    public static void printMessage(String message, String... formats) {
        try {
            System.out.print(String.format(message, formats));
        } catch (MissingFormatArgumentException ex) {
            throw new MissingFormatArgumentException(missingFormatArgument);
        }
    }

    public static void printErrorMessage(String errorMessage, String... formats) {
        try {
            System.err.println(String.format(errorMessage, formats));
        } catch (MissingFormatArgumentException ex) {
            throw new MissingFormatArgumentException(missingFormatArgument);
        }
    }
}
