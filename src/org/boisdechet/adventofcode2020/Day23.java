package org.boisdechet.adventofcode2020;

import org.boisdechet.adventofcode2020.utils.InputUtil;
import org.boisdechet.adventofcode2020.utils.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Day23 {

    private static class Cup {
        int value;
        Cup next;

        public Cup(int value) {
            this.value = value;
        }
    }

    public static String dumpCups(Cup first, boolean nice) {
        StringBuilder builder = new StringBuilder();
        Cup cur = first;
        int displayed = 0;
        do {
            if(nice){
                builder.append(cur == first ? "(" + cur.value + ")" : cur.value).append(' ');
            } else {
                builder.append(cur.value);
            }
            cur = cur.next;
            displayed++;
        } while(cur != first && displayed <= 20);
        return builder.toString();
    }

    private static void solve(Map<Integer, Cup> map, Cup first, int maxVal, int loops) {
        int iter = 0;
        Cup curr = first;

        int destVal = 0;
        while(iter < loops) {
            // remove 3 cups
            Cup first3 = curr.next;
            curr.next = curr.next.next.next.next;
            // compute destination value
            destVal = curr.value;
            do {
                destVal = destVal == 1 ? destVal = maxVal : destVal - 1;
            } while (destVal == first3.value || destVal == first3.next.value || destVal == first3.next.next.value);
            // move 3 cups
            Cup dest = map.get(destVal);
            Cup destNext = dest.next;
            dest.next = first3;
            first3.next.next.next = destNext;
            // next current cup
            curr = curr.next;
            iter++;
        }
    }

    /**
     * Part 1
     */
    public static String part1(String sample) throws IOException {
        List<Integer> list = sample.chars()
                .map(c -> c - (int) '0')
                .boxed().collect(Collectors.toList());

        Map<Integer, Cup> map = new HashMap<>();
        Cup last = null;
        Cup first = null;
        for(int i : list) {
            Cup c = new Cup(i);
            map.put(i, c);          // keep cup in map for fast search
            if(last != null) {
                last.next = c;      // link with next
            } else {
                first = c;          // keep first as reference
            }
            last = c;               // keep reference
        }
        last.next = first;

        solve(map, first, 9, 100);

        return dumpCups(map.get(1),false).substring(1);
    }

    /**
     * Part 2
     */
    public static long part2(String sample) throws IOException {
        List<Integer> list = sample.chars()
                .map(c -> c - (int) '0')
                .boxed().collect(Collectors.toList());

        Map<Integer, Cup> map = new HashMap<>();
        Cup last = null;
        Cup first = null;
        for(int i : list) {
            Cup c = new Cup(i);
            map.put(i, c);          // keep cup in map for fast search
            if(last != null) {
                last.next = c;      // link with next
            } else {
                first = c;          // keep first as reference
            }
            last = c;               // keep reference
        }
        // insert values until 1000000 is reached
        for(int i=10; i<=1000000; i++) {
            Cup c = new Cup(i);
            map.put(i, c);
            last.next = c;
            last = c;
        }
        last.next = first;

        solve(map, first, 1000000, 10000000);
        long val1 = map.get(1).next.value;
        long val2 = map.get(1).next.next.value;
        return val1 * val2;
    }

    public static void main(String[] args) {
        Log.welcome();
        try {
            Log.i(String.format("Labels on the cups : %s", part1("523764819")));
            Log.i(String.format("Labels multiplied : %d", part2("523764819")));
        } catch(Exception exc) {
            Log.w(String.format("Error during execution: %s", exc.getMessage()));
        }
        Log.bye();
    }


}
