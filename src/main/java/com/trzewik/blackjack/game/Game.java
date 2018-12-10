package com.trzewik.blackjack.game;

import java.util.*;

import com.trzewik.blackjack.deck.Deck;
import com.trzewik.blackjack.deck.enums.MoveType;
import com.trzewik.blackjack.players.Contestant;
import com.trzewik.blackjack.players.Croupier;
import com.trzewik.blackjack.players.Player;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Game {

    private List<Player> players;
    private Croupier croupier;
    private Deck deck;
    private List<Contestant> contestants;

    public Game() {
        this.deck = new Deck();
        this.croupier = new Croupier();
        this.players = new ArrayList<>();
    }

    public Croupier getCroupier() {
        return croupier;
    }

    public Deck getDeck() {
        return deck;
    }

    public List<Contestant> getContestants() {
        return contestants;
    }

    public void setContestants(List<Contestant> contestants) {
        this.contestants = contestants;
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public void createPlayer(String name, int cash) {
        Player player = new Player(name, cash);
        this.players.add(player);
    }

    public List<Player> getPlayersWithPossibleMove() {
        return this.players.stream()
                .filter(Player::hasPossibleMove)
                .collect(Collectors.toList());
    }

    public void createContestants() {
        this.contestants = new ArrayList<>(this.players);
        contestants.add(croupier);
    }

    public void dealCards() {
        for (int i = 0; i < 2; i++) {
            for (Contestant contestant : this.contestants) {
                contestant.addCardToHand(this.deck.getCard());
            }
        }
    }

    public void playerBetting(Player player, int betValue) {
        player.setBetValue(betValue);
        croupier.getMoneyFromPlayer(player);
    }

    public void playerAuction(Player player, MoveType choice) {
        if (player.getHand().size() >= 11) {
            player.setLastMove(MoveType.STAND);
        }
        if (player.hasPossibleMove()) {
            player.setLastMove(choice);
            gameAction(choice, player);
        }
    }

    public boolean anyPlayerHaveMove() {
        return getPlayersWithPossibleMove().size() > 0;
    }

    private void gameAction(MoveType choice, Player player) {
        switch (choice) {
            case HIT:
                player.addCardToHand(deck.getCard());
                break;
            case DOUBLE_DOWN:
                player.addCardToHand(deck.getCard());
                croupier.getMoneyFromPlayerIfDoubleDown(player);
                break;
        }
    }

    public List<Contestant> sortContestants() {
        List<Contestant> sortedContestants = new ArrayList<>(getSortedWinners());
        sortedContestants.addAll(getSortedBusters());
        return sortedContestants;
    }

    private List<Contestant> getSortedBusters() {
        return this.contestants.stream()
                .filter(Contestant::checkIfBuster)
                .sorted(Comparator.comparing(Contestant::countScore))
                .collect(Collectors.toList());
    }

    private List<Contestant> getSortedWinners() {
        return this.contestants.stream()
                .filter(contestant -> !contestant.checkIfBuster())
                .sorted(Comparator.comparing(Contestant::countScore).reversed())
                .collect(Collectors.toList());
    }

    public void setContestantsPositions() {
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

    public void setWinnersCash() {
        this.players.stream()
                .filter(player -> player.getPosition() == 1)
                .forEach(player -> player.setCash(player.getCash() + this.getWinPrize()));
    }

    public int getWinPrize() {
        List<Contestant> winners = new ArrayList<>();
        for (Contestant contestant : this.contestants) {
            if (contestant.getPosition() == 1) {
                winners.add(contestant);
            }
        }
        return this.croupier.getCasino() / winners.size();
    }
}