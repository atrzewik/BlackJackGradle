package com.trzewik.blackjack;

import java.util.*;

import com.trzewik.blackjack.deck.Deck;
import com.trzewik.blackjack.players.Croupier;
import com.trzewik.blackjack.players.Player;
import com.trzewik.userinputprovider.UserInputProvider;

public class Game {

    private List<Player> players;
    private Croupier croupier;
    private Deck deck;

    Game(int numberOfPlayers){
        this.deck = new Deck();
        this.croupier = new Croupier();
        this.players = new ArrayList<>();
        this.createPlayers(numberOfPlayers);
        this.dealCards();
        this.printCroupierSingleCard();
        this.playersBetting();
        this.playersAuction();
        this.getCroupierCards();
    }


    private void createPlayers(int numberOfPlayers){
        for (int i=0; i<numberOfPlayers; i++){
            Player player = new Player();
            player.setName(UserInputProvider.collectString(MessageProvider.collectName));
            player.setCash(UserInputProvider.collectIntegerInRangeMin(1, MessageProvider.collectCash, player.getName()));
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
        MessagePrinter.printMessage(MessageProvider.collectCardFromCroupier + croupier.getSingleCard());
    }

    private void playersBetting(){
        for (Player player: this.players){
            MessagePrinter.printMessage(MessageProvider.tellPlayerHandPoints, player.getName(), player.getHand().toString(), String.valueOf(player.countScore()));
            player.setBetValue(UserInputProvider.collectIntegerInRangeMinMax(1, player.getCash(),MessageProvider.askPlayerForBet, player.getName()));
            croupier.getMoneyFromPlayer(player);
        }
    }

    private MoveType getUserChoice(Player player){
        if (player.getCash() >= player.getBetValue() && player.getLastMove() == null){
            return UserInputProvider.collectProperMoveType(new ArrayList<>(Arrays.asList(MoveType.STAND, MoveType.HIT, MoveType.DOUBLEDOWN)), new ArrayList<>(Arrays.asList(MoveType.STAND.getExpectedPlayerChoice(), MoveType.STAND.getExpectedPlayerChoice().toUpperCase(), MoveType.HIT.getExpectedPlayerChoice(), MoveType.HIT.getExpectedPlayerChoice().toUpperCase(), MoveType.DOUBLEDOWN.getExpectedPlayerChoice(), MoveType.DOUBLEDOWN.getExpectedPlayerChoice().toUpperCase())), MessageProvider.askPlayerForHitStandDouble, player.getName());
        }
        else {return UserInputProvider.collectProperMoveType(new ArrayList<>(Arrays.asList(MoveType.STAND, MoveType.HIT)), new ArrayList<>(Arrays.asList(MoveType.STAND.getExpectedPlayerChoice(), MoveType.STAND.getExpectedPlayerChoice().toUpperCase(), MoveType.HIT.getExpectedPlayerChoice(), MoveType.HIT.getExpectedPlayerChoice().toUpperCase())), MessageProvider.getAskPlayerForHitStand, player.getName());
        }
    }

    private void gameAction(MoveType choice, Player player) {
        switch (choice) {
            case HIT:
                player.addCardToHand(deck.getCard());
                break;
            case DOUBLEDOWN:
                player.addCardToHand(deck.getCard());
                croupier.getMoneyFromPlayerIfDoubleDown(player);
                player.setLastMove(MoveType.DOUBLEDOWN);
                MessagePrinter.printMessage(MessageProvider.getTellPlayerHandPointsBet, player.getHand().toString(), String.valueOf(player.countScore()), String.valueOf(player.getBetValue()));
                break;
            default:
        }
    }

    private void getCroupierCards(){
        while (croupier.shouldDrawCards()){
            MessagePrinter.printMessage(MessageProvider.tellCroupierHandPoints, croupier.getHand().toString(), String.valueOf(croupier.countScore()));
            croupier.addCardToHand(deck.getCard());
        }
        MessagePrinter.printMessage(MessageProvider.tellCroupierHandPoints, croupier.getHand().toString(), String.valueOf(croupier.countScore()));
    }

    private boolean anyPlayerHaveMove(){
        boolean somePlayerHaveMove = false;
        for (Player player: this.players){
            if (player.getLastMove() == MoveType.HIT || player.getLastMove() == null){
                somePlayerHaveMove = true;
            }
        }
        return somePlayerHaveMove;
    }

    private void playersAuction(){
        while (this.anyPlayerHaveMove()){
            for (Player player: this.players){
                if (player.getHand().size() >= 11){
                    player.setLastMove(MoveType.STAND);
                }
                if (player.getLastMove() != MoveType.STAND && player.getLastMove() != MoveType.DOUBLEDOWN){
                    MessagePrinter.printMessage(MessageProvider.tellPlayerHandPoints, player.getName(), player.getHand().toString(), String.valueOf(player.countScore()));
                    MoveType choice = this.getUserChoice(player);
                    player.setLastMove(choice);
                    this.gameAction(choice, player);
                }
            }
        }
    }
}
