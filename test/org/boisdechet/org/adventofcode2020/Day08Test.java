package org.boisdechet.org.adventofcode2020;

import org.boisdechet.adventofcode2020.Day07;
import org.boisdechet.adventofcode2020.Day08;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class Day08Test {

    @Test
    public void examples() throws Exception {
        assertEquals(5, Day08.part1("s1"));
        assertEquals(8, Day08.part2("s1"));
        // puzzle answers
        assertEquals(1384, Day08.part1(null));
        assertEquals(761, Day08.part2(null));
    }

}
