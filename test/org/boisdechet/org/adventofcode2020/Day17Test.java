package org.boisdechet.org.adventofcode2020;

import org.boisdechet.adventofcode2020.Day16;
import org.boisdechet.adventofcode2020.Day17;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class Day17Test {

    @Test
    public void examples() throws Exception {
        assertEquals(112, Day17.part1("s1"));
        assertEquals(848, Day17.part2("s1"));
        // puzzle answers
        assertEquals(273, Day17.part1(null));
        assertEquals(1504, Day17.part2(null));
    }

}
