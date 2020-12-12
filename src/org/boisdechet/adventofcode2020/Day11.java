package org.boisdechet.adventofcode2020;

import org.boisdechet.adventofcode2020.utils.InputUtil;
import org.boisdechet.adventofcode2020.utils.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class Day11 {

    private static final short FLOOR = 0;
    private static final short OCCUPIED = 1;
    private static final short EMPTY = 2;

    private static final void dumpGrid(short[][] grid) {
        StringBuilder builder = new StringBuilder();
        for(int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                builder.append(grid[y][x] == OCCUPIED ? '#' : grid[y][x] == EMPTY ? 'L' : '.');
            }
            builder.append('\n');
        }
        System.out.println(builder.toString());
    }

    private static boolean isOccupied(short[][] grid, int x, int y) {
        return x >= 0 && x < grid[0].length && y >= 0 && y < grid.length && grid[y][x] == OCCUPIED;
    }

    private static int occupiedSeats(short[][] grid, int x, int y) {
        int count = 0;
        if(isOccupied(grid, x-1, y-1)) count++;
        if(isOccupied(grid, x, y-1)) count++;
        if(isOccupied(grid, x+1, y-1)) count++;
        if(isOccupied(grid, x-1, y)) count++;
        if(isOccupied(grid, x+1, y)) count++;
        if(isOccupied(grid, x-1, y+1)) count++;
        if(isOccupied(grid, x, y+1)) count++;
        if(isOccupied(grid, x+1, y+1)) count++;
        return count;
    }

    private static void increment(short[][] grid, int x, int y) {
        if( x >= 0 && x < grid[0].length && y >= 0 && y < grid.length ) {
            grid[y][x]++;
        }
    }

    /**
     * Fills count array with the number of occupied seats adjacent
     * @param grid original states
     * @param count number of occupied seats adjacent in all 8 directions
     */
    private static void fillOccupiedGrid(short[][] grid, short[][] count) {
        for(int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                if(grid[y][x] == OCCUPIED) {
                    increment(count, x-1, y-1);
                    increment(count, x, y-1);
                    increment(count, x, y+1);
                    increment(count, x-1, y);
                    increment(count, x+1, y);
                    increment(count, x-1, y+1);
                    increment(count, x, y+1);
                    increment(count, x+1, y+1);
                }
            }
        }
    }

    /**
     * Part 1
     */
    public static long part1(String sample) throws IOException {
        String input = InputUtil.readInputAsString(11, sample).trim();
        String[] lines = input.split("\n");
        short[][] grid = new short[lines.length][lines[0].length()];
        for(int y = 0; y < lines.length; y++) {
            for(int x = 0; x < lines[0].length(); x++) {
                switch(lines[y].charAt(x)) {
                    case '#': grid[y][x] = OCCUPIED; break;
                    case 'L': grid[y][x] = EMPTY; break;
                    case '.': grid[y][x] = FLOOR; break;
                    default: throw new IllegalStateException("Invalid character : " + lines[y].charAt(x));
                }
            }
        }
        boolean changed = true;
        while(changed) {
            changed = false;
            short[][] newGrid = new short[grid.length][grid[0].length];
            for(int y = 0; y < grid.length; y++) {
                for (int x = 0; x < grid[0].length; x++) {
                    if(grid[y][x] == EMPTY && occupiedSeats(grid, x, y) == 0) {
                        newGrid[y][x] = OCCUPIED;
                        changed = true;
                    } else if(grid[y][x] == OCCUPIED && occupiedSeats(grid, x, y) >= 4) {
                        newGrid[y][x] = EMPTY;
                        changed = true;
                    } else {
                        newGrid[y][x] = grid[y][x];
                    }
                }
            }
            grid = newGrid;
        }
        //dumpGrid(grid);

        int count = 0;
        for(int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                if (grid[y][x] == OCCUPIED) count++;
            }
        }
        return count;
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
            Log.i(String.format("Seats ended up occupied : %d", part1(null)));
            //Log.i(String.format("Total number of distinct ways : %d", part2(null)));
        } catch(Exception exc) {
            Log.w(String.format("Error during execution: %s", exc.getMessage()));
            exc.printStackTrace();
        }
        Log.bye();
    }


}
