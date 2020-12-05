package org.boisdechet.org.adventofcode2020;

import org.boisdechet.adventofcode2020.Day03;
import org.boisdechet.adventofcode2020.Day04;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class Day04Test {

    @Test
    public void examples() throws Exception {
        assertEquals(2, Day04.part1("s1"));
        assertEquals(0, Day04.part2("s2"));
        assertEquals(4, Day04.part2("s3"));
        // puzzle answers
        assertEquals(216, Day04.part1(null));
        //assertEquals(2421944712L, Day03.part2(null));
    }

}
