package com.trzewik.blackjack;

import java.util.ArrayList;
import java.util.List;

import com.trzewik.blackjack.deck.Deck;
import com.trzewik.blackjack.players.Croupier;
import com.trzewik.blackjack.players.Player;
import com.trzewik.userinputprovider.UserInputProvider;

public class Game {

    private int numberOfPlayers;
    private List<Player> players;
    private Croupier croupier;
    private Player player;
    private Deck deck;

    Game(int numberOfPlayers){
        System.out.println("Welcome into BlackJack!!");
        this.numberOfPlayers = numberOfPlayers;
        this.deck = new Deck();
        this.croupier = new Croupier();
//        this.player = new Player();
        this.players = new ArrayList<>();
        this.createPlayers();
        this.dealCards();
        this.printCroupierSingleCard();
        this.playersBetting();
        this.playersAuction();
        this.getCroupierCards();
    }


    private void createPlayers(){
        for (int i=0; i<this.numberOfPlayers; i++){
            player = new Player();
            player.setName(UserInputProvider.collectString("Please enter player name: ", ""));
            player.setCash(UserInputProvider.collectIntegerInRangeMin(1, "Please enter amount of cash for %s: ", player.getName()));
            this.players.add(player);
        }
    }

    private void dealCards(){
        for (int i=0; i<2; i++){
            for (Player player: this.players){
                player.addCardToHand(deck.getCard());
            }
            croupier.addCardToHand(deck.getCard());
        }
    }

    private void printCroupierSingleCard(){
        System.out.println("Croupier have: " + croupier.getSingleCard());
    }

    private void playersBetting(){
        for (Player player: this.players){
            System.out.printf("%s have: %s, %s points. ", player.getName(), player.getHand(), player.countScore());
            player.setBetValue(UserInputProvider.collectIntegerInRangeMinMax(1, player.getCash(),"%s please enter your bet bigger than 1$: ", player.getName()));
            croupier.getMoneyFromPlayer(player);
        }
    }

    private String getUserChoice(Player player){
        if (player.getCash() >= player.getBetValue() && player.getLastMove() == MoveType.NONE){
            return (UserInputProvider.collectProperString(MoveType.valuesWithoutNoneUpLow(), "%s, please enter h for hit, st for stand or dd for double down: ", player.getName())).toLowerCase();
        }
        else {return (UserInputProvider.collectProperString(MoveType.valuesWithoutDoubleUpLow(),"%s, please enter h for hit or st for stand: ", player.getName())).toLowerCase();
        }
    }

    private void gameAction(String choice, Player player){
        if (choice.equals(MoveType.HIT.getValue())){
            player.addCardToHand(deck.getCard());
        }
        else if (choice.equals(MoveType.DOUBLEDOWN.getValue())){
            player.addCardToHand(deck.getCard());
            croupier.getMoneyFromPlayerIfDoubleDown(player);
            player.setLastMove(MoveType.DOUBLEDOWN);
            System.out.printf("You have %s, %s points and your bet value now is equal: %s.\n", player.getHand(), player.countScore(), player.getBetValue());
        }
    }

    private void getCroupierCards(){
        while (croupier.shouldDrawCards()){
            System.out.printf("Croupier have: %s, %s points. ", croupier.getHand(), croupier.countScore());
            croupier.addCardToHand(deck.getCard());
        }
        System.out.printf("\nCroupier have: %s, %s points", croupier.getHand(), croupier.countScore());
    }

    private boolean playNextTurn(){
        boolean whatToDo = false;
        for (Player player: this.players){
            if (player.getLastMove() == MoveType.HIT || player.getLastMove() == MoveType.NONE){
                whatToDo = true;
            }
        }
        return whatToDo;
    }

    private void playersAuction(){
        while (this.playNextTurn()){
            for (Player player: this.players){
                if (player.getHand().size() >= 11){
                    player.setLastMove(MoveType.STAND);
                }
                if (player.getLastMove() != MoveType.STAND && player.getLastMove() != MoveType.DOUBLEDOWN){
                    System.out.printf("%s have: %s, %s points. ", player.getName(), player.getHand(), player.countScore());
                    String choice = this.getUserChoice(player);
                    player.setLastMove(MoveType.matchMove(choice));
                    this.gameAction(choice, player);
                }
            }
        }
    }



}
