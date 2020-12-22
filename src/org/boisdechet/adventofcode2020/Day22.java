package org.boisdechet.adventofcode2020;

import org.boisdechet.adventofcode2020.utils.InputUtil;
import org.boisdechet.adventofcode2020.utils.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day22 {

    private static final int HASH = 50;

    private static class Player {
        Integer playerId;
        Queue<Integer> deck;

        private Player() { deck = new LinkedList<>(); }

        public Player(Integer playerId) {
            this.playerId = playerId;
            deck = new LinkedList<>();
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            //builder.append(String.format("Player %d's deck: ", playerId));
            builder.append('[');
            for(Integer i : deck) {
                builder.append(i).append(", ");
            }
            if(builder.length() > 2) {
                builder.deleteCharAt(builder.length() - 1);
                builder.deleteCharAt(builder.length() - 1);
            }
            builder.append(']');
            return builder.toString();
        }
    }

    private static int hashGame(Player p1, Player p2) {
        int[] values = new int[p1.deck.size() + p2.deck.size() + 1];
        int idx = 0;
        for(Integer i : p1.deck) values[idx++] = i;
        values[idx++] = 0;
        for(Integer i : p2.deck) values[idx++] = i;
        return Arrays.hashCode(values);
    }

    private static int hashGameString(Player p1, Player p2) {
        StringBuilder builder = new StringBuilder();
        for(Integer i : p1.deck) {
            builder.append(i).append(':');
        }
        builder.append("#");
        for(Integer i : p2.deck) {
            builder.append(i).append(':');
        }
        return builder.toString().hashCode();
    }



    /**
     * Returns the player of that game
     */
    private static Player game(Player p1, Player p2, boolean recursive, int game) {
        long round = 1;
        Set<Integer> alreadyPlayed = new HashSet<>();

        while(p1.deck.size() > 0 && p2.deck.size() > 0) {

            int hash = hashGame(p1, p2);
            //System.out.println(hash);
            if(alreadyPlayed.contains(hash)) {
                return p1;
            }
            else alreadyPlayed.add(hash);

            Integer card1 = p1.deck.poll();
            Integer card2 = p2.deck.poll();
            if(card1 == null | card2 == null) throw new IllegalStateException("Invalid cards!");
            Player winner = card1 > card2 ? p1 : p2;
            if(recursive && card1 <= p1.deck.size() && card2 <= p2.deck.size()) {
                Player copyP1 = new Player(p1.playerId);
                List<Integer> copyList1 = new ArrayList<>(p1.deck);
                copyP1.deck.addAll(copyList1.subList(0, card1));
                Player copyP2 = new Player(p2.playerId);
                List<Integer> copyList2 = new ArrayList<>(p2.deck);
                copyP2.deck.addAll(copyList2.subList(0, card2));
                winner = game(copyP1, copyP2, true, game+1);
                winner = winner == copyP1 ? p1 : p2;
            }
            if(winner == p1) {
                p1.deck.add(card1);
                p1.deck.add(card2);
            } else {
                p2.deck.add(card2);
                p2.deck.add(card1);
            }
            round++;
        }
        //System.out.println(p1.deck.size() > 0 ? p1 : p2);
        return p1.deck.size() > 0 ? p1 : p2;
    }

    public static long solve(String sample, boolean recursive) throws IOException {
        BufferedReader input = InputUtil.readInput(22, sample);
        String line = "";

        Player p1 = null, p2 = null;
        Pattern p = Pattern.compile("Player (\\d+):");
        while ((line = input.readLine()) != null) {
            if(line.length() == 0) continue;
            Matcher m = p.matcher(line);
            if(m.matches()) {
                if(p1 == null) p1 = new Player(Integer.parseInt(m.group(1)));
                else p2 = new Player(Integer.parseInt(m.group(1)));
            } else {
                if(p1 == null) throw new IllegalStateException("No player initialized!");
                if(p2 == null) p1.deck.add(Integer.parseInt(line));
                else p2.deck.add(Integer.parseInt(line));
            }
        }

        if(p1 == null || p2 == null) throw new IllegalStateException("Initialization failed!");

        long result = 0;
        Player winner = game(p1, p2, recursive, 1);
        for(int i = winner.deck.size(); i > 0; i--) {
            Integer card = winner.deck.poll();
            if(card == null) throw new IllegalStateException("Invalid card!");
            result += i * card;
        }

        return result;
    }

    /**
     * Part 1
     */
    public static long part1(String sample) throws IOException {
        return solve(sample, false);
    }

    /**
     * Part 2
     */
    public static long part2(String sample) throws IOException {
        return solve(sample, true);
    }

    public static void main(String[] args) {
        Log.welcome();
        try {
            Log.i(String.format("Winning player's score : %d", part1(null)));
            Log.i(String.format("Winning player's score (recursive) : %d", part2(null)));
        } catch(Exception exc) {
            Log.w(String.format("Error during execution: %s", exc.getMessage()));
        }
        Log.bye();
    }


}
