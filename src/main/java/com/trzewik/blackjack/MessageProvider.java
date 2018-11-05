package com.trzewik.blackjack;

public class MessageProvider {

    public static String collectName = "Please enter player name: ";

    public static String collectCash = "Please enter amount of cash for %s: ";

    public static String collectCardFromCroupier = "Croupier have: ";

    public static String tellPlayerHandPoints = "%s have: %s, %s points. ";

    public static String tellCroupierHandPoints = "Croupier have: %s, %s points";

    public static String askPlayerForBet = "%s please enter your bet bigger than 1$: ";

    public static String askPlayerForHitStandDouble = "%s, please enter h for hit, st for stand or dd for double down: ";

    public static String getAskPlayerForHitStand = "%s, please enter h for hit or st for stand: ";

    public static String getTellPlayerHandPointsBet = "You have %s, %s points and your bet value now is equal: %s ";


    public static void printMessage(String message){
        System.out.println(message);
    }

    public static void printMessageWithTwoFormat(String message, String format, String secondFormat){
        System.out.printf(message, format, secondFormat);
    }

    public static void printMessageWithThreeFormat(String message, String format, String secondFormat, String thirdFormat){
        System.out.printf(message, format, secondFormat, thirdFormat);
    }

}
