package org.boisdechet.adventofcode2020;

import org.boisdechet.adventofcode2020.utils.InputUtil;
import org.boisdechet.adventofcode2020.utils.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Day01 {

    /**
     * Part 1
     */
    public static long part1(String sample) throws IOException {
        BufferedReader input = InputUtil.readInput(1, sample);
        String line = "";
        // put all number in set
        Set<Integer> set = new HashSet<>();
        while ((line = input.readLine()) != null) {
            set.add(Integer.parseInt(line));
        }
        // iterate through set
        for(Integer i : set) {
            if( set.contains(2020-i) ) {
                return i * (2020-i);
            }
        }
        // not found
        throw new IllegalStateException("Part 1 was impossible to solve!");
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
            set.add(Integer.parseInt(line));
        }
        // iterate twice (1,2)
        for(Integer i1 : set) {
            for(Integer i2 : set) {
                if (i1 + i2 < 2020 && set.contains(2020 - i1 -i2)) {
                    return i1 * i2 * (2020 - i1 - i2);
                }
            }
        }
        // not found
        throw new IllegalStateException("Part 2 was impossible to solve!");
    }

    public static void main(String[] args) {
        Log.welcome();
        try {
            Log.i(String.format("Two entries multiplied : %d", part1(null)));
            Log.i(String.format("Three entries multiplied : %d", part2(null)));
        } catch(Exception exc) {
            Log.w(String.format("Error during execution: %s", exc.getMessage()));
        }
        Log.bye();
    }


}
