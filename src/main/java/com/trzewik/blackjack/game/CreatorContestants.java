package com.trzewik.blackjack.game;

import com.trzewik.blackjack.players.Contestant;
import com.trzewik.blackjack.players.Croupier;
import com.trzewik.blackjack.players.Player;

import java.util.ArrayList;
import java.util.List;


public class CreatorContestants {

    interface Contestants{
        List<Contestant> allContestants(List<Player> players, Croupier croupier);
    }

    public static void main(String ... args){

        Contestants createContestants = (players, croupier) -> {
            List<Contestant> contestants = new ArrayList<>(players);
            contestants.add(croupier);
            return contestants;
        };
    }
}
