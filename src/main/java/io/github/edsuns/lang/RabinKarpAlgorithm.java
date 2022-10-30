package io.github.edsuns.lang;

/**
 * Created by Edsuns@qq.com on 2022/6/26.
 */
final class RabinKarpAlgorithm {

    private RabinKarpAlgorithm() {
        // private
    }

    /**
     * 不发生哈希碰撞时，时间复杂度为 O(n)；全都哈希碰撞时，时间复杂度为 O(nm)
     */
    public static int search(String str, String target) {
        int n = str.length(), m = target.length();
        if (m == 0) return 0;
        if (n < m) return -1;
        int x = rollingHash(target, m);
        int hash = rollingHash(str, m);
        for (int i = 0, end = n - m; i < end; i++) {
            if (hash == x && str.regionMatches(i, target, 0, m)) return i;
            hash = ((hash - (((int) str.charAt(i)) << (m - 1))) << 1) + str.charAt(i + m);
        }
        return -1;
    }

    static int rollingHash(String str, int count) {
        int hash = 0;
        for (int i = 0; i < count; i++) {
            hash += (str.charAt(i) << (count - i - 1));
        }
        return hash;
    }
}
