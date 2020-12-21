package org.boisdechet.adventofcode2020;

import org.boisdechet.adventofcode2020.utils.InputUtil;
import org.boisdechet.adventofcode2020.utils.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day16 {

    private final static int SECTION_RULES = 0;
    private final static int SECTION_TICKET = 1;
    private final static int SECTION_NEARBY = 2;

    /**
     * Ticket rule
     */
    private static class Rule implements Comparable<Rule> {
        String name;
        int from1, to1, from2, to2;
        List<Integer> validFor = new ArrayList<>();

        public Rule(String name, int from1, int to1, int from2, int to2) {
            this.name = name;
            this.from1 = from1; this.to1 = to1;
            this.from2 = from2; this.to2 = to2;
        }

        public boolean isValid(int number) {
            return (number >= from1 && number <= to1) || (number >= from2 && number <= to2);
        }

        @Override
        public int compareTo(Rule rule) {
            return Integer.compare(this.validFor.size(), rule.validFor.size());
        }
    }

    /**
     * Function to check if nearby ticket is valid for any rule
     */
    private static boolean isValid(List<Rule> rules, int value) {
        for(Rule r : rules) {
            if(r.isValid(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Class for keeping all parsed data
     */
    private static class Infos {
        List<Rule> rules = new ArrayList<>();
        List<Integer> ticket = new ArrayList<>();
        List<List<Integer>> validNearby = new ArrayList<>();
        long errorRate = 0L;
    }

    private static Infos parse(String sample) throws IOException {
        BufferedReader input = InputUtil.readInput(16, sample);
        String line = "";

        Infos infos = new Infos();
        int section = SECTION_RULES;
        Pattern p = Pattern.compile("(.*): (\\d+)-(\\d+) or (\\d+)-(\\d+)");
        while ((line = input.readLine()) != null) {
            if(line.length() == 0) {
                continue;
            }
            else if(line.startsWith("your ticket:")) {
                section = SECTION_TICKET;
                continue;
            } else if(line.startsWith("nearby tickets:")) {
                section = SECTION_NEARBY;
                continue;
            }
            if(section == SECTION_RULES) {
                Matcher m = p.matcher(line);
                if (m.matches()) {
                    Rule rule = new Rule(m.group(1),
                            Integer.parseInt(m.group(2)),
                            Integer.parseInt(m.group(3)),
                            Integer.parseInt(m.group(4)),
                            Integer.parseInt(m.group(5)));
                    infos.rules.add(rule);
                } else {
                    throw new IllegalStateException("Invalid rule : " + line);
                }
            } else if(section == SECTION_TICKET) {
                String[] values = line.split(",");
                for(String val : values) {
                    infos.ticket.add(Integer.parseInt(val));
                }
            } else if(section == SECTION_NEARBY) {
                String[] values = line.split(",");
                List<Integer> numbers = new ArrayList<>();
                boolean isValid = true;
                for(String val : values) {
                    int value = Integer.parseInt(val);
                    numbers.add(value);
                    if(!isValid(infos.rules, value)) {
                        isValid = false;
                        infos.errorRate += value;
                    }
                }
                if(isValid) {
                    infos.validNearby.add(numbers);
                }
            }
        }
        return infos;
    }

    /**
     * Part 1
     */
    public static long part1(String sample) throws IOException {
        return parse(sample).errorRate;
    }

    private static List<Integer> validCombination(Infos infos, List<Integer> rulesSeq, int idx) {
        if(idx>=infos.rules.size()) {
            return rulesSeq;
        }
        Rule rule = infos.rules.get(idx);
        Set<Integer> assigned = new HashSet<>(rulesSeq);
        for(int i=0; i<infos.ticket.size(); i++) {
            if(assigned.contains(i)) continue;
            boolean valid = true;
            for(List<Integer> nearby : infos.validNearby) {
                if(!rule.isValid(nearby.get(i))) {
                    valid = false;
                    break;
                }
            }
            if(valid) {
                List<Integer> newSeq = new ArrayList<>(rulesSeq);
                newSeq.add(i);
                List<Integer> found = validCombination(infos, newSeq, idx+1);
                if(found != null) return found;
            }
        }
        return null;
    }

    private static void setRulesValidFor(Infos infos) {
        for(Rule r : infos.rules) {
            for(int idx = 0; idx<infos.ticket.size(); idx++) {
                boolean valid = true;
                for(List<Integer> nearby : infos.validNearby) {
                    if(!r.isValid(nearby.get(idx))) {
                        valid = false;
                        break;
                    }
                }
                if(valid) {
                    r.validFor.add(idx);
                }
            }
        }
    }

    /**
     * Part 2
     */
    public static long part2(String sample) throws IOException {
        Infos infos = parse(sample);
        setRulesValidFor(infos);
        Collections.sort(infos.rules); // sort by number of valid positions (speed-up)
        List<Integer> combination = validCombination(infos, new ArrayList<>(), 0);
        if(combination == null) throw new IllegalStateException("No solution found!");
        long result = 1L;
        for(int idx = 0; idx<combination.size(); idx++) {
            Rule r = infos.rules.get(idx);
            if(r.name.startsWith("departure")) {
                result *= infos.ticket.get(combination.get(idx));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Log.welcome();
        try {
            Log.i(String.format("Ticket scanning error rate : %d", part1(null)));
            Log.i(String.format("Départure multiplication : %d", part2(null)));
            //Log.i(String.format("Answer part2 : %d", part2(null)));
        } catch(Exception exc) {
            Log.w(String.format("Error during execution: %s", exc.getMessage()));
        }
        Log.bye();
    }


}
