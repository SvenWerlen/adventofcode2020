package org.boisdechet.adventofcode2020;

import org.boisdechet.adventofcode2020.utils.InputUtil;
import org.boisdechet.adventofcode2020.utils.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Day00 {

    /**
     * Part 1
     */
    public static long part1(String sample) throws IOException {
        BufferedReader input = InputUtil.readInput(1, sample);
        String line = "";
        // put all number in set
        Set<Integer> set = new HashSet<>();
        while ((line = input.readLine()) != null) {
            System.out.println(line);
        }
        return 0L;
    }

    /**
     * Part 2
     */
    public static long part2(String sample) throws IOException {
        BufferedReader input = InputUtil.readInput(1, sample);
        String line = "";
        // put all number in set
        Set<Integer> set = new HashSet<>();
        while ((line = input.readLine()) != null) {
            System.out.println(line);
        }
        return 0L;
    }

    public static void main(String[] args) {
        Log.welcome();
        try {
            Log.i(String.format("Answer part1 : %d", part1(null)));
            Log.i(String.format("Answer part2 : %d", part2(null)));
        } catch(Exception exc) {
            Log.w(String.format("Error during execution: %s", exc.getMessage()));
        }
        Log.bye();
    }


}
