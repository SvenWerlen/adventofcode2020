package org.boisdechet.adventofcode2020;

import org.boisdechet.adventofcode2020.utils.InputUtil;
import org.boisdechet.adventofcode2020.utils.Log;

import java.io.BufferedReader;
import java.io.IOException;

public class Day12 {

    private static final class Ship {
        double direction = 0;
        long east = 0;
        long north = 0;

        @Override
        public String toString() {
            return String.format("(%d, %d) -> %.0f", east, north, direction);
        }
    }

    private static double getAngleInDegrees(double east, double north) {
        return Math.tan(north/east) * 180 / Math.PI;
    }

    /**
     * Part 1
     */
    public static long part1(String sample) throws IOException {
        BufferedReader input = InputUtil.readInput(12, sample);
        String line = "";

        // put all number in set
        Ship ship = new Ship();
        while ((line = input.readLine()) != null) {
            if(line.length() < 2) {
                throw new IllegalStateException("Invalid input : " + line);
            }
            char action = line.charAt(0);
            int dist = Integer.parseInt(line.substring(1));
            if( action == 'N' ) ship.north += dist;
            else if( action == 'S' ) ship.north -= dist;
            else if( action == 'E' ) ship.east += dist;
            else if( action == 'W' ) ship.east -= dist;
            else if( action == 'L' ) ship.direction = (ship.direction + dist) % 360;
            else if( action == 'R' ) ship.direction = (ship.direction + 360 - dist) % 360;
            else if( action == 'F' ) {
                double angleRad = ship.direction * Math.PI / 180;
                ship.east += Math.round(Math.cos(angleRad) * dist);
                ship.north += Math.round(Math.sin(angleRad) * dist);
            } else {
                throw new IllegalStateException("Invalid action : " + action);
            }

            //System.out.println(String.format("%s : %s", line, ship));
        }
        return Math.round(Math.abs(ship.east) + Math.abs(ship.north));
    }

    // https://www.stashofcode.fr/rotation-dun-point-autour-dun-centre/
    public static long[] rotate(long east, long north, long angle) {
        double angleRad = angle * Math.PI / 180;
        long x = Math.round(east * Math.cos (angleRad) + north * Math.sin (angleRad));
        long y = Math.round(-east * Math.sin (angleRad) + north * Math.cos (angleRad));
        return new long[] {x, y};
    }

    /**
     * Part 2
     */
    public static long part2(String sample) throws IOException {
        BufferedReader input = InputUtil.readInput(12, sample);
        String line = "";

        // put all number in set
        Ship ship = new Ship();
        long wpEast = 10;
        long wpNorth = 1;

        while ((line = input.readLine()) != null) {
            if(line.length() < 2) {
                throw new IllegalStateException("Invalid input : " + line);
            }
            char action = line.charAt(0);
            int dist = Integer.parseInt(line.substring(1));
            if( action == 'N' ) wpNorth += dist;
            else if( action == 'S' ) wpNorth -= dist;
            else if( action == 'E' ) wpEast += dist;
            else if( action == 'W' ) wpEast -= dist;
            else if( action == 'L' || action == 'R' ) {
                long[] newPos = rotate(wpEast, wpNorth, action == 'L' ? -dist : dist);
                wpEast = newPos[0];
                wpNorth = newPos[1];
            }
            else if( action == 'F' ) {
                ship.east += wpEast * dist;
                ship.north += wpNorth * dist;
            } else {
                throw new IllegalStateException("Invalid action : " + action);
            }

            //System.out.println(String.format("%s : %s (%d, %d)", line, ship, wpEast, wpNorth));
        }
        return Math.round(Math.abs(ship.east) + Math.abs(ship.north));
    }

    public static void main(String[] args) {
        Log.welcome();
        try {
            Log.i(String.format("Manhattan distance : %d", part1(null)));
            Log.i(String.format("Manhattan distance : %d", part2(null)));
        } catch(Exception exc) {
            Log.w(String.format("Error during execution: %s", exc.getMessage()));
        }
        Log.bye();
    }


}
