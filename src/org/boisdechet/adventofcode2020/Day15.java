package org.boisdechet.adventofcode2020;

import org.boisdechet.adventofcode2020.utils.InputUtil;
import org.boisdechet.adventofcode2020.utils.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class Day15 {

    private static long execute(String input, int iterations) {
        String[] values = input.split(",");
        Map<Integer, Integer> results = new HashMap<>(); // key : value spoken, value : last time
        int lastSpoken = 0;
        for(int i=1; i<=iterations; i++) {
            int prevSpoken = lastSpoken;
            if(i <= values.length) {
                lastSpoken = Integer.parseInt(values[i-1]);
            } else if(results.containsKey(lastSpoken)) {
                lastSpoken = i-1-results.get(lastSpoken);
            } else {
                lastSpoken = 0;
            }
            if(i>1) results.put(prevSpoken, i-1);
            //System.out.println(String.format("%d : %d", i, lastSpoken));
        }
        return lastSpoken;
    }

    /**
     * Part 1
     */
    public static long part1(String input) throws IOException {
        return execute(input, 2020);
    }

    /**
     * Part 2
     */
    public static long part2(String input) throws IOException {
        return execute(input, 30000000);
    }

    public static void main(String[] args) {
        Log.welcome();
        try {
            Log.i(String.format("2020th number spoken : %d", part1("9,6,0,10,18,2,1")));
            Log.i(String.format("30000000th number spoken : %d", part2("9,6,0,10,18,2,1")));
        } catch(Exception exc) {
            Log.w(String.format("Error during execution: %s", exc.getMessage()));
        }
        Log.bye();
    }


}
