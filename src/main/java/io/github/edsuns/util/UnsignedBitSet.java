package io.github.edsuns.util;

import javax.annotation.concurrent.ThreadSafe;

/**
 * Created by Edsuns@qq.com on 2022/6/26.
 */
@ThreadSafe
public class UnsignedBitSet implements Cloneable {
    /*
     * BitSets are packed into arrays of "words". Currently a word is
     * a long, which consists of 64 bits, requiring 6 address bits.
     * The choice of word size is determined purely by performance concerns.
     */
    private static final int ADDRESS_BITS_PER_WORD = 6;

    /**
     * The internal field corresponding to the serialField "bits".
     */
    private long[] words;

    public UnsignedBitSet(int unsignedBits) {
        this.words = new long[wordIndex(unsignedBits - 1) + 1];
    }

    public void set(int bitIndex) {
        int wordIndex = wordIndex(bitIndex);
        checkCapacity(wordIndex);
        words[wordIndex] |= (1L << bitIndex);
    }

    public void set(int bitIndex, boolean value) {
        if (value)
            set(bitIndex);
        else
            clear(bitIndex);
    }

    public void clear(int bitIndex) {
        int wordIndex = wordIndex(bitIndex);
        checkCapacity(wordIndex);
        words[wordIndex] &= ~(1L << bitIndex);
    }

    public void clear() {
        int n = words.length;
        while (n > 0)
            words[--n] = 0;
    }

    public boolean get(int bitIndex) {
        int wordIndex = wordIndex(bitIndex);
        checkCapacity(wordIndex);
        return (words[wordIndex] & (1L << bitIndex)) != 0;
    }

    public boolean isEmpty() {
        int n = words.length;
        while (n > 0)
            if (words[--n] != 0) return false;
        return true;
    }

    public int words() {
        return words.length;
    }

    public int size() {
        return words.length << ADDRESS_BITS_PER_WORD;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof UnsignedBitSet))
            return false;

        UnsignedBitSet o = (UnsignedBitSet) obj;

        if (o.words.length != this.words.length)
            return false;

        int n = words.length;
        while (n > 0)
            if (o.words[--n] != words[n]) return false;
        return true;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        UnsignedBitSet result = (UnsignedBitSet) super.clone();
        result.words = words.clone();
        return result;
    }

    private void checkCapacity(int wordIndex) {
        if (wordIndex >= words.length)
            throw new IndexOutOfBoundsException("wordIndex=" + wordIndex + ", words=" + words());
    }

    private static int wordIndex(int bitIndex) {
        return (int) (Integer.toUnsignedLong(bitIndex) >> ADDRESS_BITS_PER_WORD);
    }
}
