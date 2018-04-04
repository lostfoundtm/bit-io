package com.lostfound.bit.io.reader;

import org.junit.Assert;
import org.junit.Test;

public class BitMaskReaderTest {

    @Test
    public void readBitTest() {
        BitArrayReader arrayReader = new BitArrayReader(new byte[]{0b0011_1001, 0b0011_1001});
        BitReader reader = new BitMaskReader(arrayReader, 0b0011_1001);

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            result.append(reader.readBit());
        }

        Assert.assertEquals("11111111", result.toString());
    }

    @Test
    public void readBitsTest() {
        BitArrayReader arrayReader = new BitArrayReader(new byte[]{0b0011_1001, 0b0011_1001});
        BitReader reader = new BitMaskReader(arrayReader, 0b0011_1001);

        byte result = (byte) reader.readBits(8).getValue();

        Assert.assertEquals((byte) 0b1111_1111, result);
    }

    @Test
    public void readBits2MasksTest() {
        BitArrayReader arrayReader = new BitArrayReader(new byte[]{0b0011_1001, 0b0011_1001});
        BitMaskReader reader = new BitMaskReader(arrayReader, 0b0011_1001);

        byte r1 = (byte) reader.readBits(4).getValue();
        reader.flushByte();
        reader.resetBitMask(0b1100_1001);
        byte r2 = (byte) reader.readBits(4).getValue();

        Assert.assertEquals((byte) 0b0000_1111, r1);
        Assert.assertEquals((byte) 0b0000_0011, r2);
    }
}
