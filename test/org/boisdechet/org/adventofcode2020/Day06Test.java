package org.boisdechet.org.adventofcode2020;

import org.boisdechet.adventofcode2020.Day05;
import org.boisdechet.adventofcode2020.Day06;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class Day06Test {

    @Test
    public void examples() throws Exception {
        assertEquals(11, Day06.part1("s1"));
        assertEquals(6, Day06.part2("s1"));
        // puzzle answers
        assertEquals(6735, Day06.part1(null));
        assertEquals(3221, Day06.part2(null));
    }

}
