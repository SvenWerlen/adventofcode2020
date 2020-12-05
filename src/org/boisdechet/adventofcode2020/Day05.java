package org.boisdechet.adventofcode2020;

import org.boisdechet.adventofcode2020.utils.InputUtil;
import org.boisdechet.adventofcode2020.utils.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day05 {

    /**
     * @param idx lower value in range [idx...idx+range]
     * @param range range size
     * @param instr instructions (FBBF...)
     * @param iter iteration
     * @return
     */
    private static int getBinaryPos(int idx, int range, String instr, int iter) {
        if(iter >= instr.length() || range == 1) {
            return idx;
        }
        char pos = instr.charAt(iter);
        if( pos == 'F' || pos == 'L' ) {
            return getBinaryPos(idx, range / 2, instr, iter+1 );
        } else if (pos == 'B' || pos == 'R') {
            return getBinaryPos(idx + range / 2, range / 2, instr, iter+1);
        } else {
            throw new IllegalStateException("Invalid instruction : " + pos);
        }
    }

    private static int getSeatID(String line) {
        if(line.length() != 10) {
            throw new IllegalStateException("Invalid seat : " + line);
        }

        int row = getBinaryPos(0, 128, line, 0);
        int col = getBinaryPos(0, 8, line, 7);

        return row * 8 + col;
    }

    private static long checkSeat(String sample, boolean maxOnly) throws IOException {
        BufferedReader input = InputUtil.readInput(5, sample);
        String line = "";
        boolean[] occupied = new boolean[128 * 8];

        // read input
        long max = 0;
        while ((line = input.readLine()) != null) {
            int seatID = getSeatID(line);
            occupied[seatID] = true;
            if(seatID > max) {
                max = seatID;
            }
        }

        // part 1 : find maximum seat
        if(maxOnly) {
            return max;
        }

        // part 2 : find unoccupied seat
        for(int i = 1; i<128*8-2; i++) {
            if(occupied[i-1] && occupied[i+1] && !occupied[i]) {
                return i;
            }
        }
        throw new IllegalStateException("No seat found!");
    }

    /**
     * Part 1
     */
    public static long part1(String sample) throws IOException {
        return checkSeat(sample, true);
    }

    /**
     * Part 2
     */
    public static long part2(String sample) throws IOException {
        return checkSeat(sample, false);
    }


    public static void main(String[] args) {
        Log.welcome();
        try {
            Log.i(String.format("Highest seat ID on a boarding pass : %d", part1(null)));
            Log.i(String.format("My seat is : %d", part2(null)));
        } catch(Exception exc) {
            Log.w(String.format("Error during execution: %s", exc.getMessage()));
        }
        Log.bye();
    }


}
