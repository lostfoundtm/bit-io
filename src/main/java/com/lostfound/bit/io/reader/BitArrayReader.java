package com.lostfound.bit.io.reader;

import com.lostfound.bit.io.BitBuffer;

public class BitArrayReader extends BitByteReader {

    protected int byteIndex = 0;
    protected int bitIndex = 0;
    protected BitBuffer buf;

    public BitArrayReader(byte[] bytes) {
        buf = new BitBuffer(bytes);
    }

    @Override
    public int readBit() {
        if (!isAvailable(1)) {
            return -1;
        }

        byte b = buf.getBit(byteIndex, bitIndex);
        bitNext();
        return b;
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

    protected boolean isAvailable(int amount) {
        return count() + amount <= buf.length();
    }

    /**
     * Number of already read bits.
     */
    protected int count() {
        return byteIndex * 8 + bitIndex;
    }
}
