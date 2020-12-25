package org.boisdechet.adventofcode2020;

import org.boisdechet.adventofcode2020.utils.InputUtil;
import org.boisdechet.adventofcode2020.utils.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Day25 {

    /**
     * Returns the handShake if loop is > 0
     * Returns the loop if public keys are specified
     */
    private static long handShake(long subjectNumber, long loop, long publicKey1, long publicKey2) {
        long pubKey = 1;
        long iter = 1;
        while(true) {
            pubKey *= subjectNumber;
            pubKey %= 20201227;
            //System.out.println(String.format("%d == %d or %d", pubKey, publicKey1, publicKey2));
            if(loop > 0) {
                if(iter == loop) return pubKey;
            }
            else if(pubKey == publicKey1) return iter;
            else if(pubKey == publicKey2) return -iter;
            iter++;
        }
    }

    /**
     * Part 1
     */
    public static long part1(long pubKey1, long pubKey2) throws IOException {
        long loop = handShake(7, 0, pubKey1, pubKey2);
        if(loop >= 0) {
            return handShake(pubKey2, loop, 0, 0);
        } else {
            return handShake(pubKey1, -loop, 0, 0);
        }
    }

    /**
     * Part 2
     */
    public static long part2(String sample) throws IOException {
        return 0L;
    }

    public static void main(String[] args) {
        Log.welcome();
        try {
            Log.i(String.format("Encryption key : %d", part1(9717666,20089533)));
        } catch(Exception exc) {
            Log.w(String.format("Error during execution: %s", exc.getMessage()));
        }
        Log.bye();
    }


}
