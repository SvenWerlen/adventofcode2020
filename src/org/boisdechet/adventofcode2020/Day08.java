package org.boisdechet.adventofcode2020;

import org.boisdechet.adventofcode2020.handshell.HandshellMachine;
import org.boisdechet.adventofcode2020.handshell.Instruction;
import org.boisdechet.adventofcode2020.utils.InputUtil;
import org.boisdechet.adventofcode2020.utils.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day08 {

    private static class Result {
        long value;
        boolean isLast;
    }

    private static Result getAccuValue(List<Instruction> instructions) {
        HandshellMachine machine = new HandshellMachine(instructions);
        Set<Integer> visited = new HashSet<>();
        boolean isLast = false;
        while(!isLast && !visited.contains(machine.getCur())) {
            //System.out.println(machine);
            //System.out.println(machine.getInstruction());
            visited.add(machine.getCur());
            isLast = machine.isLastInstruction();
            machine.next();
        }
        Result result = new Result();
        result.value = machine.getAccuValue();
        result.isLast = isLast;
        return result;
    }

    /**
     * Part 1
     */
    public static long part1(String sample) throws IOException {
        BufferedReader input = InputUtil.readInput(8, sample);
        String line = "";
        // read input
        List<Instruction> instructions = new ArrayList<>();
        while ((line = input.readLine()) != null) {
            instructions.add(new Instruction(line));
        }
        return getAccuValue(instructions).value;
    }

    /**
     * Part 2
     */
    public static long part2(String sample) throws IOException {
        BufferedReader input = InputUtil.readInput(8, sample);
        String line = "";
        // read input
        List<Instruction> instructions = new ArrayList<>();
        while ((line = input.readLine()) != null) {
            instructions.add(new Instruction(line));
        }
        for(int i=0; i<instructions.size(); i++) {
            if(instructions.get(i).fixCorrupted()) {
                Result res = getAccuValue(instructions);
                if(res.isLast) {
                    return res.value;
                }
                instructions.get(i).fixCorrupted(); // revert back
            }
        }
        throw new IllegalStateException("Solution not found!");
    }


    public static void main(String[] args) {
        Log.welcome();
        try {
            Log.i(String.format("Value of the accumulator : %d", part1(null)));
            Log.i(String.format("Value of the accumulator after the program terminates : %d", part2(null)));
        } catch(Exception exc) {
            Log.w(String.format("Error during execution: %s", exc.getMessage()));
            exc.printStackTrace();
        }
        Log.bye();
    }


}
