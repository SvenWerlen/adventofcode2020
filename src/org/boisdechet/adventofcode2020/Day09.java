package org.boisdechet.adventofcode2020;

import org.boisdechet.adventofcode2020.handshell.HandshellMachine;
import org.boisdechet.adventofcode2020.handshell.Instruction;
import org.boisdechet.adventofcode2020.utils.InputUtil;
import org.boisdechet.adventofcode2020.utils.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class Day09 {

    private static class Result {
        long invalid;
        long weakness;
    }

    private static Result solve(String sample) throws IOException {
        BufferedReader input = InputUtil.readInput(9, sample);
        String line = "";

        int preamble = sample != null ? 5 : 25;

        List<Integer> all = new ArrayList<>();
        LinkedList<Integer> lastX = new LinkedList<>();
        Result result = new Result();

        // read input
        while ((line = input.readLine()) != null) {
            Integer value = Integer.parseInt(line);
            lastX.add(value);
            all.add(value);

            // check if sum of 25 precedent
            if(lastX.size() > preamble) {
                boolean found = false;
                for(Integer val : lastX) {
                    if( lastX.contains(value-val) ) {
                        found = true;
                        break;
                    }
                }
                if(!found) {
                    result.invalid = value;
                    break;
                }
                lastX.remove();
            }
        }

        // find weakness
        LinkedList<Integer> contig = new LinkedList<>();
        int sum = 0;
        for(Integer val : all) {
            if(contig.size() > 1 && sum == result.invalid) {
                result.weakness = Collections.min(contig) + Collections.max(contig);
                break;
            }
            contig.add(val);
            sum += val;
            while(sum > result.invalid) {
                sum -= contig.poll();
            }
        }

        return result;
    }

    /**
     * Part 1
     */
    public static long part1(String sample) throws IOException {
        return solve(sample).invalid;
    }

    /**
     * Part 2
     */
    public static long part2(String sample) throws IOException {
        return solve(sample).weakness;
    }


    public static void main(String[] args) {
        Log.welcome();
        try {
            Log.i(String.format("First number that does not have this property : %d", part1(null)));
            Log.i(String.format("Encryption weakness : %d", part2(null)));
        } catch(Exception exc) {
            Log.w(String.format("Error during execution: %s", exc.getMessage()));
            exc.printStackTrace();
        }
        Log.bye();
    }


}
