package org.boisdechet.org.adventofcode2020;

import org.boisdechet.adventofcode2020.Day14;
import org.boisdechet.adventofcode2020.Day15;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class Day15Test {

    @Test
    public void examples() throws Exception {
        assertEquals(436, Day15.part1("0,3,6"));
        assertEquals(1, Day15.part1("1,3,2"));
        assertEquals(10, Day15.part1("2,1,3"));
        assertEquals(27, Day15.part1("1,2,3"));
        assertEquals(78, Day15.part1("2,3,1"));
        assertEquals(438, Day15.part1("3,2,1"));
        assertEquals(1836, Day15.part1("3,1,2"));
        // takes too long !!!
//        assertEquals(175594, Day15.part2("0,3,6"));
//        assertEquals(2578, Day15.part2("1,3,2"));
//        assertEquals(3544142, Day15.part2("2,1,3"));
//        assertEquals(261214, Day15.part2("1,2,3"));
//        assertEquals(6895259, Day15.part2("2,3,1"));
//        assertEquals(18, Day15.part2("3,2,1"));
//        assertEquals(362, Day15.part2("3,1,2"));
        // puzzle answers
        assertEquals(1238, Day15.part1("9,6,0,10,18,2,1"));
        assertEquals(3745954, Day15.part2("9,6,0,10,18,2,1"));
    }

}
