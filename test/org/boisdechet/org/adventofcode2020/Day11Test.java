package org.boisdechet.org.adventofcode2020;

import org.boisdechet.adventofcode2020.Day10;
import org.boisdechet.adventofcode2020.Day11;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class Day11Test {

    @Test
    public void examples() throws Exception {
        assertEquals(37, Day11.part1("s1"));
        assertEquals(26, Day11.part2("s1"));
        // puzzle answers
        assertEquals(2412, Day11.part1(null));
        assertEquals(2176, Day11.part2(null));
    }

}
