package io.github.edsuns.lang;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Edsuns@qq.com on 2022/6/25.
 */
class Base62Test {

    @Test
    void maxValue_thenEqualsExpected() {
        final long expected = Long.MAX_VALUE;
        long actual = Base62.parseUnsigned(Base62.toUnsignedString(expected));
        assertEquals(expected, actual);

        final long expectedUnsignedMax = -1L;
        long actualUnsignedMax = Base62.parseUnsigned(Base62.toUnsignedString(expectedUnsignedMax));
        assertEquals(expectedUnsignedMax, actualUnsignedMax);
    }

    @Test
    void minValue_thenEqualsExpected() {
        final long expected = Long.MIN_VALUE;
        long actual = Base62.parseUnsigned(Base62.toUnsignedString(expected));
        assertEquals(expected, actual);
    }

    @Test
    void zeroValue_thenEqualsExpected() {
        final long expected = 0L;
        long actual = Base62.parseUnsigned(Base62.toUnsignedString(expected));
        assertEquals(expected, actual);
    }

    @Test
    void positiveValue_thenEqualsExpected() {
        final long expected = 17;
        long actual = Base62.parseUnsigned(Base62.toUnsignedString(expected));
        assertEquals(expected, actual);
    }

    @Test
    void negativeValue_thenEqualsExpected() {
        final long expected = -Long.MAX_VALUE;
        long actual = Base62.parseUnsigned(Base62.toUnsignedString(expected));
        assertEquals(expected, actual);
    }

    @Test
    void nullValue_thenThrows() {
        Exception exception = assertThrows(NumberFormatException.class, () -> Base62.parseUnsigned(null));

        String expectedMessage = "null";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void illegalValues_thenThrows() {
        Exception exception1 = assertThrows(NumberFormatException.class, () -> Base62.parseUnsigned(""));
        Exception exception2 = assertThrows(NumberFormatException.class, () -> Base62.parseUnsigned("-xyzZ"));
        Exception exception3 = assertThrows(NumberFormatException.class, () -> Base62.parseUnsigned("MNmn~"));

        String expectedMessage = "Illegal number format";

        assertTrue(exception1.getMessage().contains(expectedMessage));
        assertTrue(exception2.getMessage().contains(expectedMessage));
        assertTrue(exception3.getMessage().contains(expectedMessage));
    }

    @Test
    void overflowValues_thenThrows() {
        Exception exception1 = assertThrows(NumberFormatException.class, () -> Base62.parseUnsigned("lYGhA16ahyg"));
        Exception exception2 = assertThrows(NumberFormatException.class, () -> Base62.parseUnsigned("lYGhA16ahz0"));

        String expectedMessage = "Numeric overflow";

        assertTrue(exception1.getMessage().contains(expectedMessage));
        assertTrue(exception2.getMessage().contains(expectedMessage));
    }
}