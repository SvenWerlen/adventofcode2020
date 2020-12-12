package org.boisdechet.org.adventofcode2020;

import org.boisdechet.adventofcode2020.Day01;
import org.boisdechet.adventofcode2020.Day12;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class Day12Test {

    @Test
    public void examples() throws Exception {
        assertEquals(25, Day12.part1("s1"));
        assertEquals(286, Day12.part2("s1"));
        // puzzle answers
        assertEquals(1589, Day12.part1(null));
        assertEquals(23960, Day12.part2(null));
    }

}
