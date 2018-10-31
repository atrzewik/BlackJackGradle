package com.trzewik.userinputprovider;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInputProvider {

    private void printErrorMessage(String errorMessage){
        System.err.println(errorMessage);
    }

    private Scanner getMessage(String message){
        Scanner userInput = new Scanner(System.in);
        System.out.println(message);
        return userInput;
    }

    public String collectStringInputFromUser(String message){
        while (true) {
            try {
                Scanner userInput = getMessage(message);
                return userInput.nextLine();
            } catch (Exception e) {
                printErrorMessage("Input must be a string! Try again: ");
            }
        }
    }

    public Integer collectIntegerInputFromUser(String message) {
        while (true) {
            try {
                Scanner userInput = getMessage(message);
                return userInput.nextInt();
            } catch (Exception e) {
                printErrorMessage("Input must be an integer! Try again: ");
            }
        }
    }

    public String collectProperStringFromUser(ArrayList<String> listOfStrings, String message){
        String userInput = this.collectStringInputFromUser(message);
        if (listOfStrings.contains(userInput)){
            return userInput;
        }
        else {
            System.err.printf("You must input string: %s! Please try again: ", listOfStrings);
            return collectProperStringFromUser(listOfStrings, message);
        }
    }

    public Integer collectIntegerInRangeMinFromUser(Integer minimum, String message){
        Integer userInput = this.collectIntegerInputFromUser(message);
        if (minimum <= userInput){
            return userInput;
        }
        else {
            System.err.printf("You must input value in range bigger than: %s ! Please try again: ", minimum);
            return collectIntegerInRangeMinFromUser(minimum, message);
        }
    }

    public Integer collectIntegerInRangeMinMaxFromUser(Integer minimum, Integer maximum, String message){
        Integer userInput = this.collectIntegerInputFromUser(message);
        if (minimum <= userInput & userInput <= maximum){
            return userInput;
        }
        else {
            System.err.printf("You must input value in range: %s - %s! Please try again: ", minimum, maximum);
            return collectIntegerInRangeMinMaxFromUser(minimum, maximum, message);
        }
    }
}
