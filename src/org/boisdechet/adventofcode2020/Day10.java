package org.boisdechet.adventofcode2020;

import org.boisdechet.adventofcode2020.utils.InputUtil;
import org.boisdechet.adventofcode2020.utils.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class Day10 {

    /**
     * Part 1
     */
    public static long part1(String sample) throws IOException {
        BufferedReader input = InputUtil.readInput(10, sample);
        String line = "";

        List<Integer> values = new ArrayList<>();

        // read input
        while ((line = input.readLine()) != null) {
            values.add( Integer.parseInt(line) );
        }
        Collections.sort(values);

        int cur = 0;
        int jolt1 = 0;
        int jolt2 = 0;
        int jolt3 = 1;
        for(int i=0; i<values.size(); i++) {
            int el = values.get(i);
            int next = i+1<values.size() ? values.get(i+1) : 0;
            if(next - el <= 3 || next == 0) {
                if(el-cur == 1) jolt1++;
                else if(el-cur == 2) jolt2++;
                else if(el-cur == 3) jolt3++;
                else {
                    throw new IllegalStateException("Invalid difference : " + (el-cur));
                }
                cur = el;
            }
        }
        return jolt1 * jolt3;
    }

    private static void addOrIncrement(Map<Integer, Long> map, int cur, int value) {
        long count = map.get(cur);
        if(map.containsKey(value)) {
            map.put(value, map.get(value) + count);
        } else {
            map.put(value, count);
        }
    }

    /**
     * Part 2
     */
    public static long part2(String sample) throws IOException {
        BufferedReader input = InputUtil.readInput(10, sample);
        String line = "";

        Set<Integer> values = new HashSet<>();
        Map<Integer, Long> combinations = new HashMap<>(); // Int + Count
        combinations.put(0, 1L);

        // read input
        int max = 0;
        while ((line = input.readLine()) != null) {
            int value = Integer.parseInt(line);
            if(value > max) max = value;
            values.add( value );
        }

        boolean isEnd = false;
        while(!isEnd) {
            isEnd = true;
            List<Integer> keys = new ArrayList<>(combinations.keySet());
            Collections.sort(keys);
            for(int el : keys) {
                if(el != max) {
                    if (values.contains(el + 1)) addOrIncrement(combinations, el, el + 1);
                    if (values.contains(el + 2)) addOrIncrement(combinations, el, el + 2);
                    if (values.contains(el + 3)) addOrIncrement(combinations, el, el + 3);
                    combinations.remove(el);
                    isEnd = false;
                }
            }
        }

        return combinations.get(max);
    }


    public static void main(String[] args) {
        Log.welcome();
        try {
            Log.i(String.format("1-jolt differences multiplied by the number of 3-jolt differences : %d", part1(null)));
            Log.i(String.format("Total number of distinct ways : %d", part2(null)));
        } catch(Exception exc) {
            Log.w(String.format("Error during execution: %s", exc.getMessage()));
            exc.printStackTrace();
        }
        Log.bye();
    }


}
