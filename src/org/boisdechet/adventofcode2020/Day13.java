package org.boisdechet.adventofcode2020;

import org.boisdechet.adventofcode2020.utils.InputUtil;
import org.boisdechet.adventofcode2020.utils.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day13 {

    /**
     * Part 1
     */
    public static long part1(String sample) throws IOException {
        String input = InputUtil.readInputAsString(13, sample);
        int time = Integer.parseInt( input.split("\n")[0] );
        int bestBus = 0;
        int bestWait = -1;
        for(String busNumber : input.split("\n")[1].split(",")) {
            if(!busNumber.equals("x")) {
                int bus = Integer.parseInt(busNumber);
                int wait = bus - (time % bus);
                if(bestWait < 0 || bestWait > wait) {
                    bestBus = bus;
                    bestWait = wait;
                }
            }
        }
        return bestBus * bestWait;
    }

    /**
     * Part 2
     *
     * Wasn't able to find by myself. I understood after reading:
     * https://www.reddit.com/r/adventofcode/comments/kc60ri/2020_day_13_can_anyone_give_me_a_hint_for_part_2/
     *
     *
     */
    public static long part2(String sample) throws IOException {
        String buses = sample != null ? sample : InputUtil.readInputAsString(13, null).split("\n")[1];
        long earliest = -1;  // current earliest
        long offset = 0;     // bus offset
        long lcm = 0;        // least common multiplier
        for(String busNumber : buses.split(",")) {
            if(!busNumber.equals("x")) {
                int bus = Integer.parseInt(busNumber);
                if (earliest < 0) {
                    earliest = bus;
                    lcm = bus;
                } else {
                    while(true) {
                        earliest += lcm;
                        if((earliest + offset) % bus == 0) {
                            lcm *= bus; // all bus numbers are primary numbers
                            break;
                        }
                    }
                }
            }
            offset++;
        }
        return earliest;
    }

    public static void main(String[] args) {
        Log.welcome();
        try {
            Log.i(String.format("ID of the earliest bus x waiting time : %d", part1(null)));
            Log.i(String.format("Earliest timestamp : %d", part2(null)));
        } catch(Exception exc) {
            Log.w(String.format("Error during execution: %s", exc.getMessage()));
        }
        Log.bye();
    }


}
