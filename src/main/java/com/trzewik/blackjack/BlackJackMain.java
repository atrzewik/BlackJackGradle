package com.trzewik.blackjack;


import com.trzewik.blackjack.deck.enums.MoveType;
import com.trzewik.blackjack.game.Game;
import com.trzewik.blackjack.players.Contestant;
import com.trzewik.blackjack.players.Player;
import com.trzewik.userinputprovider.MessagePrinter;
import com.trzewik.userinputprovider.MessageProvider;
import com.trzewik.userinputprovider.UserInputMatcher;
import com.trzewik.userinputprovider.UserInputProvider;

public class BlackJackMain {
    public static void main(String[] args) {
        Game game = new Game();
        createPlayers(game, 2);
        game.createContestants();
        game.dealCards();
        MessagePrinter.printMessageInLine(MessageProvider.collectCardFromCroupier + game.getCroupier().cardsToPrint());
        playersBetting(game);
        playersAuction(game);
        getCroupierCards(game);
        game.setContestants(game.sortContestants());
        game.setContestantsPositions();
        game.setWinnersCash();
        printGameResults(game);
    }

    private static void createPlayers(Game game, int numberOfPlayers) {
        for (int i = 0; i < numberOfPlayers; i++) {
            String name = UserInputProvider.collectString(MessageProvider.collectName);
            int cash = UserInputProvider.collectIntegerInRangeMin(1, MessageProvider.collectCash, name);
            game.createPlayer(name, cash);
        }
    }

    private static void playersBetting(Game game) {
        for (Player player : game.getPlayers()) {
            MessagePrinter.printMessageInLine(MessageProvider.tellPlayerHandPoints, player.getName(), player.cardsToPrint().toString(), String.valueOf(player.countScore()));
            game.playerBetting(player, UserInputProvider.collectIntegerInRangeMinMax(1, player.getCash(), MessageProvider.askPlayerForBet, player.getName()));
        }
    }

    private static void playersAuction(Game game) {
        while (game.anyPlayerHaveMove()) {
            for (Player player : game.getPlayersWithPossibleMove()) {
                MessagePrinter.printMessageInLine(MessageProvider.tellPlayerHandPoints, player.getName(), player.getHand().toString(), String.valueOf(player.countScore()));
                game.playerAuction(player, getUserChoice(player));
                if (player.getLastMove() == MoveType.DOUBLE_DOWN) {
                    MessagePrinter.printMessageInLine(MessageProvider.getTellPlayerHandPointsBet, player.getHand().toString(), String.valueOf(player.countScore()), String.valueOf(player.getBetValue()));
                }
            }
        }
    }

    private static MoveType getUserChoice(Player player) {
        if (player.getCash() >= player.getBetValue() && player.getLastMove() == null) {
            return UserInputMatcher.collectProperMoveType(MoveType.values(), MessageProvider.askPlayerForHitStandDouble, player.getName());
        } else
            return UserInputMatcher.collectProperMoveType(MoveType.getHitStand(), MessageProvider.askPlayerForHitStand, player.getName());
    }

    private static void getCroupierCards(Game game) {
        while (game.getCroupier().shouldDrawCards()) {
            MessagePrinter.printMessageInLine(MessageProvider.tellCroupierHandPoints, game.getCroupier().getHand().toString(), String.valueOf(game.getCroupier().countScore()));
            game.getCroupier().addCardToHand(game.getDeck().getCard());
        }
        MessagePrinter.printMessageInLine(MessageProvider.tellCroupierHandPoints, game.getCroupier().getHand().toString(), String.valueOf(game.getCroupier().countScore()));
    }

    private static void printGameResults(Game game) {
        for (int i = 0; i < game.getContestants().size(); i++) {
            Contestant currentContestant = game.getContestants().get(i);
            int contestantPosition = currentContestant.getPosition();
            if (!currentContestant.checkIfBuster()) {
                if (contestantPosition == 1) {
                    getProperResultsPrinting(game, i, currentContestant, MessageProvider.winners, MessageProvider.winner, currentContestant.getName(), String.valueOf(currentContestant.countScore()), String.valueOf(game.getWinPrize()), String.valueOf(currentContestant.getCash()));
                } else {
                    getProperResultsPrinting(game, i, currentContestant, MessageProvider.players, MessageProvider.player, currentContestant.getName(), String.valueOf(currentContestant.countScore()), String.valueOf(currentContestant.getCash()));
                }
            } else {
                getProperResultsPrinting(game, i, currentContestant, MessageProvider.busters, MessageProvider.buster, currentContestant.getName(), String.valueOf(currentContestant.countScore()), String.valueOf(currentContestant.getCash()));
            }
        }
    }

    private static void getProperResultsPrinting(Game game, int placeOfCurrentContestant, Contestant currentContestant, String message, String messageInLine, String... formats) {
        Contestant lastContestant = game.getContestants().get(game.getContestants().size() - 1);
        int placeOfNextContestant = placeOfCurrentContestant + 1;
        if (currentContestant == lastContestant) {
            MessagePrinter.printMessageInLine(messageInLine, formats);
        } else if (currentContestant.getPosition().equals(game.getContestants().get(placeOfNextContestant).getPosition())) {
            MessagePrinter.printMessage(message, formats);
        } else {
            MessagePrinter.printMessageInLine(messageInLine, formats);
        }
    }
}