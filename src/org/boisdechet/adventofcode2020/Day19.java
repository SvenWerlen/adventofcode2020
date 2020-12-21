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
    private static Pattern pattern31 = null;
    private static Pattern pattern42 = null;


    private static String buildRegex(Map<Integer, String> rules, String rule, boolean replace) {
        int idx = rule.indexOf('|');
        if(idx >=0) {
            return String.format("(?:%s|%s)", buildRegex(rules, rule.substring(0, idx), replace), buildRegex(rules, rule.substring(idx+1), replace));
        }
        idx = rule.indexOf('"');
        if(idx >= 0) {
            return rule.substring(idx+1,idx+2); // assuming that puzzle can only have 1 single character
        }
        StringBuilder builder = new StringBuilder();
        String[] parts = rule.trim().split(" ");
        for(String p : parts) {
            int ruleNo = Integer.parseInt(p);
            if(replace && ruleNo == 8) {
                builder.append(String.format("(?:%s)+", regex42));
            } else if(replace && ruleNo == 11) {
                builder.append(String.format("(%s+)(%s+)", regex42, regex31));
            } else {
                builder.append(buildRegex(rules, rules.get(ruleNo), replace));
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
            regex31 = buildRegex(rules, rules.get(31), true);
            regex42 = buildRegex(rules, rules.get(42), true);
            pattern31 = Pattern.compile(regex31);
            pattern42 = Pattern.compile(regex42);
            //System.out.println(regex31);
            //System.out.println(regex42);
        }

        String regex = buildRegex(rules, rules.get(0), replace);
        //System.out.println("Regex : " + regex);
        Pattern p = Pattern.compile(regex);

        long count = 0L;
        for(String msg : messages) {
            Matcher m = p.matcher(msg);
            if(m.matches()) {
                if(m.groupCount() != 0) {
                    long count1 = pattern42.matcher(m.group(1)).results().count();
                    long count2 = pattern31.matcher(m.group(2)).results().count();
                    System.out.println(count1 + " vs " + count2);
                    if(count1 != count2) continue;
                }
                count++;
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
