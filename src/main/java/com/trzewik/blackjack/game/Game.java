package com.trzewik.blackjack.game;

import java.util.*;

import com.trzewik.blackjack.deck.Deck;
import com.trzewik.blackjack.deck.enums.MoveType;
import com.trzewik.blackjack.players.Contestant;
import com.trzewik.blackjack.players.Croupier;
import com.trzewik.blackjack.players.Player;
import com.trzewik.userinputprovider.MessagePrinter;
import com.trzewik.userinputprovider.UserInputMatcher;
import com.trzewik.userinputprovider.UserInputProvider;

public class Game {

    private List<Player> players;
    private Croupier croupier;
    private Deck deck;
    private List<Contestant> contestants;

    public Game(int numberOfPlayers){
        this.deck = new Deck();
        this.croupier = new Croupier();
        this.players = new ArrayList<>();
        this.createPlayers(numberOfPlayers);
        this.dealCards();
        this.printCroupierSingleCard();
        this.playersBetting();
        this.playersAuction();
        this.getCroupierCards();
        this.contestants = this.createContestants();
        this.getContestantsPositions();
        this.sortContestantsByPosition();
    }


    private void createPlayers(int numberOfPlayers){
        for (int i=0; i<numberOfPlayers; i++){
            String name = UserInputProvider.collectString(MessageProvider.collectName);
            int cash = UserInputProvider.collectIntegerInRangeMin(1, MessageProvider.collectCash, name);
            Player player = new Player(name, cash);
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
            return UserInputMatcher.collectProperMoveType(MoveType.values(), MessageProvider.askPlayerForHitStandDouble, player.getName());
        }
        else {return UserInputMatcher.collectProperMoveType(MoveType.getHitStand(), MessageProvider.getAskPlayerForHitStand, player.getName());
        }
    }

    private void gameAction(MoveType choice, Player player) {
        switch (choice) {
            case HIT:
                player.addCardToHand(deck.getCard());
                break;
            case DOUBLE_DOWN:
                player.addCardToHand(deck.getCard());
                croupier.getMoneyFromPlayerIfDoubleDown(player);
                player.setLastMove(MoveType.DOUBLE_DOWN);
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
                if (player.getLastMove() == MoveType.HIT || player.getLastMove() == null){
                    MessagePrinter.printMessage(MessageProvider.tellPlayerHandPoints, player.getName(), player.getHand().toString(), String.valueOf(player.countScore()));
                    MoveType choice = this.getUserChoice(player);
                    player.setLastMove(choice);
                    this.gameAction(choice, player);
                }
            }
        }
    }

    private List<Contestant> createContestants(){
        List<Contestant> contestants = new ArrayList<>(this.players);
        contestants.add(croupier);
        return contestants;
        }

    private void sortContestantsByScores() {
        contestants.sort(Comparator.comparing(Contestant::countScore));
    }

    private void getContestantsPositions(){
        this.sortContestantsByScores();
        int positionFirst = 1;
        int numberOfContestants = this.contestants.size();
        for (int i=numberOfContestants; i>0; i--){
            int reverseIteration = i -1;
            Contestant currentContestant = this.contestants.get(reverseIteration);
            if (currentContestant.countScore()>21){
                if (i==numberOfContestants){
                    currentContestant.setPosition(i);
                }
                else {
                    Contestant previousContestant = this.contestants.get(reverseIteration+1);
                    if (currentContestant.countScore() == previousContestant.countScore()){
                        this.contestants.get(reverseIteration).setPosition(previousContestant.getPosition());
                    }
                    else {currentContestant.setPosition(i);
                    currentContestant.setBuster(true);
                    }
                }
            }
            else {
                if (i==numberOfContestants){
                    currentContestant.setPosition(positionFirst);
                }
                else {
                    Contestant previousContestant = this.contestants.get(reverseIteration+1);
                    if (currentContestant.countScore() == previousContestant.countScore()){
                        this.contestants.get(reverseIteration).setPosition(positionFirst);
                    }
                    else {
                        positionFirst += 1;
                        this.contestants.get(reverseIteration).setPosition(positionFirst);
                    }
                }
            }
        }
    }

    private void sortContestantsByPosition() {
        contestants.sort(Comparator.comparing(Contestant::getPosition));
    }

    private int getWinPrize(){
        List<Contestant> winners = new ArrayList<>();
        for (Contestant contestant: this.contestants){
            if (contestant.getPosition() == 1){
                winners.add(contestant);
            }
        }
        return this.croupier.getCasino()/winners.size();
    }
}
