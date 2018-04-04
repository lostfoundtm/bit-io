package com.lostfound.bit.io.reader;

public class BitMaskReader extends BitReader {

    protected BitByteReader reader;
    private int bitMask;

    public BitMaskReader(BitByteReader reader, int bitMask) {
        this.reader = reader;
        this.bitMask = bitMask;

        if (bitMask <= 0) {
            throw new IllegalArgumentException("Incorrect bit mask specified");
        }
    }

    @Override
    public int readBit() {
        ensureBitPosition();
        return reader.readBit();
    }

    public void resetBitMask(int bitMask) {
        this.bitMask = bitMask;
    }

    public void flushByte() {
        while (reader.bitIndex() != 0) {
            reader.bitNext();
        }
    }

    protected boolean matchBitMask() {
        int testMask = ~(1 << reader.bitIndex()) & 0x000000ff;
        return (bitMask & testMask) != bitMask;
    }

    protected void ensureBitPosition() {
        while (!matchBitMask()) {
            reader.bitNext();
        }
    }
}
