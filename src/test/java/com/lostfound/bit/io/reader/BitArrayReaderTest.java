package com.lostfound.bit.io.reader;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BitArrayReaderTest {

    @Test
    public void bitIndex7Test() {
        BitArrayReader reader = new BitArrayReader(new byte[]{1, 1});

        for (int i = 0; i < 7; i++) {
            reader.readBit();
        }

        assertEquals(7, reader.bitIndex());
        assertEquals(0, reader.byteIndex);
    }

    @Test
    public void bitIndex8Test() {
        BitArrayReader reader = new BitArrayReader(new byte[]{1, 1});

        for (int i = 0; i < 8; i++) {
            reader.readBit();
        }

        assertEquals(0, reader.bitIndex());
        assertEquals(1, reader.byteIndex);
    }

    @Test
    public void bitIndex16est() {
        BitArrayReader reader = new BitArrayReader(new byte[]{1, 1});

        for (int i = 0; i < 16; i++) {
            reader.readBit();
        }

        assertEquals(0, reader.bitIndex());
        assertEquals(2, reader.byteIndex);
    }

    @Test
    public void readBitEndTest() {
        BitArrayReader reader = new BitArrayReader(new byte[]{5});

        for (int i = 0; i < 8; i++) {
            reader.readBit();
        }
        int b = reader.readBit();

        assertEquals(-1, b);
    }

    @Test
    public void readBitTest() {
        BitArrayReader reader = new BitArrayReader(new byte[]{5});

        assertEquals(1, reader.readBit());
        assertEquals(0, reader.readBit());
        assertEquals(1, reader.readBit());
    }

    @Test
    public void readBitsTest() {
        BitArrayReader reader = new BitArrayReader(new byte[]{5, (byte) 255});

        int b1 = reader.readBits(8).getValue();
        int b2 = reader.readBits(8).getValue();

        assertEquals(5, b1);
        assertEquals((byte) 255, (byte) b2);
    }
}
