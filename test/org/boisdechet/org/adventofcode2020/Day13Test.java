package org.boisdechet.org.adventofcode2020;

import org.boisdechet.adventofcode2020.Day12;
import org.boisdechet.adventofcode2020.Day13;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class Day13Test {

    @Test
    public void examples() throws Exception {
        assertEquals(295, Day13.part1("s1"));
        assertEquals(1068781, Day13.part2("7,13,x,x,59,x,31,19"));
        assertEquals(3417, Day13.part2("17,x,13,19"));
        assertEquals(754018, Day13.part2("67,7,59,61"));
        assertEquals(779210, Day13.part2("67,x,7,59,61"));
        assertEquals(1261476, Day13.part2("67,7,x,59,61"));
        assertEquals(1202161486, Day13.part2("1789,37,47,1889"));
        // puzzle answers
        assertEquals(3246, Day13.part1(null));
        assertEquals(1010182346291467L, Day13.part2(null));
    }

}
