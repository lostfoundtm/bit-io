package com.lostfound.bit.io.writer;

import com.lostfound.bit.io.Bits;
import org.junit.Assert;
import org.junit.Test;

public class BitMaskWriterTest {

    @Test
    public void writeBitTest() {
        BitFlexArrayWriter arrayWriter = new BitFlexArrayWriter();
        BitWriter writer = new BitMaskWriter(arrayWriter, 0b0010_1011);

        for (int i = 0; i < 16; i++) {
            writer.writeBit(1);
        }
        byte[] bytes = arrayWriter.toByteArray();

        Assert.assertEquals(0b0010_1011, bytes[0]);
        Assert.assertEquals(0b0010_1011, bytes[1]);
        Assert.assertEquals(0b0010_1011, bytes[2]);
        Assert.assertEquals(0b0010_1011, bytes[3]);
    }

    @Test
    public void writeBitsTest() {
        BitFlexArrayWriter arrayWriter = new BitFlexArrayWriter();
        BitWriter writer = new BitMaskWriter(arrayWriter, 0b0010_1011);

        for (int i = 0; i < 2; i++) {
            writer.writeBits(new Bits(255, 8));
        }
        byte[] bytes = arrayWriter.toByteArray();

        Assert.assertEquals(0b0010_1011, bytes[0]);
        Assert.assertEquals(0b0010_1011, bytes[1]);
        Assert.assertEquals(0b0010_1011, bytes[2]);
        Assert.assertEquals(0b0010_1011, bytes[3]);
    }

    @Test
    public void writeBits2MasksTest() {
        BitFlexArrayWriter arrayWriter = new BitFlexArrayWriter();
        BitMaskWriter writer = new BitMaskWriter(arrayWriter, 0b0010_1011);

        writer.writeBits(new Bits(255, 8));
        writer.flushByte();
        writer.resetBitMask(0b1110_1000);
        writer.writeBits(new Bits(255, 8));

        byte[] bytes = arrayWriter.toByteArray();

        Assert.assertEquals(0b0010_1011, bytes[0]);
        Assert.assertEquals(0b0010_1011, bytes[1]);
        Assert.assertEquals((byte) 0b1110_1000, bytes[2]);
        Assert.assertEquals((byte) 0b1110_1000, bytes[3]);
    }
}
