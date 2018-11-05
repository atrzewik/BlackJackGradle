package com.trzewik.blackjack;

import java.util.*;

import com.trzewik.blackjack.deck.Deck;
import com.trzewik.blackjack.players.Contestant;
import com.trzewik.blackjack.players.Croupier;
import com.trzewik.blackjack.players.Player;
import com.trzewik.userinputprovider.UserInputProvider;

public class Game {

    private List<Player> players;
    private Croupier croupier;
    private Player player;
    private Deck deck;
    private List<Contestant> contestants;

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
            player = new Player();
            player.setName(UserInputProvider.collectString(MessageProvider.collectName, ""));
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
        MessageProvider.printMessage(MessageProvider.collectCardFromCroupier + croupier.getSingleCard());
    }

    private void playersBetting(){
        for (Player player: this.players){
            MessageProvider.printMessageWithThreeFormat(MessageProvider.tellPlayerHandPoints, player.getName(), player.getHand().toString(), String.valueOf(player.countScore()));
            player.setBetValue(UserInputProvider.collectIntegerInRangeMinMax(1, player.getCash(),MessageProvider.askPlayerForBet, player.getName()));
            croupier.getMoneyFromPlayer(player);
        }
    }

    private String getUserChoice(Player player){
        if (player.getCash() >= player.getBetValue() && player.getLastMove() == MoveType.NONE){
            return (UserInputProvider.collectProperString(new ArrayList<>(Arrays.asList(MoveType.STAND.getValue(), MoveType.STAND.getValue().toUpperCase(), MoveType.HIT.getValue(), MoveType.HIT.getValue().toUpperCase(), MoveType.DOUBLEDOWN.getValue(), MoveType.DOUBLEDOWN.getValue().toUpperCase())), MessageProvider.askPlayerForHitStandDouble, player.getName())).toLowerCase();
        }
        else {return (UserInputProvider.collectProperString(new ArrayList<>(Arrays.asList(MoveType.STAND.getValue(), MoveType.STAND.getValue().toUpperCase(), MoveType.HIT.getValue(), MoveType.HIT.getValue().toUpperCase())),MessageProvider.getAskPlayerForHitStand, player.getName())).toLowerCase();
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
            MessageProvider.printMessageWithThreeFormat(MessageProvider.getTellPlayerHandPointsBet, player.getHand().toString(), String.valueOf(player.countScore()), String.valueOf(player.getBetValue()));
        }
    }

    private void getCroupierCards(){
        while (croupier.shouldDrawCards()){
            MessageProvider.printMessageWithTwoFormat(MessageProvider.tellCroupierHandPoints, croupier.getHand().toString(), String.valueOf(croupier.countScore()));
            croupier.addCardToHand(deck.getCard());
        }
        MessageProvider.printMessageWithTwoFormat(MessageProvider.tellCroupierHandPoints, croupier.getHand().toString(), String.valueOf(croupier.countScore()));
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
                    MessageProvider.printMessageWithThreeFormat(MessageProvider.tellPlayerHandPoints, player.getName(), player.getHand().toString(), String.valueOf(player.countScore()));
                    String choice = this.getUserChoice(player);
                    player.setLastMove(MoveType.matchMove(choice));
                    this.gameAction(choice, player);
                }
            }
        }
    }
//
//    private List<Contestant> createContestants(){
//        List<Contestant> contestants = new ArrayList<>();
//        contestants.addAll(this.players);
//        contestants.add(croupier);
//        return contestants;
//    }
//
//    private List<Integer> createListOfScores(){
//        List<Integer> scores = new ArrayList<>();
//        for (Contestant c: contestants){
//            scores.add(c.countScore());
//        }
//        return scores;
//    }
//
//    private void setPlayersPosition(){
//        List<Integer> scores = this.createListOfScores();
//        int position = this.numberOfPlayers+1;
//        while (scores.size()>0){
//            for (int i=0; i<=this.numberOfPlayers; i++){
//                int maximumScore = Collections.max(scores);
//                Contestant currentContestant = contestants.get(i);
//                if (currentContestant.countScore() == maximumScore){
//                    currentContestant.setPosition(position);
//                    position --;
//                    scores.remove(maximumScore);
//                    if (currentContestant.countScore() > 21){
//                        currentContestant.setBuster(true);
//                    }
//                }
//
//            }
//
//        }
//    }
//
//
//    private List<Integer> contestantsNames(){
//        List<Integer> names = new ArrayList<>();
//        for (Contestant c: this.createContestants()){
//            names.add(c.getPosition());
//        }
//        return names;
//    }

}
