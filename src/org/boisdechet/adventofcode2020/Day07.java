package org.boisdechet.adventofcode2020;

import org.boisdechet.adventofcode2020.utils.InputUtil;
import org.boisdechet.adventofcode2020.utils.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day07 {

    // tiny utility class
    private static class Pair {
        public Pair(int count, String bagName) { this.count = count; this.bagName = bagName; }
        String bagName;
        int count;
    }

    private static boolean containsBag(Map<String, List<Pair>> rules, String containerName, String bagName) {
        // found!
        if(containerName.equals(bagName)) {
            return true;
        }
        if(!rules.containsKey(containerName)) {
            throw new IllegalStateException("Looking for a bag that has no rule : " + containerName);
        }
        for(Pair cont : rules.get(containerName)) {
            if(containsBag(rules, cont.bagName, bagName)) {
                return true;
            }
        }
        return false;
    }

    private static long countBags(Map<String, List<Pair>> rules, String bagName) {
        if(!rules.containsKey(bagName)) {
            throw new IllegalStateException("Looking for a bag that has no rule : " + bagName);
        }
        long count = 0;
        for(Pair cont : rules.get(bagName)) {
            count +=  cont.count * countBags(rules, cont.bagName);
        }
        return count + 1;
    }


    public static Map<String, List<Pair>> compileRules(String sample) throws IOException {
        BufferedReader input = InputUtil.readInput(7, sample);
        String line = "";

        Pattern pattern1 = Pattern.compile("^([ \\w]+) bags contain (.+)\\.$");
        Pattern pattern2 = Pattern.compile("(\\d+) ([ \\w]+) bags?");
        Map<String, List<Pair>> rules = new HashMap<>();
        // read input
        while ((line = input.readLine()) != null) {
            Matcher m1 = pattern1.matcher(line);
            if(m1.matches()) {
                String container = m1.group(1);
                // check consistency #1 : only 1 rule per bag
                if(rules.containsKey(container)) { throw new IllegalStateException("2 rules for the same bag : " + container); }
                List<Pair> items = new ArrayList<>();
                Matcher m2 = pattern2.matcher(m1.group(2));
                while(m2.find()) {
                    items.add(new Pair(Integer.parseInt(m2.group(1)), m2.group(2)));
                }
                // check consistency #2 : check that regex extracted the items
                if(items.size() == 0 && !"no other bags".equals(m1.group(2))) { throw new IllegalStateException("Rule with no items : " + line); }
                rules.put(container, items);
            } else {
                throw new IllegalStateException("Invalid rule : " + line);
            }
        }
        return rules;
    }

    /**
     * Part 1
     */
    public static long part1(String sample) throws IOException {
        Map<String, List<Pair>> rules = compileRules(sample);
        // count number of bags
        long count = 0;
        for(String container : rules.keySet()) {
            if(!container.equals("shiny gold") && containsBag(rules, container, "shiny gold")) {
                count++;
            }
        }
        return count;
    }

    /**
     * Part 2
     */
    public static long part2(String sample) throws IOException {
        Map<String, List<Pair>> rules = compileRules(sample);
        return countBags(rules, "shiny gold") -1;
    }


    public static void main(String[] args) {
        Log.welcome();
        try {
            Log.i(String.format("Number of bag colors that can eventually contain at least one shiny gold bag : %d", part1(null)));
            Log.i(String.format("Individual bags inside your single shiny gold : %d", part2(null)));
        } catch(Exception exc) {
            Log.w(String.format("Error during execution: %s", exc.getMessage()));
            exc.printStackTrace();
        }
        Log.bye();
    }


}
