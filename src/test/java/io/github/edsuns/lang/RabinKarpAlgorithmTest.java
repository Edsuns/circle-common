package io.github.edsuns.lang;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Edsuns@qq.com on 2022/4/25.
 */
class RabinKarpAlgorithmTest {

    @Test
    void test() {
        assertEquals(3, RabinKarpAlgorithm.search("abcdefg", "def"));
        assertEquals(-1, RabinKarpAlgorithm.search("abcdefg", "edc"));
        assertEquals(-1, RabinKarpAlgorithm.search("def", "abcdefg"));
        assertEquals(0, RabinKarpAlgorithm.search("abc", ""));
    }
}
