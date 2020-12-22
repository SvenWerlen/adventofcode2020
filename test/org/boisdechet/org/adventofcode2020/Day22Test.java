package org.boisdechet.org.adventofcode2020;

import org.boisdechet.adventofcode2020.Day22;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class Day22Test {

    @Test
    public void examples() throws Exception {
        assertEquals(306, Day22.part1("s1"));
        assertEquals(291, Day22.part2("s1"));
        assertEquals(105, Day22.part2("s2"));
        // puzzle answers
        assertEquals(30780, Day22.part1(null));
        assertEquals(36621, Day22.part2(null));
    }

}
