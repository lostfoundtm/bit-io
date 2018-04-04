package com.lostfound.bit.io.writer;


import com.lostfound.bit.io.BitBuffer;

public class BitFlexArrayWriter extends BitArrayWriter {

    private static final int DEFAULT_BUF_SIZE = 10;

    public BitFlexArrayWriter() {
        super(DEFAULT_BUF_SIZE);
    }

    public BitFlexArrayWriter(int initialSize) {
        super(initialSize);
    }

    @Override
    protected void ensureCapacity(int amount) {
        if (count() + amount > buf.length()) {
            grow(buf.size() + 1);
        }
    }

    protected void grow(int minCapacity) {
        if (minCapacity < 0) { // overflow
            throw new OutOfMemoryError();
        }

        int oldSize = buf.size();
        int newSize = oldSize + (oldSize >> 1);

        if (minCapacity > newSize) {
            newSize = minCapacity;
        }

        byte[] oldBytes = buf.getBytes();
        byte[] newBytes = new byte[newSize];

        System.arraycopy(oldBytes, 0, newBytes, 0, oldSize);
        buf = new BitBuffer(newBytes, false);
    }
}
