package org.boisdechet.org.adventofcode2020;

import org.boisdechet.adventofcode2020.Day23;
import org.boisdechet.adventofcode2020.Day24;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class Day24Test {

    @Test
    public void examples() throws Exception {
        assertEquals(10, Day24.part1("s1"));
        assertEquals(2208, Day24.part2("s1"));
        // puzzle answers
        assertEquals(322, Day24.part1(null));
        assertEquals(3831, Day24.part2(null));
    }

}
