package org.boisdechet.adventofcode2020;

import org.boisdechet.adventofcode2020.utils.InputUtil;
import org.boisdechet.adventofcode2020.utils.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day06 {

    /**
     * Part 1
     */
    public static long part1(String sample) throws IOException {
        BufferedReader input = InputUtil.readInput(6, sample);
        String line = "";

        Set<Character> answers = new HashSet<>();
        // read input
        long count = 0;
        while ((line = input.readLine()) != null) {
            if(line.length() == 0) {
                count += answers.size();
                answers.clear();
            } else {
                for(int i=0; i<line.length(); i++) {
                    answers.add(line.charAt(i));
                }
            }
        }
        count += answers.size();
        return count;
    }

    /**
     * Part 2
     */
    public static long part2(String sample) throws IOException {
        BufferedReader input = InputUtil.readInput(6, sample);
        String line = "";

        Set<Character> answers = new HashSet<>();
        // read input
        long count = 0;
        boolean newGroup = true;
        while ((line = input.readLine()) != null) {
            if(line.length() == 0) {
                count += answers.size();
                answers.clear();
                newGroup = true;
            } else if(newGroup) {
                newGroup = false;
                for(int i=0; i<line.length(); i++) {
                    answers.add(line.charAt(i));
                }
            } else {
                List<Character> copy = new ArrayList<>(answers);
                for(Character c : copy) {
                    if( line.indexOf(c) < 0) {
                        answers.remove(c);
                    }
                }
            }
        }
        count += answers.size();
        return count;
    }


    public static void main(String[] args) {
        Log.welcome();
        try {
            Log.i(String.format("Sum of questions with anyone answers \"yes\" : %d", part1(null)));
            Log.i(String.format("Sum of questions with everyone answers \"yes\" : %d", part2(null)));
        } catch(Exception exc) {
            Log.w(String.format("Error during execution: %s", exc.getMessage()));
            exc.printStackTrace();
        }
        Log.bye();
    }


}
