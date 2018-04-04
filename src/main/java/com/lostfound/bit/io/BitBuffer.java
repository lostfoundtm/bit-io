package com.lostfound.bit.io;

import java.util.Arrays;

public class BitBuffer {

    private byte[] bytes;

    public BitBuffer(byte[] bytes, boolean makeCopy) {
        if (makeCopy) {
            this.bytes = Arrays.copyOf(bytes, bytes.length);
        } else {
            this.bytes = bytes;
        }
    }

    public BitBuffer(byte[] bytes) {
        this(bytes, false);
    }

    public BitBuffer(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Size can be 0");
        }
        this.bytes = new byte[size];
    }

    public int getBit(int index) {
        if (index >= length()) {
            throw new IndexOutOfBoundsException();
        }

        int numberOfByte = index / 8;
        index = index % 8;

        return (bytes[numberOfByte] >> index) & 1;
    }

    public byte getBit(int byteIndex, int bitIndex) {
        if (byteIndex >= size() || bitIndex >= 8) {
            throw new IndexOutOfBoundsException();
        }

        return (byte) ((bytes[byteIndex] >> bitIndex) & 0b00000001);
    }

    public void setBit(int index, int b) {
        int currentValue = getBit(index);
        int expectedValue = b & 1;

        if (currentValue == expectedValue) {
            // nothing should be updated
            return;
        }

        // determine which operation should be performed
        int sign = currentValue < expectedValue ? 1 : -1;

        int numberOfByte = index / 8;
        index = index % 8;

        bytes[numberOfByte] += (1 << index) * sign;
    }

    public void setBit(int byteIndex, int bitIndex, int b) {
        int currentValue = getBit(byteIndex, bitIndex);
        int expectedValue = b & 1;

        if (currentValue == expectedValue) {
            // nothing should be updated
            return;
        }

        // determine which operation should be performed
        int sign = currentValue < expectedValue ? 1 : -1;

        bytes[byteIndex] += (1 << bitIndex) * sign;
    }

    /**
     * Returns number of bits.
     */
    public int length() {
        return bytes.length * 8;
    }

    /**
     * Returns byte array size.
     */
    public int size() {
        return bytes.length;
    }

    /**
     * Returns copy of byte array.
     */
    public byte[] toBytes() {
        return Arrays.copyOf(bytes, size());
    }

    /**
     * Returns byte array;
     */
    public byte[] getBytes() {
        return bytes;
    }
}
