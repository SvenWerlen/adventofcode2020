package org.boisdechet.adventofcode2020;

import org.boisdechet.adventofcode2020.utils.InputUtil;
import org.boisdechet.adventofcode2020.utils.Log;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Day17 {

    private static class Point {
        int x,y,z;

        public Point(int x, int y, int z) {
            this.x = x; this.y = y; this.z = z;
        }

        @Override
        public String toString() {
            return String.format("(%d,%d,%d)", x, y, z);
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Point) {
                Point p = (Point)obj;
                return this.x == p.x && this.y == p.y && this.z == p.z;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return toString().hashCode();
        }
    }

    private static void dumpWorld(Set<Point> space) {
        int minX = 10, maxX = 0, minY = 10, maxY = 0, minZ = 10, maxZ = 0;
        for(Point p : space) {
            minX = Math.min(minX, p.x); maxX = Math.max(maxX, p.x);
            minY = Math.min(minY, p.y); maxY = Math.max(maxY, p.y);
            minZ = Math.min(minZ, p.z); maxZ = Math.max(maxZ, p.z);
        }
        StringBuilder buf = new StringBuilder();
        for(int z = minZ; z<=maxZ; z++) {
            buf.append('\n').append("z=").append(z).append('\n');
            for(int y = maxY; y>=minY; y--) {
                for(int x = minX; x<=maxX; x++) {
                    buf.append(space.contains(new Point(x,y,z)) ? '#' : '.');
                }
                buf.append('\n');
            }
        }
        System.out.println(buf.toString());
    }

    private static int countNearbyActives(Set<Point> space, Point from) {
        int count = 0;
        for(int x=from.x-1; x<=from.x+1; x++) {
            for(int y=from.y-1; y<=from.y+1; y++) {
                for(int z=from.z-1; z<=from.z+1; z++) {
                    Point p = new Point(x,y,z);
                    if(!from.equals(p) && space.contains(p)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    /**
     * Part 1
     */
    public static long part1(String sample) throws IOException {
        String[] rows = InputUtil.readInputAsString(17, sample).trim().split("\n");

        Set<Point> space = new HashSet<>();

        int cycles = 6;
        int minX = cycles;
        int maxX = cycles+rows[0].length();
        int minY = minX;
        int maxY = maxX;
        int minZ = minX;
        int maxZ = maxX;

        for(int y=0; y<rows.length; y++) {
            for(int x=0; x<rows[0].length(); x++) {
                Point p = new Point(cycles + x, cycles + y, cycles);
                if(rows[rows.length-1-y].charAt(x) == '#') {
                    space.add(p);
                }
            }
        }
        //dumpWorld(space);
        for(int c=0; c<cycles; c++) {
            Set<Point> tempSpace = new HashSet<>();
            for(int x=minX-1; x<=maxX+1; x++) {
                for(int y=minY-1; y<=maxY+1; y++) {
                    for(int z=minZ-1; z<=maxZ+1; z++) {
                        Point p = new Point(x,y,z);
                        //System.out.println(p);
                        int actives = countNearbyActives(space, p);
                        if( (space.contains(p) && (actives == 2 || actives == 3)) ||
                            (!space.contains(p) && actives == 3)) {
                            tempSpace.add(p);
                            minX = Math.min(minX, p.x);
                            maxX = Math.max(maxX, p.x);
                            minY = Math.min(minY, p.y);
                            maxY = Math.max(maxY, p.y);
                            minZ = Math.min(minZ, p.z);
                            maxZ = Math.max(maxZ, p.z);

                        }
                    }
                }
            }
            space = tempSpace;
            //System.out.println("==============================================");
            //dumpWorld(space);
        }

        return space.size();
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
            Log.i(String.format("Answer part1 : %d", part1(null)));
            //Log.i(String.format("Answer part2 : %d", part2(null)));
        } catch(Exception exc) {
            Log.w(String.format("Error during execution: %s", exc.getMessage()));
        }
        Log.bye();
    }


}
