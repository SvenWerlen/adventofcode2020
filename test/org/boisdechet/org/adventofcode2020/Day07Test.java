package org.boisdechet.org.adventofcode2020;

import org.boisdechet.adventofcode2020.Day06;
import org.boisdechet.adventofcode2020.Day07;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class Day07Test {

    @Test
    public void examples() throws Exception {
        assertEquals(4, Day07.part1("s1"));
        assertEquals(32, Day07.part2("s1"));
        assertEquals(126, Day07.part2("s2"));
        // puzzle answers
        assertEquals(242, Day07.part1(null));
        assertEquals(176035, Day07.part2(null));
    }

}
