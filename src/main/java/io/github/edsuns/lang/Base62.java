package io.github.edsuns.lang;

/**
 * Created by Edsuns@qq.com on 2021/11/3.
 */
public final class Base62 {
    private Base62() {
        // private
    }

    public static final int RADIX = 62;

    static final char[] DIGITS = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D',
            'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z'
    };

    public static String toUnsignedString(long unsigned) {
        byte[] buf = new byte[11];
        int charPos = 10;

        while (Long.compareUnsigned(unsigned, RADIX) >= 0) {
            buf[charPos--] = (byte) DIGITS[(int) Long.remainderUnsigned(unsigned, RADIX)];
            unsigned = Long.divideUnsigned(unsigned, RADIX);
        }
        buf[charPos] = (byte) DIGITS[(int) unsigned];

        return new String(buf, charPos, (11 - charPos));
    }

    public static long parseUnsigned(String unsigned) throws NumberFormatException {
        if (unsigned == null) {
            throw new NumberFormatException("null");
        }

        int i = 0, len = unsigned.length();

        if (len > 0) {
            long result = 0;
            while (i < len) {
                // Accumulating negatively avoids surprises near MAX_VALUE
                int digit = digit(unsigned.charAt(i++));
                long multi = result * RADIX;
                if (digit < 0 || digit >= RADIX) {
                    throw new NumberFormatException(illegalFormatOf(unsigned));
                }
                if (Long.compareUnsigned(multi, result) < 0) {
                    throw new NumberFormatException(overflowOf(unsigned));
                }
                result = multi;
                long add = result + digit;
                if (Long.compareUnsigned(add, result) < 0) {
                    throw new NumberFormatException(overflowOf(unsigned));
                }
                result = add;
            }
            return result;
        }
        throw new NumberFormatException(illegalFormatOf(unsigned));
    }

    static int digit(char c) {
        return c >= 'A' && c <= 'Z' ? c - 'A' + Character.MAX_RADIX : Character.digit(c, Character.MAX_RADIX);
    }

    private static String illegalFormatOf(String unsigned) {
        return "Illegal number format: \"" + unsigned + "\"";
    }

    private static String overflowOf(String unsigned) {
        return "Numeric overflow: \"" + unsigned + "\"";
    }
}
