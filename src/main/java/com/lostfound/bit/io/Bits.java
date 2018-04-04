package com.lostfound.bit.io;

public class Bits {

    /**
     * @see #getValue()
     */
    private int value;

    /**
     * @see #getAmount()
     */
    private int amount;

    public Bits() {
    }

    public Bits(int value, int amount) {
        if (amount < 1 || amount > 8) {
            throw new IndexOutOfBoundsException();
        }

        this.value = value;
        this.amount = amount;
    }

    public Bits(Bits clone) {
        this.value = clone.value;
        this.amount = clone.amount;
    }

    /**
     * Int value that contains bits.
     */
    public int getValue() {
        return value;
    }

    /**
     * Number of bits that value contains.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Adds one bit. New bit will be set from right.<br>
     * Example:
     * <ul>
     * <li>Value before: 0000 0101</li>
     * <li>Call method: {@code pushBit(1)}</li>
     * <li>Value after: 0000 1101</li>
     * </ul>
     *
     * @param b bit value: {@code 0} or {@code 1}
     */
    public void pushBit(int b) {
        if (amount >= 8) {
            throw new IndexOutOfBoundsException();
        }
        value += b << amount;
        amount++;
    }

    /**
     * Removes and returns first (from left) bit.
     * Example:
     * <ul>
     * <li>Value before: 0000 1101</li>
     * <li>Call method: {@code popBit()}</li>
     * <li>Value after: 0000 0110</li>
     */
    public int popBit() {
        if (amount < 1) {
            throw new IndexOutOfBoundsException();
        }
        int b = value & 1;
        value = value >> 1;
        amount--;
        return b;
    }

    /**
     * Returns first (from left) bit.
     */
    public int pickBit() {
        if (amount < 1) {
            throw new IndexOutOfBoundsException();
        }
        return value & 1;
    }
}
