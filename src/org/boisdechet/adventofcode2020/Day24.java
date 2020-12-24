package org.boisdechet.adventofcode2020;

import org.boisdechet.adventofcode2020.utils.InputUtil;
import org.boisdechet.adventofcode2020.utils.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Day24 {

    /**
     * Class for representing a position in the puzzle (0,0) is upper left corner
     */
    private static class Pos {
        int x, y;
        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return String.format("(%d,%d)", x, y);
        }

        @Override
        public int hashCode() {
            return Objects.hash(x,y);
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Pos) {
                Pos p = (Pos)obj;
                return x == p.x && y == p.y;
            }
            return false;
        }
    }

    /**
     * Class for parsing and working with directions
     */
    private static class Path {

        private final static int SE = 0;
        private final static int SW = 1;
        private final static int NW = 2;
        private final static int NE = 3;
        private final static int E = 4;
        private final static int W = 5;

        private final static String[] DIRS = new String[] { "se", "sw", "nw", "ne", "e", "w" };
        public final static int[][] ADJ = new int[][] { {0,1}, {0,-1}, {-1,0}, {-1,1}, {1,0}, {1,-1} };
        private int x, y;

        public Path(String instructions) {
            this.x = 0;
            this.y = 0;
            while(instructions.length() != 0) {
                boolean found = false;
                for(int i=0; i<6; i++) {
                    if(instructions.startsWith(DIRS[i])) {
                        updatePosition(i);
                        instructions = instructions.substring(DIRS[i].length());
                        found = true;
                        break;
                    }
                }
                if(!found) throw new IllegalStateException("Invalid instruction : " + instructions);
            }
        }

        private void updatePosition(int pos) {
            if(pos == SE) { y++; }
            else if(pos == SW) { x--; y++; }
            else if(pos == NW) { y--; }
            else if(pos == NE) { x++; y--; }
            else if(pos == E) { x++; }
            else if(pos == W) { x--; }
            else throw new IllegalStateException("Invalid position : " + pos);
        }

        public Pos getPosition() { return new Pos(x,y); }
    }

    /**
     * Part 1
     */
    public static long part1(String sample) throws IOException {
        BufferedReader input = InputUtil.readInput(24, sample);
        String line = "";
        Set<Pos> flipped = new HashSet<>();
        while ((line = input.readLine()) != null) {
            Pos p = (new Path(line.strip())).getPosition();
            if(flipped.contains(p)) {
                flipped.remove(p);
            } else {
                flipped.add(p);
            }
        }
        return flipped.size();
    }

    /**
     * Part 2
     */
    public static long part2(String sample) throws IOException {
        BufferedReader input = InputUtil.readInput(24, sample);
        String line = "";
        Set<Pos> flipped = new HashSet<>();
        while ((line = input.readLine()) != null) {
            Pos p = (new Path(line.strip())).getPosition();
            if(flipped.contains(p)) {
                flipped.remove(p);
            } else {
                flipped.add(p);
            }
        }

        for(int day = 1; day<=100; day++) {
            Set<Pos> flippedNew = new HashSet<>();  // new position flipped
            Set<Pos> whiteAdj = new HashSet<>();    // white tiles that must be checked
            for(Pos p : flipped) {
                int count = 0;
                for (int adj = 0; adj < Path.ADJ.length; adj++) {
                    Pos checkPos = new Pos(p.x + Path.ADJ[adj][0], p.y + Path.ADJ[adj][1]);
                    if (flipped.contains(checkPos)) {
                        count++;
                    } else {
                        whiteAdj.add(checkPos);
                    }
                }
                // Any black tile with zero or more than 2 black tiles immediately adjacent to it is flipped to white.
                // i.e. any blaock tile remains black if it has 1 or 2 black tiles adjacent
                if(count == 1 || count == 2) {
                    flippedNew.add(p);
                }
            }
            // Check whites adjacent to black tiles
            for(Pos w : whiteAdj) {
                int count = 0;
                for (int adj = 0; adj < Path.ADJ.length; adj++) {
                    if (flipped.contains(new Pos(w.x + Path.ADJ[adj][0], w.y + Path.ADJ[adj][1]))) {
                        count++;
                    }
                }
                // Any white tile with exactly 2 black tiles immediately adjacent to it is flipped to black.
                if(count == 2) {
                    flippedNew.add(w);
                }
            }
            flipped = flippedNew;
            //System.out.println(String.format("Day %d: %d", day, flipped.size()));
        }
        return flipped.size();
    }

    public static void main(String[] args) {
        Log.welcome();
        try {
            Log.i(String.format("Tiles are left with the black side up : %d", part1(null)));
            Log.i(String.format("Tiles black after 100 days : %d", part2(null)));
        } catch(Exception exc) {
            Log.w(String.format("Error during execution: %s", exc.getMessage()));
        }
        Log.bye();
    }


}
