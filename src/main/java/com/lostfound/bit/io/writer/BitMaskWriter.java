package com.lostfound.bit.io.writer;

public class BitMaskWriter extends BitWriter {

    protected BitByteWriter writer;
    private int bitMask;

    public BitMaskWriter(BitByteWriter writer, int bitMask) {
        this.writer = writer;
        this.bitMask = bitMask;

        if (bitMask <= 0) {
            throw new IllegalArgumentException("Incorrect bit mask specified");
        }
    }

    @Override
    public void writeBit(int b) {
        ensureBitPosition();
        writer.writeBit(b);
    }

    public void resetBitMask(int bitMask) {
        this.bitMask = bitMask;
    }

    public void flushByte() {
        while (writer.bitIndex() != 0) {
            writer.bitNext();
        }
    }

    protected boolean matchBitMask() {
        int testMask = ~(1 << writer.bitIndex()) & 0x000000ff;
        return (bitMask & testMask) != bitMask;
    }

    protected void ensureBitPosition() {
        while (!matchBitMask()) {
            writer.bitNext();
        }
    }
}
