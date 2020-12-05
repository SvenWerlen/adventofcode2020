package org.boisdechet.adventofcode2020;

import org.boisdechet.adventofcode2020.utils.InputUtil;
import org.boisdechet.adventofcode2020.utils.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day04 {

    private static final String[] KEYS = { "byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid", "cid" };
    private static final String[] EYE_COLOR = { "amb", "blu", "brn", "gry", "grn", "hzl", "oth" };

    private static boolean isPasswordValid(Set<String> keys) {
        for(String k : KEYS) {
            if(!keys.contains(k) && !k.equals("cid") ) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkRange(int min, int max, int value) {
        return value >= min && value <= max;
    }

    private static boolean isValueValid(String key, String value) {
        Matcher m;
        switch(key) {
            case "byr":
                m = Pattern.compile("^\\d{4}$").matcher(value);
                if(m.matches()) return checkRange(1920,2002, Integer.parseInt(value));
                break;
            case "iyr":
                m = Pattern.compile("^\\d{4}$").matcher(value);
                if(m.matches()) return checkRange(2010,2020, Integer.parseInt(value));
                break;
            case "eyr":
                m = Pattern.compile("^\\d{4}$").matcher(value);
                if(m.matches()) return checkRange(2020,2030, Integer.parseInt(value));
                break;
            case "hgt":
                m = Pattern.compile("^(\\d+)cm$").matcher(value);
                if(m.matches()) return checkRange(150,193, Integer.parseInt(m.group(1)));
                m = Pattern.compile("^(\\d+)in$").matcher(value);
                if(m.matches()) return checkRange(59,76, Integer.parseInt(m.group(1)));
                break;
            case "hcl":
                m = Pattern.compile("^#[a-f0-9]{6}$").matcher(value);
                if(m.matches()) return true;
                break;
            case "ecl":
                return Arrays.asList(EYE_COLOR).contains(value);
            case "pid":
                m = Pattern.compile("^\\d{9}$").matcher(value);
                return m.matches();
            case "cid":
                return true;
        }
        return false;
    }

    private static long getValidPasswordCount(String sample, boolean checkValue) throws IOException {
        BufferedReader input = InputUtil.readInput(4, sample);
        String line = "";

        // read input
        int count = 0;
        Pattern p = Pattern.compile("(\\w{3}):([^ ]+)");
        Set<String> keys = new HashSet<>();
        while ((line = input.readLine()) != null) {
            if(line.length() == 0) {
                if(isPasswordValid(keys)) count++;
                keys.clear();
                continue;
            }

            Matcher m = p.matcher(line);
            while (m.find()) {
                if(!checkValue || isValueValid(m.group(1), m.group(2))) {
                    keys.add(m.group(1));
                }
            }
        }
        if(isPasswordValid(keys)) count++;

        return count;
    }

    /**
     * Part 1
     */
    public static long part1(String sample) throws IOException {
        return getValidPasswordCount(sample, false);
    }

    /**
     * Part 2
     */
    public static long part2(String sample) throws IOException {
        return getValidPasswordCount(sample, true);
    }


    public static void main(String[] args) {
        Log.welcome();
        try {
            Log.i(String.format("Number of valid passports : %d", part1(null)));
            Log.i(String.format("Number of valid passports : %d", part2(null)));
        } catch(Exception exc) {
            Log.w(String.format("Error during execution: %s", exc.getMessage()));
        }
        Log.bye();
    }


}
