package io.github.edsuns.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Edsuns@qq.com on 2022/6/26.
 */
class UnsignedBitSetTest {

    @Test
    void setPositive() {
        final int size = 100;
        UnsignedBitSet bits = new UnsignedBitSet(size);
        assertTrue(bits.isEmpty());

        bits.set(10);
        assertTrue(bits.get(10));
        bits.set(11, true);
        assertTrue(bits.get(11));
        bits.set(11, false);
        assertFalse(bits.get(11));
        bits.set(20, false);
        assertFalse(bits.get(20));

        assertThrows(IndexOutOfBoundsException.class, () -> bits.set(Integer.MIN_VALUE));

        assertFalse(bits.isEmpty());
    }

    @Test
    void setNegative() {
        final int size = Integer.MIN_VALUE + 100;
        UnsignedBitSet bits = new UnsignedBitSet(size);
        assertTrue(bits.isEmpty());
        assertTrue(Integer.compareUnsigned(size, bits.size()) <= 0);

        bits.set(Integer.MIN_VALUE);

        assertFalse(bits.isEmpty());
        assertTrue(bits.get(Integer.MIN_VALUE));
        assertFalse(bits.get(Integer.MIN_VALUE + 1));
    }

    @Test
    void size() {
        final int size = Integer.MIN_VALUE;
        assertEquals(0, Integer.toUnsignedLong(size) % Long.SIZE);
        UnsignedBitSet bits = new UnsignedBitSet(size);

        bits.set(bits.size() - 1);
        assertTrue(bits.get(bits.size() - 1));

        assertThrows(IndexOutOfBoundsException.class, () -> bits.get(bits.size()));
        assertEquals(size, bits.size());
    }

    @Test
    void clear() {
        final int size = Integer.MIN_VALUE + 100;
        UnsignedBitSet bits = new UnsignedBitSet(size);
        assertTrue(bits.isEmpty());

        bits.set(size + 1);
        bits.set(size);
        bits.set(0);
        bits.set(1);

        assertTrue(bits.get(size + 1));
        bits.clear(size + 1);
        assertFalse(bits.get(size + 1));

        assertTrue(bits.get(size));
        assertTrue(bits.get(0));
        assertTrue(bits.get(1));

        assertFalse(bits.isEmpty());
        bits.clear();
        assertTrue(bits.isEmpty());
    }

    @Test
    void cloneAndEquals() throws CloneNotSupportedException {
        final int size = 100;
        UnsignedBitSet bits = new UnsignedBitSet(size);
        bits.set(1);
        bits.set(3);
        bits.set(5);
        bits.set(7);
        bits.set(8);
        UnsignedBitSet another = new UnsignedBitSet(size + 64);
        another.set(1);
        another.set(3);
        another.set(5);
        another.set(7);
        Object clone = bits.clone();

        assertEquals(bits, bits);
        assertEquals(bits, clone);
        assertNotEquals(bits, another);
        assertNotEquals(bits, new Object());
    }
}