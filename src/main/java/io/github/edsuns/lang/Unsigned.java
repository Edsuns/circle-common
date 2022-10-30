package io.github.edsuns.lang;

/**
 * Created by Edsuns@qq.com on 2022/6/26.
 */
public final class Unsigned {
    private Unsigned() {
        // private
    }

    public static long toUnsignedLong(int unsigned) {
        return Integer.toUnsignedLong(unsigned);
    }

    public static int toUnsignedInt(String unsigned) {
        return Integer.parseUnsignedInt(unsigned);
    }

    public static int toUnsignedInt(String unsigned, int radix) {
        return Integer.parseUnsignedInt(unsigned, radix);
    }

    public static long toUnsignedLong(String unsigned) {
        return Long.parseUnsignedLong(unsigned);
    }

    public static long toUnsignedLong(String unsigned, int radix) {
        return Long.parseUnsignedLong(unsigned, radix);
    }

    public static int plus(int unsignedA, int unsignedB) {
        return unsignedA + unsignedB;
    }

    public static int minus(int unsignedA, int unsignedB) {
        return unsignedA - unsignedB;
    }

    public static int multiply(int unsignedA, int unsignedB) {
        return unsignedA * unsignedB;
    }

    public static int divide(int unsignedA, int unsignedB) {
        return Integer.divideUnsigned(unsignedA, unsignedB);
    }

    public static int remainder(int unsignedA, int unsignedB) {
        return Integer.remainderUnsigned(unsignedA, unsignedB);
    }

    public static long plus(long unsignedA, long unsignedB) {
        return unsignedA + unsignedB;
    }

    public static long minus(long unsignedA, long unsignedB) {
        return unsignedA - unsignedB;
    }

    public static long multiply(long unsignedA, long unsignedB) {
        return unsignedA * unsignedB;
    }

    public static long divide(long unsignedA, long unsignedB) {
        return Long.divideUnsigned(unsignedA, unsignedB);
    }

    public static long remainder(long unsignedA, long unsignedB) {
        return Long.remainderUnsigned(unsignedA, unsignedB);
    }

    public static int compare(short unsignedA, short unsignedB) {
        return Short.compareUnsigned(unsignedA, unsignedB);
    }

    public static int compare(int unsignedA, int unsignedB) {
        return Integer.compareUnsigned(unsignedA, unsignedB);
    }

    public static int compare(long unsignedA, long unsignedB) {
        return Long.compareUnsigned(unsignedA, unsignedB);
    }

    public static int compare(int unsignedA, long unsignedB) {
        return Long.compareUnsigned(toUnsignedLong(unsignedA), unsignedB);
    }

    public static int compare(long unsignedA, int unsignedB) {
        return Long.compareUnsigned(unsignedA, toUnsignedLong(unsignedB));
    }
}
