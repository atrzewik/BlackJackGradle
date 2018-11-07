package com.trzewik.blackjack.game;

import java.util.*;
import java.util.stream.Collectors;

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

    public Game(int numberOfPlayers) {
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
        this.setBusters();
        this.sortContestants();
        this.setContestantsPositions();
        this.printGameResults();
    }


    private void createPlayers(int numberOfPlayers) {
        for (int i = 0; i < numberOfPlayers; i++) {
            String name = UserInputProvider.collectString(MessageProvider.collectName);
            int cash = UserInputProvider.collectIntegerInRangeMin(1, MessageProvider.collectCash, name);
            Player player = new Player(name, cash);
            this.players.add(player);
        }
    }

    private void dealCards() {
        for (int i = 0; i < 2; i++) {
            for (Player player : this.players) {
                player.addCardToHand(deck.getCard());
            }
            croupier.addCardToHand(deck.getCard());
        }
    }

    private void printCroupierSingleCard() {
        MessagePrinter.printMessageInLine(MessageProvider.collectCardFromCroupier + croupier.getSingleCard());
    }

    private void playersBetting() {
        for (Player player : this.players) {
            MessagePrinter.printMessageInLine(MessageProvider.tellPlayerHandPoints, player.getName(), player.getHand().toString(), String.valueOf(player.countScore()));
            player.setBetValue(UserInputProvider.collectIntegerInRangeMinMax(1, player.getCash(), MessageProvider.askPlayerForBet, player.getName()));
            croupier.getMoneyFromPlayer(player);
        }
    }

    private MoveType getUserChoice(Player player) {
        if (player.getCash() >= player.getBetValue() && player.getLastMove() == null) {
            return UserInputMatcher.collectProperMoveType(MoveType.values(), MessageProvider.askPlayerForHitStandDouble, player.getName());
        } else {
            return UserInputMatcher.collectProperMoveType(MoveType.getHitStand(), MessageProvider.askPlayerForHitStand, player.getName());
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
                MessagePrinter.printMessageInLine(MessageProvider.getTellPlayerHandPointsBet, player.getHand().toString(), String.valueOf(player.countScore()), String.valueOf(player.getBetValue()));
                break;
            default:
        }
    }

    private void getCroupierCards() {
        while (croupier.shouldDrawCards()) {
            MessagePrinter.printMessageInLine(MessageProvider.tellCroupierHandPoints, croupier.getHand().toString(), String.valueOf(croupier.countScore()));
            croupier.addCardToHand(deck.getCard());
        }
        MessagePrinter.printMessageInLine(MessageProvider.tellCroupierHandPoints, croupier.getHand().toString(), String.valueOf(croupier.countScore()));
    }

    private boolean anyPlayerHaveMove() {
        boolean somePlayerHaveMove = false;
        for (Player player : this.players) {
            if (player.getLastMove() == MoveType.HIT || player.getLastMove() == null) {
                somePlayerHaveMove = true;
            }
        }
        return somePlayerHaveMove;
    }

    private void playersAuction() {
        while (this.anyPlayerHaveMove()) {
            for (Player player : this.players) {
                if (player.getHand().size() >= 11) {
                    player.setLastMove(MoveType.STAND);
                }
                if (player.getLastMove() == MoveType.HIT || player.getLastMove() == null) {
                    MessagePrinter.printMessageInLine(MessageProvider.tellPlayerHandPoints, player.getName(), player.getHand().toString(), String.valueOf(player.countScore()));
                    MoveType choice = this.getUserChoice(player);
                    player.setLastMove(choice);
                    this.gameAction(choice, player);
                }
            }
        }
    }

    private List<Contestant> createContestants() {
        List<Contestant> contestants = new ArrayList<>(this.players);
        contestants.add(croupier);
        return contestants;
    }

    private void setBusters() {
        for (Contestant contestant : this.contestants) {
            if (contestant.countScore() > 21) {
                contestant.setBuster(true);
            }
        }
    }

    private List<Contestant> getSortedBusters() {
        return this.contestants.stream()
                .filter(Contestant::getBuster)
                .sorted(Comparator.comparing(Contestant::countScore))
                .collect(Collectors.toList());
    }

    private List<Contestant> getSortedWinners() {
        return this.contestants.stream()
                .filter(contestant -> !contestant.getBuster())
                .sorted(Comparator.comparing(Contestant::countScore).reversed())
                .collect(Collectors.toList());
    }

    private void sortContestants() {
        List<Contestant> sortedBusters = new ArrayList<>(this.getSortedBusters());
        List<Contestant> sortedWinners = new ArrayList<>(this.getSortedWinners());
        this.contestants = new ArrayList<>(sortedWinners);
        this.contestants.addAll(sortedBusters);
    }

    private void setContestantsPositions() {
        int previousContestantScore = 0;
        int previousContestantPosition = 0;
        int position = 1;
        for (Contestant contestant : this.contestants) {
            int playerScore = contestant.countScore();
            if (playerScore == previousContestantScore) {
                contestant.setPosition(previousContestantPosition);
            } else {
                contestant.setPosition(position);
            }
            previousContestantScore = playerScore;
            previousContestantPosition = contestant.getPosition();
            position++;
        }
    }

    private int getWinPrize() {
        List<Contestant> winners = new ArrayList<>();
        for (Contestant contestant : this.contestants) {
            if (contestant.getPosition() == 1) {
                winners.add(contestant);
            }
        }
        return this.croupier.getCasino() / winners.size();
    }

    private List<Integer> getContestantsPositions() {
        List<Integer> contestantsPositions = new ArrayList<>();
        for (Contestant contestant : this.contestants) {
            contestantsPositions.add(contestant.getPosition());
        }
        return contestantsPositions;
    }

    private void getProperResultsPrinting(int contestantPosition, List<Integer>contestantsPositions, String message, String messageInLine, String ... formats){
        contestantsPositions.remove((Integer)contestantPosition);
        if (contestantsPositions.contains(contestantPosition)) {
            MessagePrinter.printMessage(message, formats);
        }
        else {
            MessagePrinter.printMessageInLine(messageInLine, formats);
        }
    }

    private void printGameResults() {
        int winPrize = this.getWinPrize();
        List<Integer> contestantsPositions = getContestantsPositions();
        for (Contestant contestant : this.contestants) {
            int contestantPosition = contestant.getPosition();
            if (!contestant.getBuster()) {
                if (contestantPosition == 1) {
                    contestant.setCash(contestant.getCash()+winPrize);
                    this.getProperResultsPrinting(contestantPosition,contestantsPositions,MessageProvider.winners, MessageProvider.winner, contestant.getName(), String.valueOf(contestant.countScore()), String.valueOf(winPrize), String.valueOf(contestant.getCash()));
                }
                else {
                    this.getProperResultsPrinting(contestantPosition,contestantsPositions,MessageProvider.players, MessageProvider.player, contestant.getName(), String.valueOf(contestant.countScore()), String.valueOf(contestant.getCash()));
                }
            }
            else {
                this.getProperResultsPrinting(contestantPosition,contestantsPositions,MessageProvider.busters, MessageProvider.buster, contestant.getName(), String.valueOf(contestant.countScore()), String.valueOf(contestant.getCash()));
            }
        }
    }
}
