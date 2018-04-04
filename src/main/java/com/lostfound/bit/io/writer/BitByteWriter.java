package com.lostfound.bit.io.writer;

public abstract class BitByteWriter extends BitWriter {

    /**
     * Returns current bit index in byte.
     */
    abstract protected int bitIndex();


    /**
     * Move bit pointer to next bit.
     */
    abstract protected void bitNext();
}
