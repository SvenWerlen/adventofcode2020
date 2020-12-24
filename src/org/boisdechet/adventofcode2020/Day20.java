package org.boisdechet.adventofcode2020;

import org.boisdechet.adventofcode2020.utils.InputUtil;
import org.boisdechet.adventofcode2020.utils.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day20 {

    private static final int TILE_SIZE = 10;

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
     * Class for representing a match
     */
    private static class Match {
        Tile tile;
        int fromEdge, toEdge;

        public Match(Tile t, int from, int to) {
            this.tile = t;
            this.fromEdge = from;
            this.toEdge = to;
        }
    }

    /**
     * Class for representing a tile
     */
    private static class Tile {

        private static final int TOP = 0;
        private static final int RIGHT = 1;
        private static final int BOTTOM = 2;
        private static final int LEFT = 3;


        int id;
        int size;
        boolean[] pixels;
        List<Match> matches = new ArrayList<>();    // list of matches (tile that has an edge that can match)

        private Tile() {}

        public Tile(int id) { this(id, TILE_SIZE); }

        public Tile(int id, int size) {
            this.id = id;
            this.size = size;
            pixels = new boolean[size*size];
        }

        /**
         * Function which parses 1 line of text and updates the pixes accordingly
         */
        public void parse(int idx, String line) {
            for(int i=0; i<line.length(); i++) {
                if(line.charAt(i) == '#') {
                    pixels[idx*size+i] = true;
                }
            }
        }

        /**
         * Converts a tile into a text representation
         */
        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("Tile ").append(id).append(":\n");
            int idx = 0;
            for(boolean b : pixels) {
                builder.append(b ? '#' : '.');
                if(++idx % size == 0) builder.append('\n');
            }
            builder.append('\n');
            return builder.toString();
        }

        public String rowToString(int row) {
            StringBuilder builder = new StringBuilder();
            for(int i=0; i<size; i++) {
                builder.append(pixels[row*size+i] ? '#' : '.');
            }
            return builder.toString();
        }

        /**
         * Returns a list of pixels for the given edge (always left to right, top to bottom)
         * (0: top, 1:right, 2:bottom, 3:left)
         */
        private boolean[] getEdge(int edgeNo) {
            boolean[] values = new boolean[size];
            if(edgeNo == TOP) {
                for(int i=0; i<size; i++) values[i] = pixels[i];
            } else if(edgeNo == RIGHT) {
                for(int i=0; i<size; i++) values[i] = pixels[(i+1)*size-1];
            } else if(edgeNo == BOTTOM) {
                for(int i=0; i<size; i++) values[i] = pixels[size*(size-1) + i];
            } else {
                for(int i=0; i<size; i++) values[i] = pixels[i*size];
            }
            return values;
        }

        /**
         * Utility to reverse an edge (flip)
         */
        public boolean[] reverse(boolean[] edge) {
            boolean[] result = new boolean[size];
            for(int i=0; i<edge.length; i++) result[i] = edge[size-1-i];
            return result;
        }

        /**
         * Checks if there is a match with the give tile and fill list of matches
         */
        public void generateMatches(Tile tile) {
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

        /**
         * Rotates the tile (90 deg clockwise)
         */
        public void rotate90() {
            boolean[] newPixels = new boolean[size*size];
            for(int x=0; x<size; x++) {
                for(int y=0; y<size; y++) {
                    int xN = size - y - 1;
                    int yN = x;
                    newPixels[yN*size+xN] = pixels[y*size+x];
                }
            }
            pixels = newPixels;
            // adapt matches
            for(Match m : matches) {
                m.fromEdge = (m.fromEdge + 1) % 4;
            }
        }

        /**
         * Flips the tile horizontally
         */
        public void flipH() {
            boolean[] newPixels = new boolean[size*size];
            for(int x=0; x<size; x++) {
                for(int y=0; y<size; y++) {
                    int xN = x;
                    int yN = size - y - 1;
                    newPixels[yN*size+xN] = pixels[y*size+x];
                }
            }
            pixels = newPixels;
            // adapt matches
            for(Match m : matches) {
                if(m.fromEdge % 2 == 0) m.fromEdge = (m.fromEdge + 2) % 4;
            }
        }

        /**
         * Flips the tile vertically
         */
        public void flipV() {
            boolean[] newPixels = new boolean[size*size];
            for(int x=0; x<size; x++) {
                for(int y=0; y<size; y++) {
                    int xN = size - x - 1;
                    int yN = y;
                    newPixels[yN*size+xN] = pixels[y*size+x];
                }
            }
            pixels = newPixels;
            // adapt matches
            for(Match m : matches) {
                if(m.fromEdge % 2 == 1) m.fromEdge = (m.fromEdge + 2) % 4;
            }
        }

        /**
         * Checks if this tile has a match with the provided one
         */
        public boolean matches(Tile tile) {
            for(Match m : matches) {
                if(m.tile == tile) return true;
            }
            return false;
        }

        /**
         * Rotates/flips tile to make it fit with around tiles
         */
        public void makeFit(int top, int right, int bottom, int left) {
            // check all combinations
            int comb = 0;
            while(comb <= 8) {
                boolean match = true;
                for(Match m : matches) {
                    if((m.fromEdge == TOP && ( top <= 0 || m.tile.id != top)) ||
                            (m.fromEdge == RIGHT && ( right <= 0 || m.tile.id != right)) ||
                            (m.fromEdge == BOTTOM && ( bottom <= 0 || m.tile.id != bottom)) ||
                            (m.fromEdge == LEFT && ( left <= 0 || m.tile.id != left))) {
                        match = false;
                        break;
                    }
                }
                if(match) return;
                comb++;
                if(comb == 4) flipH();
                else rotate90();
            }
            throw new IllegalStateException("Couldn't make it fit!");
        }

    }

    /**
     * Displays entire puzzle
     */
    public static void dumpPuzzle(Map<Pos, Tile> puzzle) {
        int size = (int) Math.sqrt(puzzle.size());
        StringBuilder b = new StringBuilder();
        for(int y=0; y<size; y++) {
            for(int r=0; r<TILE_SIZE; r++) {
                for(int x=0; x<size; x++) {
                    b.append(puzzle.get(new Pos(x, y)).rowToString(r)).append(' ');
                }
                b.append('\n');
            }
            b.append('\n');
        }
        System.out.println(b.toString());
    }

    /**
     * Return a large tile representing the puzzle without borders on tile
     */
    public static Tile getPuzzleWithoutTileBorder(Map<Pos, Tile> puzzle) {
        int size = (int) Math.sqrt(puzzle.size());
        StringBuilder b = new StringBuilder();
        for(int y=0; y<size; y++) {
            for(int r=1; r<TILE_SIZE-1; r++) {
                for(int x=0; x<size; x++) {
                    b.append(puzzle.get(new Pos(x, y)).rowToString(r).substring(1,TILE_SIZE-1));
                }
                b.append('\n');
            }
        }
        System.out.println(b.toString());
        // build new tile
        Tile result = new Tile(0, size * (TILE_SIZE-2));
        int line = 0;
        for(String row : b.toString().split("\n")) {
            result.parse(line, row);
            line++;
        }
        return result;
    }

    /**
     * Utility function to read puzzle input and convert it into tiles
     */
    public static List<Tile> readInput(String sample) throws IOException {
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
                curTile.parse(curIdx, line);
                curIdx++;
            }
        }
        for(int i=0; i<tiles.size(); i++) {
            for(int j=i+1; j<tiles.size(); j++) {
                tiles.get(i).generateMatches(tiles.get(j));
            }
        }
        return tiles;
    }

    /**
     * Part 1
     */
    public static long part1(String sample) throws IOException {
        List<Tile> tiles = readInput(sample);
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
        List<Tile> tiles = readInput(sample);

        // filter by type
        Queue<Tile> corners = new LinkedList<>();
        Queue<Tile> edges = new LinkedList<>();
        Queue<Tile> inside = new LinkedList<>();

        for (Tile t : tiles) {
            if (t.matches.size() == 2) corners.add(t);
            else if (t.matches.size() == 3) edges.add(t);
            else if (t.matches.size() == 4) inside.add(t);
            else throw new IllegalStateException("Algorithm won't work!");
        }
        int edgeCount = (int) Math.sqrt(tiles.size()) - 2; // size of an edge
        // check prerequisites
        if (corners.size() != 4 || edges.size() != 4 * edgeCount)
            throw new IllegalStateException("Tiles don't fit for algorithm to work");

        // build order
        Queue<Pos> pos = new LinkedList<>();
        for (int i = 0; i < edgeCount + 2; i++) pos.add(new Pos(i, 0));                    // top
        for (int i = 0; i < edgeCount + 1; i++) pos.add(new Pos(edgeCount + 1, i + 1)); // right
        for (int i = edgeCount; i >= 0; i--) pos.add(new Pos(i, edgeCount + 1));           // bottom
        for (int i = edgeCount; i > 0; i--) pos.add(new Pos(0, i));                        // left

        Map<Pos, Tile> puzzle = new HashMap<>();
        Tile cur = corners.poll();
        puzzle.put(pos.poll(), cur);
        int idx = 0;
        // start from upper-left corner and then try to do the edges (clockwise)
        while (edges.size() > 0) {
            Queue<Tile> poss = idx == edgeCount ? corners : edges;
            Tile found = null;
            for (Match m : cur.matches) {
                if (poss.contains(m.tile)) {
                    found = m.tile;
                    break;
                }
            }
            if (found == null) throw new IllegalStateException("Next tile not found!!");
            puzzle.put(pos.poll(), found);
            poss.remove(found);
            cur = found;
            idx = idx == edgeCount ? 0 : idx + 1;
        }

        //for(Pos p : puzzle.keySet()) System.out.println(p);

        // now proceed with inside of the puzzle (left to right, up to down)
        for (int x = 1; x <= edgeCount; x++) {
            for (int y = 1; y <= edgeCount; y++) {
                Tile left = puzzle.get(new Pos(x - 1, y));
                Tile top = puzzle.get(new Pos(x, y - 1));
                if (left == null || top == null)
                    throw new IllegalStateException(String.format("Something got wrong! (%d,%d)", x, y));
                Tile found = null;
                for (Tile t : inside) {
                    if (left.matches(t) && top.matches(t)) {
                        found = t;
                        break;
                    }
                }
                if (found == null) throw new IllegalStateException("Next inside tile not found!!");
                puzzle.put(new Pos(x, y), found);
                inside.remove(found);
            }
        }

        // finally flip/rotate to fit tile in place
        for (int x = 0; x <= edgeCount + 1; x++) {
            for (int y = 0; y <= edgeCount + 1; y++) {
                Tile curr = puzzle.get(new Pos(x, y));
                Tile top = puzzle.get( new Pos(x,y-1) );
                Tile right = puzzle.get( new Pos(x+1,y) );
                Tile bottom = puzzle.get( new Pos(x,y+1) );
                Tile left = puzzle.get( new Pos(x-1,y) );
                curr.makeFit(
                        top != null ? top.id : 0,
                        right != null ? right.id : 0,
                        bottom != null ? bottom.id : 0,
                        left != null ? left.id : 0);
                //System.out.println(String.format("(%s,%s) %s", x, y, curr));
            }
        }

        dumpPuzzle(puzzle);
        Tile result = getPuzzleWithoutTileBorder(puzzle);
        System.out.println(result);

        return 0L;
    }

    public static void main(String[] args) {
        Log.welcome();
        try {
            //Log.i(String.format("IDs of the four corner tiles multiplied : %d", part1(null)));
            Log.i(String.format("Answer part2 : %d", part2(null)));
        } catch(Exception exc) {
            Log.w(String.format("Error during execution: %s", exc.getMessage()));
        }
        Log.bye();
    }


}
