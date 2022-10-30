package io.github.edsuns.lang;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Edsuns@qq.com on 2022/6/26.
 */
class UnsignedTest {

    @Test
    void unsignedIntToUnsignedLong() {
        assertEquals(1L << 31, Unsigned.toUnsignedLong(1 << 31));
    }
}