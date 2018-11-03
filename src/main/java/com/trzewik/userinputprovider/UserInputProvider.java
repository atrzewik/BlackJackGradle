package com.trzewik.userinputprovider;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInputProvider {

    private static void printErrorMessage(String errorMessage, String format, String secondFormat){
        System.err.printf(errorMessage, format, secondFormat);
    }
    private static void printMessage(String message, String format){
        System.out.printf(message, format);
    }

    private static Scanner getMessage(String message, String format){
        Scanner userInput = new Scanner(System.in);
        printMessage(message, format);
        return userInput;
    }

    public static String collectString(String message, String format){
        while (true) {
            try {
                Scanner userInput = getMessage(message, format);
                return userInput.nextLine();
            } catch (Exception e) {
                printErrorMessage("Input must be a string! Try again: ", "","");
            }
        }
    }

    public static Integer collectInteger(String message, String format) {
        while (true) {
            try {
                Scanner userInput = getMessage(message, format);
                return userInput.nextInt();
            } catch (Exception e) {
                printErrorMessage("Input must be an integer! Try again: ", "","");
            }
        }
    }

    public static String collectProperString(List<String> listOfStrings, String message, String format) {
        while (true) {
            try {
                String userInput = collectString(message, format);
                if (listOfStrings.contains(userInput)) {
                    return userInput;
                }
                throw new IllegalArgumentException();
            } catch (Exception e) {
                printErrorMessage("You must input string: %s! Please try again: ", listOfStrings.toString(), "");
            }
        }
    }

    public static Integer collectIntegerInRangeMin(Integer minimum, String message, String format){
            while (true) {
                try {
                    Integer userInput = collectInteger(message, format);
                    if (userInput >= minimum) {
                        return userInput;
                    }
                    throw new ArithmeticException();
                } catch (Exception e) {
                    printErrorMessage("You must input value in range bigger than: %s ! Please try again: ", minimum.toString(), "");
                }
            }
        }

    public static Integer collectIntegerInRangeMinMax(Integer minimum, Integer maximum, String message, String format){
        while (true){
            try {
                Integer userInput = collectInteger(message,format);
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

    public static void main(String[] args){
        List<String> a = new ArrayList<>();
        a.add("as");
        a.add("bis");
        System.out.println(collectProperString(a,"Give me %s","as or bis"));
    }
}
