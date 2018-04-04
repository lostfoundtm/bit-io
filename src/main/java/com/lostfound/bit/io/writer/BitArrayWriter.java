package com.lostfound.bit.io.writer;

import com.lostfound.bit.io.BitBuffer;
import com.lostfound.bit.io.Bits;

public class BitArrayWriter extends BitByteWriter {

    protected int byteIndex = 0;
    protected int bitIndex = 0;
    protected BitBuffer buf;

    public BitArrayWriter(byte[] bytes) {
        buf = new BitBuffer(bytes);
    }

    public BitArrayWriter(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Size can be 0");
        }
        buf = new BitBuffer(size);
    }

    @Override
    public void writeBit(int b) {
        ensureCapacity(1);
        buf.setBit(byteIndex, bitIndex, b);
        bitNext();
    }

    @Override
    public void writeBits(Bits bits) {
        ensureCapacity(bits.getAmount());

        while (bits.getAmount() > 0) {
            int b = bits.popBit();
            buf.setBit(byteIndex, bitIndex, b);
            bitNext();
        }
    }

    public byte[] toByteArray() {
        return buf.toBytes();
    }

    @Override
    protected int bitIndex() {
        return bitIndex;
    }

    @Override
    protected void bitNext() {
        bitIndex++;
        if (bitIndex >= 8) {
            byteIndex++;
            bitIndex = 0;
        }
    }

    protected void ensureCapacity(int amount) {
        if (count() + amount > buf.length()) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Number of already wrote bits.
     */
    protected int count() {
        return byteIndex * 8 + bitIndex;
    }
}
