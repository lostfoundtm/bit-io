package com.lostfound.bit.io;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BitsTest {

    @Test
    public void pushBitTest() {
        Bits bits = new Bits(0b0000_0101, 3);

        bits.pushBit(1);
        int value = bits.getValue();

        assertEquals(0b0000_1101, value);
    }

    @Test
    public void popBitTest() {
        Bits bits = new Bits(0b0000_1101, 3);

        int b = bits.popBit();
        int value = bits.getValue();

        assertEquals(1, b);
        assertEquals(0b0000_0110, value);
    }
}
