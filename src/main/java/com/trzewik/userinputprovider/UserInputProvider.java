package com.trzewik.userinputprovider;

import java.util.List;
import java.util.Scanner;
import java.util.MissingFormatArgumentException;

public class UserInputProvider {


    public static String collectString(String message, String ... formats){
        while (true) {
            try {
                Scanner userInput = getMessage(message, formats);
                return userInput.nextLine();
            } catch (Exception e) {
                printErrorMessage("Input must be a string! Try again: ");
            }
        }
    }

    public static Integer collectInteger(String message, String ... formats) {
        while (true) {
            try {
                Scanner userInput = getMessage(message, formats);
                return userInput.nextInt();
            } catch (Exception e) {
                printErrorMessage("Input must be an integer! Try again: ");
            }
        }
    }

    public static String collectProperString(List<String> listOfStrings, String message, String ... formats) {
        while (true) {
            try {
                String userInput = collectString(message, formats);
                if (listOfStrings.contains(userInput)) {
                    return userInput;
                }
                throw new IllegalArgumentException();
            } catch (Exception e) {
                printErrorMessage("You must input string: %s! Please try again: ", listOfStrings.toString());
            }
        }
    }

    public static Integer collectIntegerInRangeMin(Integer minimum, String message, String ... formats){
            while (true) {
                try {
                    Integer userInput = collectInteger(message, formats);
                    if (userInput >= minimum) {
                        return userInput;
                    }
                    throw new ArithmeticException();
                } catch (Exception e) {
                    printErrorMessage("You must input value in range bigger than: %s ! Please try again: ", minimum.toString());
                }
            }
        }

    public static Integer collectIntegerInRangeMinMax(Integer minimum, Integer maximum, String message, String ... formats){
        while (true){
            try {
                Integer userInput = collectInteger(message,formats);
                if (userInput >= minimum && userInput <= maximum){
                    return userInput;
                }
                throw new  ArithmeticException();
            }
            catch (Exception e){
                printErrorMessage("You must input value in range: %s - %s! Please try again: ", minimum.toString(), maximum.toString());
            }
        }
    }

    private static void printErrorMessage(String errorMessage, String ... formats){
        try {
        System.err.println(String.format(errorMessage, formats));
        }
        catch (MissingFormatArgumentException ex){
            throw new MissingFormatArgumentException(missingFormatArgument);
        }
    }

    private static void printMessage(String message, String ... formats){
        try {
        System.out.println(String.format(message, formats));
        }
        catch (MissingFormatArgumentException ex){
            throw new MissingFormatArgumentException(missingFormatArgument);
        }
    }

    private static Scanner getMessage(String message, String ... formats){
        Scanner userInput = new Scanner(System.in);
        printMessage(message, formats);
        return userInput;
    }

    private static String missingFormatArgument = "You specified too less format arguments!";
}
