package com.trzewik.userinputprovider;

import java.util.List;
import java.util.Scanner;


public class UserInputProvider {


    public static String collectString(String message, String ... formats){
        while (true) {
            try {
                Scanner userInput = getMessage(message, formats);
                return userInput.nextLine();
            } catch (Exception e) {
                MessagePrinter.printErrorMessage("Input must be a string! Try again: ");
            }
        }
    }

    public static Integer collectInteger(String message, String ... formats) {
        while (true) {
            try {
                Scanner userInput = getMessage(message, formats);
                return userInput.nextInt();
            } catch (Exception e) {
                MessagePrinter.printErrorMessage("Input must be an integer! Try again: ");
            }
        }
    }

    public static String collectProperString(List<String> strings, String message, String ... formats) {
        while (true) {
            try {
                String userInput = collectString(message, formats);
                if (strings.contains(userInput)) {
                    return userInput;
                }
                throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                MessagePrinter.printErrorMessage("You must input string: %s! Please try again: ", strings.toString());
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
                    MessagePrinter.printErrorMessage("You must input value in range bigger than: %s ! Please try again: ", minimum.toString());
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
                MessagePrinter.printErrorMessage("You must input value in range: %s - %s! Please try again: ", minimum.toString(), maximum.toString());
            }
        }
    }

    private static Scanner getMessage(String message, String ... formats){
        Scanner userInput = new Scanner(System.in);
        MessagePrinter.printMessage(message, formats);
        return userInput;
    }


}
