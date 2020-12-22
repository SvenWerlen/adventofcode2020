package org.boisdechet.org.adventofcode2020;

import org.boisdechet.adventofcode2020.Day20;
import org.boisdechet.adventofcode2020.Day21;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class Day21Test {

    @Test
    public void examples() throws Exception {
        assertEquals(5, Day21.part1("s1"));
        assertEquals("mxmxvkd,sqjhc,fvjkl", Day21.part2("s1"));
        // puzzle answers
        assertEquals(2779, Day21.part1(null));
        assertEquals("lkv,lfcppl,jhsrjlj,jrhvk,zkls,qjltjd,xslr,rfpbpn", Day21.part2(null));
    }

}
