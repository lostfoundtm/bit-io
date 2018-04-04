package com.lostfound.bit.io.writer;

import com.lostfound.bit.io.Bits;

public abstract class BitWriter {

    /**
     * Writes one bit.
     *
     * @param b bit value
     */
    public abstract void writeBit(int b);

    /**
     * Writes specified amount of bits which contains value.
     *
     * @param bits object that contains bits value and their amount
     */
    public void writeBits(Bits bits) {
        while (bits.getAmount() > 0) {
            writeBit(bits.popBit());
        }
    }

    /**
     * Writes specified amount of bits which contains value.
     *
     * @param value  bits value
     * @param amount amount of bits to write
     */
    public void writeBits(int value, int amount) {
        this.writeBits(new Bits(value, amount));
    }
}
