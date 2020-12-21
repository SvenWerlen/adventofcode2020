package org.boisdechet.org.adventofcode2020;

import org.boisdechet.adventofcode2020.Day18;
import org.boisdechet.adventofcode2020.Day19;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class Day19Test {

    @Test
    public void examples() throws Exception {
        assertEquals(2, Day19.part1("s1"));
        assertEquals(3, Day19.part1("s2"));
        assertEquals(12, Day19.part2("s2"));
        // puzzle answers
        assertEquals(208, Day19.part1(null));
        assertEquals(316, Day19.part2(null));
    }

}
