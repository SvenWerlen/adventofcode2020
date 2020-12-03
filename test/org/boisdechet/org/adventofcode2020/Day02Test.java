package org.boisdechet.org.adventofcode2020;

import org.boisdechet.adventofcode2020.Day01;
import org.boisdechet.adventofcode2020.Day02;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class Day02Test {

    @Test
    public void examples() throws Exception {
        assertEquals(2, Day02.part1("s1"));
        assertEquals(1, Day02.part2("s1"));
        // puzzle answers
        assertEquals(640, Day02.part1(null));
        assertEquals(472, Day02.part2(null));
    }

}
