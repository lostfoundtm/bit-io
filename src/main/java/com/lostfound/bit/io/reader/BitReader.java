package com.lostfound.bit.io.reader;

import com.lostfound.bit.io.Bits;

public abstract class BitReader {

    /**
     * Reads one bit.
     */
    abstract public int readBit();

    /**
     * Reads specified amount of bits.
     *
     * @param amount number of bits to read
     * @return object that contains bits value and their amount
     */
    public Bits readBits(int amount) {
        if (amount < 1 || amount > 8) {
            throw new IndexOutOfBoundsException();
        }

        Bits bits = new Bits();
        for (int i = 0; i < amount; i++) {
            int b = readBit();

            if (b == -1) {
                break;
            }

            bits.pushBit(b);
        }

        return bits;
    }

    /**
     * Skips one bit.
     *
     * @return 1 - if succeed, 0 - if failed
     */
    public int skip() {
        return skip(1);
    }

    /**
     * Skips {@code n} bits
     *
     * @param n number of bits to skip
     * @return number of bits have been skipped
     */
    public int skip(int n) {
        int skipped = 0;

        for (int i = 0; i < n; i++) {
            int b = readBit();

            if (b == -1) {
                break;
            }

            skipped++;
        }

        return skipped;
    }
}
