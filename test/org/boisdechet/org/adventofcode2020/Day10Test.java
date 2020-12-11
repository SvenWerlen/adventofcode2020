package org.boisdechet.org.adventofcode2020;

import org.boisdechet.adventofcode2020.Day10;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class Day10Test {

    @Test
    public void examples() throws Exception {
        assertEquals(35, Day10.part1("s1"));
        assertEquals(220, Day10.part1("s2"));
        assertEquals(8, Day10.part2("s1"));
        assertEquals(19208, Day10.part2("s2"));
        // puzzle answers
        assertEquals(2030, Day10.part1(null));
        assertEquals(42313823813632L, Day10.part2(null));
    }

}
