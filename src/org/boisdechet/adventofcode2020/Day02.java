package org.boisdechet.adventofcode2020;

import org.boisdechet.adventofcode2020.utils.InputUtil;
import org.boisdechet.adventofcode2020.utils.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day02 {

    /**
     * Part 1
     */
    public static long part1(String sample) throws IOException {
        BufferedReader input = InputUtil.readInput(2, sample);
        String line = "";

        int passwordValidCount = 0;
        Pattern p = Pattern.compile("(\\d+)-(\\d+) (\\w): (\\w+)");
        while ((line = input.readLine()) != null) {
            Matcher m = p.matcher(line);
            if(!m.find()) {
                throw new IllegalStateException("Couldn't parse line : " + line);
            }

            int min = Integer.parseInt(m.group(1));
            int max = Integer.parseInt(m.group(2));
            char c = m.group(3).charAt(0);
            String password = m.group(4);

            // check password
            int count = 0;
            for(char cc : password.toCharArray()) {
                if(cc == c) count++;
            }

            if(count >= min && count <= max) {
                passwordValidCount++;
            }
        }
        return passwordValidCount;
    }

    /**
     * Part 1
     */
    public static long part2(String sample) throws IOException {
        BufferedReader input = InputUtil.readInput(2, sample);
        String line = "";

        int passwordValidCount = 0;
        Pattern p = Pattern.compile("(\\d+)-(\\d+) (\\w): (\\w+)");
        while ((line = input.readLine()) != null) {
            Matcher m = p.matcher(line);
            if(!m.find()) {
                throw new IllegalStateException("Couldn't parse line : " + line);
            }

            int pos1 = Integer.parseInt(m.group(1));
            int pos2 = Integer.parseInt(m.group(2));
            char c = m.group(3).charAt(0);
            String password = m.group(4);

            // check password
            int count = password.length() >= pos1 && password.charAt(pos1-1) == c ? 1 : 0;
            count += password.length() >= pos2 && password.charAt(pos2-1) == c ? 1 : 0;

            if( count == 1) {
                passwordValidCount++;
            }
        }
        return passwordValidCount;
    }


    public static void main(String[] args) {
        Log.welcome();
        try {
            Log.i(String.format("Number of valid passwords : %d", part1(null)));
            Log.i(String.format("Number of valid passwords : %d", part2(null)));
        } catch(Exception exc) {
            Log.w(String.format("Error during execution: %s", exc.getMessage()));
        }
        Log.bye();
    }


}
