package com.lostfound.bit.io.writer;

import com.lostfound.bit.io.Bits;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BitArrayWriterTest {

    @Test
    public void bitIndex7Test() {
        BitArrayWriter writer = new BitArrayWriter(2);

        for (int i = 0; i < 7; i++) {
            writer.writeBit(1);
        }

        assertEquals(7, writer.bitIndex());
        assertEquals(0, writer.byteIndex);
    }

    @Test
    public void bitIndex8Test() {
        BitArrayWriter writer = new BitArrayWriter(2);

        for (int i = 0; i < 8; i++) {
            writer.writeBit(1);
        }

        assertEquals(0, writer.bitIndex());
        assertEquals(1, writer.byteIndex);
    }

    @Test
    public void bitIndex16est() {
        BitArrayWriter writer = new BitArrayWriter(2);

        for (int i = 0; i < 16; i++) {
            writer.writeBit(1);
        }

        assertEquals(0, writer.bitIndex());
        assertEquals(2, writer.byteIndex);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void writeBitIndexOutOfBoundsExceptionTest() {
        BitArrayWriter writer = new BitArrayWriter(2);

        for (int i = 0; i < 16; i++) {
            writer.writeBit(1);
        }
        writer.writeBit(1);
    }

    @Test
    public void writeBitTest() {
        BitArrayWriter writer = new BitArrayWriter(1);

        writer.writeBit(1);
        writer.writeBit(0);
        writer.writeBit(1);
        byte b = writer.toByteArray()[0];

        assertEquals(5, b);
    }

    @Test
    public void writeBitsTest() {
        BitArrayWriter writer = new BitArrayWriter(1);

        writer.writeBits(new Bits(5, 3));
        byte b = writer.toByteArray()[0];

        assertEquals(5, b);
    }
}
