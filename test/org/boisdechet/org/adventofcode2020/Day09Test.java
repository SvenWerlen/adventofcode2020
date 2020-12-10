package org.boisdechet.org.adventofcode2020;

import org.boisdechet.adventofcode2020.Day08;
import org.boisdechet.adventofcode2020.Day09;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class Day09Test {

    @Test
    public void examples() throws Exception {
        assertEquals(127, Day09.part1("s1"));
        assertEquals(62, Day09.part2("s1"));
        // puzzle answers
        assertEquals(15690279, Day09.part1(null));
        assertEquals(2174232, Day09.part2(null));
    }

}
