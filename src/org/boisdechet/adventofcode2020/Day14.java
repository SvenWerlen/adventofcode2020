package org.boisdechet.adventofcode2020;

import org.boisdechet.adventofcode2020.utils.InputUtil;
import org.boisdechet.adventofcode2020.utils.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day14 {

    private static final String MASK = "mask = ";

    private static final long applyMask(String mask, long value) {
        // convert to binary representation
        String valueBinary = Long.toBinaryString(value);
        // pad fill with 0s
        StringBuilder valueBuilder = new StringBuilder(String.format("%1$" + mask.length() + "s", valueBinary).replace(' ', '0'));
        // apply mask
        for(int idx=mask.length()-1; idx>=0; idx--) {
            if(mask.charAt(idx) == '0' || mask.charAt(idx) == '1') {
                valueBuilder.setCharAt(idx, mask.charAt(idx));
            }
        }
        return Long.parseLong(valueBuilder.toString(), 2);
    }

    /**
     * Part 1
     */
    public static long part1(String sample) throws IOException {
        BufferedReader input = InputUtil.readInput(14, sample);
        String line = "";

        Map<Long, Long> memory = new HashMap<>();
        Pattern p = Pattern.compile("mem\\[(\\d+)] = (\\d+)");
        String mask = null;
        // put all number in set
        while ((line = input.readLine()) != null) {
            if(line.startsWith(MASK)) {
                mask = line.substring(MASK.length());
            } else {
                Matcher m = p.matcher(line);
                if(!m.find()) throw new IllegalStateException("Couldn't parse line : " + line);
                if(mask == null) throw new IllegalStateException("No mask found!");
                long memorySlot = Long.parseLong(m.group(1));
                long value = Long.parseLong(m.group(2));
                value = applyMask(mask, value);
                memory.put(memorySlot, value);
            }
        }
        long total = 0;
        for(long value : memory.values()) {
            total += value;
        }
        return total;
    }

    private static final String applyMaskV2(String mask, long address) {
        // convert to binary representation
        String addressBinary = Long.toBinaryString(address);
        // pad fill with 0s
        StringBuilder valueBuilder = new StringBuilder(String.format("%1$" + mask.length() + "s", addressBinary).replace(' ', '0'));
        // apply mask
        for(int idx=mask.length()-1; idx>=0; idx--) {
            if(mask.charAt(idx) == 'X' || mask.charAt(idx) == '1') {
                valueBuilder.setCharAt(idx, mask.charAt(idx));
            }
        }
        return valueBuilder.toString();
    }

    private static final void writeToMemory(Map<Long, Long> memory, String memorySlot, long value) {
        int idx = memorySlot.indexOf('X');
        if(idx >= 0) {
            writeToMemory(memory, memorySlot.substring(0,idx) + '0' + memorySlot.substring(idx+1), value);
            writeToMemory(memory, memorySlot.substring(0,idx) + '1' + memorySlot.substring(idx+1), value);
        } else {
            memory.put(Long.parseLong(memorySlot, 2), value);
        }
    }

    /**
     * Part 2
     */
    public static long part2(String sample) throws IOException {
        BufferedReader input = InputUtil.readInput(14, sample);
        String line = "";

        Map<Long, Long> memory = new HashMap<>();
        Pattern p = Pattern.compile("mem\\[(\\d+)] = (\\d+)");
        String mask = null;
        // put all number in set
        while ((line = input.readLine()) != null) {
            if(line.startsWith(MASK)) {
                mask = line.substring(MASK.length());
            } else {
                Matcher m = p.matcher(line);
                if(!m.find()) throw new IllegalStateException("Couldn't parse line : " + line);
                if(mask == null) throw new IllegalStateException("No mask found!");
                String memorySlot = applyMaskV2(mask, Long.parseLong(m.group(1)));
                long value = Long.parseLong(m.group(2));
                writeToMemory(memory, memorySlot, value);
            }
        }
        long total = 0;
        for(long value : memory.values()) {
            total += value;
        }
        return total;
    }

    public static void main(String[] args) {
        Log.welcome();
        try {
            Log.i(String.format("Sum of all values left : %d", part1(null)));
            Log.i(String.format("Sum of all values left (v2) : %d", part2(null)));
        } catch(Exception exc) {
            Log.w(String.format("Error during execution: %s", exc.getMessage()));
            exc.printStackTrace();
        }
        Log.bye();
    }


}
