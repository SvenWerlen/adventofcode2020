package org.boisdechet.org.adventofcode2020;

import org.boisdechet.adventofcode2020.Day01;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class Day01Test {

    @Test
    public void examples() throws Exception {
        assertEquals(514579, Day01.part1("s1"));
        assertEquals(241861950, Day01.part2("s1"));
        // puzzle answers
        assertEquals(1016131, Day01.part1(null));
        assertEquals(276432018, Day01.part2(null));
    }

}
