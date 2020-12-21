package org.boisdechet.adventofcode2020;

import org.boisdechet.adventofcode2020.utils.InputUtil;
import org.boisdechet.adventofcode2020.utils.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day19 {

    // global variables
    private static String regex31 = null;
    private static String regex42 = null;

    private static String buildRegex(Map<Integer, String> rules, String rule, int replaceCount) {
        int idx = rule.indexOf('|');
        if(idx >=0) {
            return String.format("(?:%s|%s)", buildRegex(rules, rule.substring(0, idx), replaceCount), buildRegex(rules, rule.substring(idx+1), replaceCount));
        }
        idx = rule.indexOf('"');
        if(idx >= 0) {
            return rule.substring(idx+1,idx+2); // assuming that puzzle can only have 1 single character
        }
        StringBuilder builder = new StringBuilder();
        String[] parts = rule.trim().split(" ");
        for(String p : parts) {
            int ruleNo = Integer.parseInt(p);
            if(replaceCount > 0 && ruleNo == 8) {
                builder.append(String.format("(?:%s)+", regex42));
            } else if(replaceCount > 0 && ruleNo == 11) {
                builder.append(regex42.repeat(replaceCount)).append(regex31.repeat(replaceCount));
            } else {
                builder.append(buildRegex(rules, rules.get(ruleNo), replaceCount));
            }
        }
        return builder.toString();
    }

    public static long solve(String sample, boolean replace) throws IOException {
        // read puzzle input
        BufferedReader input = InputUtil.readInput(19, sample);
        String line = "";
        boolean isMsg = false;
        List<String> messages = new ArrayList<>();
        Map<Integer, String> rules = new HashMap<>();
        while ((line = input.readLine()) != null) {
            if(line.length() == 0) { isMsg = true; continue; }
            if(isMsg) messages.add(line);
            else {
                int idx = line.indexOf(':');
                rules.put(Integer.parseInt(line.substring(0, idx)), line.substring(idx+2));
            }
        }

        if(replace) {
            regex31 = buildRegex(rules, rules.get(31), 0);
            regex42 = buildRegex(rules, rules.get(42), 0);
        }

        //System.out.println("Regex : " + regex);
        List<Pattern> patterns = new ArrayList<>();
        if(!replace) {
            patterns.add(Pattern.compile(buildRegex(rules, rules.get(0), 0)));
        } else {
            for(int i=1; i<=10; i++) {
                patterns.add(Pattern.compile(buildRegex(rules, rules.get(0), i)));
            }
        }

        long count = 0L;
        for(String msg : messages) {
            for(Pattern p : patterns) {
                if (p.matcher(msg).matches()) {
                    count++;
                    break;
                }
            }
        }
        return count;
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
            Log.i(String.format("Messages completely matching rule 0 : %d", part1(null)));
            Log.i(String.format("Messages completely matching rule 0 : %d", part2(null)));
        } catch(Exception exc) {
            Log.w(String.format("Error during execution: %s", exc.getMessage()));
            exc.printStackTrace();
        }
        Log.bye();
    }


}
