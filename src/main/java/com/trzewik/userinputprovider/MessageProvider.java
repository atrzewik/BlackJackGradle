package com.trzewik.userinputprovider;


public class MessageProvider {

    public static String collectName = "Please enter player name: ";

    public static String collectCash = "Please enter amount of cash for %s: ";

    public static String collectCardFromCroupier = "Croupier have: ";

    public static String tellPlayerHandPoints = "%s have: %s, %s points. ";

    public static String tellCroupierHandPoints = "Croupier have: %s, %s points";

    public static String askPlayerForBet = "%s please enter your bet bigger than 1$: ";

    public static String askPlayerForHitStandDouble = "%s, please enter h for hit, st for stand or dd for double down: ";

    public static String askPlayerForHitStand = "%s, please enter h for hit or st for stand: ";

    public static String getTellPlayerHandPointsBet = "You have %s, %s points and your bet value now is equal: %s ";

    public static String winners = "The winner is %s with %s points and %s $prize - his cash capital is equal %s $, and ";

    public static String winner = "The winner is %s with %s points and %s $prize - his cash capital is equal %s $";

    public static String players = "%s with %s points and cash capital equal %s $, and ";

    public static String player = "%s with %s points and cash capital equal %s $";

    public static String busters = "The busters is %s with %s points and cash capital equal %s $, and ";

    public static String buster = "The busters is %s with %s points and cash capital equal %s $";
}