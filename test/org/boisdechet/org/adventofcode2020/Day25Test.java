package org.boisdechet.org.adventofcode2020;

import org.boisdechet.adventofcode2020.Day01;
import org.boisdechet.adventofcode2020.Day25;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class Day25Test {

    @Test
    public void examples() throws Exception {
        assertEquals(14897079, Day25.part1(5764801, 17807724));
        // puzzle answers
        assertEquals(19924389, Day25.part1(9717666,20089533));
    }

}
