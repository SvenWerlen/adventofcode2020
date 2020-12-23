package org.boisdechet.org.adventofcode2020;

import org.boisdechet.adventofcode2020.Day22;
import org.boisdechet.adventofcode2020.Day23;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class Day23Test {

    @Test
    public void examples() throws Exception {
        assertEquals("67384529", Day23.part1("389125467"));
        assertEquals(149245887792L, Day23.part2("389125467"));
        // puzzle answers
        assertEquals("49576328", Day23.part1("523764819"));
        assertEquals(511780369955L, Day23.part2("523764819"));
    }

}
