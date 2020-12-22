package org.boisdechet.adventofcode2020;

import org.boisdechet.adventofcode2020.utils.InputUtil;
import org.boisdechet.adventofcode2020.utils.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day20 {

    public final static int SIZE = 10;

    private static class Match {
        Tile tile;
        int fromEdge, toEdge;

        public Match(Tile t, int from, int to) {
            this.tile = t;
            this.fromEdge = from;
            this.toEdge = to;
        }
    }

    private static class Tile {
        int id;
        boolean[] pixels = new boolean[100];
        List<Match> matches = new ArrayList<>();

        public Tile(int id) {
            this.id = id;
        }

        public void scan(int idx, String line) {
            for(int i=0; i<line.length(); i++) {
                if(line.charAt(i) == '#') {
                    pixels[idx*SIZE+i] = true;
                }
            }
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("Tile ").append(id).append(":\n");
            int idx = 0;
            for(boolean b : pixels) {
                builder.append(b ? '#' : '.');
                if(++idx % SIZE == 0) builder.append('\n');
            }
            builder.append('\n');
            return builder.toString();
        }

        private boolean[] getEdge(int edgeNo) {
            boolean[] values = new boolean[SIZE];
            if(edgeNo == 0) {
                for(int i=0; i<SIZE; i++) values[i] = pixels[i];
            } else if(edgeNo == 1) {
                for(int i=0; i<SIZE; i++) values[i] = pixels[(i+1)*SIZE-1];
            } else if(edgeNo == 2) {
                for(int i=0; i<SIZE; i++) values[i] = pixels[SIZE*(SIZE-1) + i];
            } else {
                for(int i=0; i<SIZE; i++) values[i] = pixels[i*SIZE];
            }
            return values;
        }

        public static boolean[] reverse(boolean[] edge) {
            boolean[] result = new boolean[SIZE];
            for(int i=0; i<edge.length; i++) result[i] = edge[SIZE-1-i];
            return result;
        }

        public void match(Tile tile) {
            for(int i=0; i<4; i++) {
                for(int j=0; j<4; j++) {
                    boolean[] edge1 = getEdge(i);
                    boolean[] edge2 = tile.getEdge(j);
                    if(Arrays.equals(edge1, edge2) || Arrays.equals(reverse(edge1), edge2)) {
                        matches.add(new Match(tile, i, j));
                        tile.matches.add(new Match(this, j, i));
                        //System.out.println(String.format("%s matches %s on (%d,%d)", id, tile.id, i, j));
                    }
                }
            }
        }
    }

    /**
     * Part 1
     */
    public static long part1(String sample) throws IOException {
        BufferedReader input = InputUtil.readInput(20, sample);
        String line = "";

        List<Tile> tiles = new ArrayList<>();
        Tile curTile = null;
        int curIdx = 0;
        Pattern p = Pattern.compile("Tile (\\d+):");
        while ((line = input.readLine()) != null) {
            Matcher m = p.matcher(line);
            if(m.matches()) {
                curTile = new Tile(Integer.parseInt(m.group(1)));
                tiles.add(curTile);
                curIdx = 0;
            } else if(line.length() > 0) {
                curTile.scan(curIdx, line);
                curIdx++;
            }
        }
        for(int i=0; i<tiles.size(); i++) {
            for(int j=i+1; j<tiles.size(); j++) {
                tiles.get(i).match(tiles.get(j));
            }
        }

        // assuming that corners have only 2 matches
        long result = 1;
        for(Tile t : tiles) {
            //System.out.println(String.format("%d has %d matches", t.id, t.matches.size()));
            if(t.matches.size() == 2) {
                result *= t.id;
            }
        }
        return result;
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
            Log.i(String.format("IDs of the four corner tiles multiplied : %d", part1(null)));
            //Log.i(String.format("Answer part2 : %d", part2(null)));
        } catch(Exception exc) {
            Log.w(String.format("Error during execution: %s", exc.getMessage()));
        }
        Log.bye();
    }


}
