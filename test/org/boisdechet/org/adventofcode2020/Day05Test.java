package org.boisdechet.org.adventofcode2020;

import org.boisdechet.adventofcode2020.Day04;
import org.boisdechet.adventofcode2020.Day05;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class Day05Test {

    @Test
    public void examples() throws Exception {
        assertEquals(357, Day05.part1("s1"));
        assertEquals(567, Day05.part1("s2"));
        assertEquals(119, Day05.part1("s3"));
        assertEquals(820, Day05.part1("s4"));
        // puzzle answers
        assertEquals(855, Day05.part1(null));
        assertEquals(552, Day05.part2(null));
    }

}
