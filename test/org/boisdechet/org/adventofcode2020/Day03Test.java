package org.boisdechet.org.adventofcode2020;

import org.boisdechet.adventofcode2020.Day02;
import org.boisdechet.adventofcode2020.Day03;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class Day03Test {

    @Test
    public void examples() throws Exception {
        assertEquals(7, Day03.part1("s1"));
        assertEquals(336, Day03.part2("s1"));
        // puzzle answers
        assertEquals(153, Day03.part1(null));
        assertEquals(2421944712L, Day03.part2(null));
    }

}
