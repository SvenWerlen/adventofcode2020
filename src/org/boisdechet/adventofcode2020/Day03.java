package org.boisdechet.adventofcode2020;

import org.boisdechet.adventofcode2020.utils.InputUtil;
import org.boisdechet.adventofcode2020.utils.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03 {

    /**
     * Part 1
     */
    public static long part1(String sample) throws IOException {
        BufferedReader input = InputUtil.readInput(3, sample);
        String line = "";

        // read input
        List<Boolean[]> grid = new ArrayList<>();
        while ((line = input.readLine()) != null) {
            Boolean[] row = new Boolean[line.length()];
            int idx = 0;
            for(Character c : line.toCharArray()) {
                row[idx++] = c == '#';
            }
            grid.add(row);
        }

        // compute
        int count = 0;
        int x = 0;
        for(Boolean[] b : grid) {
            if( b[x] ) count++;
            x = ( x + 3 ) % b.length;
        }

        return count;
    }

    /**
     * Part 2
     */
    public static long part2(String sample) throws IOException {
        BufferedReader input = InputUtil.readInput(3, sample);
        String line = "";

        // read input
        List<Boolean[]> grid = new ArrayList<>();
        while ((line = input.readLine()) != null) {
            Boolean[] row = new Boolean[line.length()];
            int idx = 0;
            for(Character c : line.toCharArray()) {
                row[idx++] = c == '#';
            }
            grid.add(row);
        }

        final int[][] CASES = { {1,1}, {3,1}, {5,1}, {7,1}, {1,2} };

        // compute
        long totalCount = 1;
        for( int[] c : CASES ) {
            int incX = c[0];
            int incY = c[1];
            int x = 0;
            int count = 0;
            for (int y = 0; y < grid.size(); y += incY) {
                if (grid.get(y)[x]) count++;
                x = (x + incX) % grid.get(y).length;
            }
            totalCount *= count;
        }

        return totalCount;
    }

    public static void main(String[] args) {
        Log.welcome();
        try {
            Log.i(String.format("Number of trees encountered : %d", part1(null)));
            Log.i(String.format("Number of trees encountered : %d", part2(null)));
        } catch(Exception exc) {
            Log.w(String.format("Error during execution: %s", exc.getMessage()));
        }
        Log.bye();
    }


}
