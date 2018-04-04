package com.lostfound.bit.io.reader;

public abstract class BitByteReader extends BitReader {

    /**
     * Returns current bit index in byte.
     */
    abstract protected int bitIndex();


    /**
     * Move bit pointer to next bit.
     */
    abstract protected void bitNext();
}
