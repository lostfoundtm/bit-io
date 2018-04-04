package com.lostfound.bit.io;

import org.junit.Test;

import static org.junit.Assert.*;

public class BitBufferTest {

    @Test
    public void toBytesTest() {
        byte[] inputBytes = new byte[]{1, 2, 3};
        BitBuffer buffer = new BitBuffer(inputBytes);

        byte[] bytes1 = buffer.toBytes();
        byte[] bytes2 = buffer.toBytes();

        assertNotSame(bytes1, bytes2);
    }

    @Test
    public void getBytesTest() {
        byte[] inputBytes = new byte[]{1, 2, 3};
        BitBuffer buffer = new BitBuffer(inputBytes);

        byte[] bytes1 = buffer.getBytes();
        byte[] bytes2 = buffer.getBytes();

        assertSame(bytes1, bytes2);
    }

    @Test
    public void getBitTest() {
        byte[] inputBytes = new byte[]{1, 2, 3};
        BitBuffer buffer = new BitBuffer(inputBytes);

        // indexes  - 7654 3210 | **** **98 | **** ****
        // buffer   - 0000 0001 | 0000 0010 | 0000 0011
        assertEquals(1, buffer.getBit(0));
        assertEquals(0, buffer.getBit(1));
        assertEquals(0, buffer.getBit(8));
        assertEquals(1, buffer.getBit(9));
    }

    @Test
    public void getBitByByteIndexTest() {
        byte[] inputBytes = new byte[]{1, 2, 3};
        BitBuffer buffer = new BitBuffer(inputBytes);

        // byte     -     0     |     1     |     2
        // indexes  - 7654 3210 | 7654 3210 | 7654 3210
        // buffer   - 0000 0001 | 0000 0010 | 0000 0011
        assertEquals(1, buffer.getBit(0, 0));
        assertEquals(0, buffer.getBit(0, 1));
        assertEquals(0, buffer.getBit(1, 0));
        assertEquals(1, buffer.getBit(1, 1));
    }

    @Test
    public void setBitTest() {
        BitBuffer buffer = new BitBuffer(2);

        buffer.setBit(8, 1);
        buffer.setBit(10, 1);

        assertEquals(5, buffer.getBytes()[1]);
    }

    @Test
    public void setBitByByteIndexTest() {
        BitBuffer buffer = new BitBuffer(2);

        buffer.setBit(1, 0, 1);
        buffer.setBit(1, 2, 1);

        assertEquals(5, buffer.getBytes()[1]);
    }

    @Test
    public void sizeTest() {
        BitBuffer buffer = new BitBuffer(4);

        assertEquals(4, buffer.size());
    }

    @Test
    public void lengthTest() {
        BitBuffer buffer = new BitBuffer(4);

        assertEquals(4 * 8, buffer.length());
    }
}