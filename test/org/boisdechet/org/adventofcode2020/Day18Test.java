package org.boisdechet.org.adventofcode2020;

import org.boisdechet.adventofcode2020.Day17;
import org.boisdechet.adventofcode2020.Day18;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class Day18Test {

    @Test
    public void examples() throws Exception {
        assertEquals(71, Day18.part1("1 + 2 * 3 + 4 * 5 + 6"));
        assertEquals(51, Day18.part1("1 + (2 * 3) + (4 * (5 + 6))"));
        assertEquals(26, Day18.part1("2 * 3 + (4 * 5)"));
        assertEquals(437, Day18.part1("5 + (8 * 3 + 9 + 3 * 4 * 3)"));
        assertEquals(12240, Day18.part1("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))"));
        assertEquals(13632, Day18.part1("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2"));
        assertEquals(231, Day18.part2("1 + 2 * 3 + 4 * 5 + 6"));
        assertEquals(51, Day18.part2("1 + (2 * 3) + (4 * (5 + 6))"));
        assertEquals(46, Day18.part2("2 * 3 + (4 * 5)"));
        assertEquals(1445, Day18.part2("5 + (8 * 3 + 9 + 3 * 4 * 3)"));
        assertEquals(669060, Day18.part2("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))"));
        assertEquals(23340, Day18.part2("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2"));
        // puzzle answers
        assertEquals(4696493914530L, Day18.part1(null));
        assertEquals(362880372308125L, Day18.part2(null));
    }

}
