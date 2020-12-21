package org.boisdechet.org.adventofcode2020;

import org.boisdechet.adventofcode2020.Day13;
import org.boisdechet.adventofcode2020.Day14;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class Day14Test {

    @Test
    public void examples() throws Exception {
        assertEquals(165, Day14.part1("s1"));
        assertEquals(208, Day14.part2("s2"));
        // puzzle answers
        assertEquals(13496669152158L, Day14.part1(null));
        assertEquals(3278997609887L, Day14.part2(null));
    }

}
