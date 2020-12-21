package org.boisdechet.adventofcode2020;

import org.boisdechet.adventofcode2020.utils.InputUtil;
import org.boisdechet.adventofcode2020.utils.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day18 {

    public static final int SIGN_PLUS = 0;
    public static final int SIGN_MULT = 1;

    /**
     * Applies sign in order
     */
    public static long computeBasic(String equation) {
        long total = 0;
        int sign = SIGN_PLUS;
        String[] parts = equation.split(" ");
        for (String p : parts) {
            if ("+".equals(p)) sign = SIGN_PLUS;
            else if ("*".equals(p)) sign = SIGN_MULT;
            else {
                int value = Integer.parseInt(p);
                if (sign == SIGN_PLUS) total += value;
                if (sign == SIGN_MULT) total *= value;
            }
        }
        return total;
    }

    /**
     * Applies sign + first and * second
     */
    public static long computeAdvanced(String equation) {
        long total = 1;

        // solve + signs first
        List<Long> values = new ArrayList<>();
        long lastVal = 0;
        int sign = SIGN_PLUS;
        String[] parts = equation.split(" ");
        for (String p : parts) {
            if ("+".equals(p)) sign = SIGN_PLUS;
            else if ("*".equals(p)) sign = SIGN_MULT;
            else {
                long value = Long.parseLong(p);
                if (sign == SIGN_PLUS) { lastVal += value; }
                if (sign == SIGN_MULT) { values.add(lastVal); lastVal = value; }
            }
        }
        values.add(lastVal);
        // solve * signs then
        for(Long i : values) {
            total *= i;
        }

        return total;
    }


    public static long compute(String equation, boolean advanced) {
        long total = 0;
        //System.out.println(equation);

        int idxPar = equation.indexOf('(');
        // equation has not parenthesis => straight-forward
        if(idxPar < 0) {
            return advanced ? computeAdvanced(equation) : computeBasic(equation);
        }
        // equation has parenthesis
        // find and solve most embedded part
        for(int i=idxPar+1; i<equation.length(); i++) {
            if(equation.charAt(i) == '(') idxPar = i;
            else if(equation.charAt(i) == ')') {
                long result = compute(equation.substring(idxPar + 1, i), advanced);
                // replace part in parenthesis with result
                return compute(equation.substring(0, idxPar) + result + equation.substring(i+1), advanced);
            }
        }
        throw new IllegalStateException("Impossible to solve because of parenthesis!");
    }

    /**
     * Part 1
     */
    public static long part1(String sample) throws IOException {
        long result = 0L;
        if(sample != null) {
            result = compute(sample, false);
        } else {
            BufferedReader input = InputUtil.readInput(18, sample);
            String line = "";
            while ((line = input.readLine()) != null) {
                if(line.length() > 0) {
                    result += compute(line, false);
                }
            }
        }
        return result;
    }

    /**
     * Part 2
     */
    public static long part2(String sample) throws IOException {
        long result = 0L;
        if(sample != null) {
            result = compute(sample, true);
        } else {
            BufferedReader input = InputUtil.readInput(18, sample);
            String line = "";
            while ((line = input.readLine()) != null) {
                if(line.length() > 0) {
                    result += compute(line, true);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Log.welcome();
        try {
            Log.i(String.format("Sum of the resulting values: %d", part1(null)));
            Log.i(String.format("Sum of the resulting values (new rules): %d", part2(null)));
        } catch(Exception exc) {
            Log.w(String.format("Error during execution: %s", exc.getMessage()));
        }
        Log.bye();
    }


}
