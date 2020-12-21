package org.boisdechet.org.adventofcode2020;

import org.boisdechet.adventofcode2020.Day15;
import org.boisdechet.adventofcode2020.Day16;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class Day16Test {

    @Test
    public void examples() throws Exception {
        assertEquals(71, Day16.part1("s1"));
        // puzzle answers
        assertEquals(19093, Day16.part1(null));
        assertEquals(5311123569883L, Day16.part2(null));
    }

}
