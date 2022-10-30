package io.github.edsuns.lang;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Created by Edsuns@qq.com on 2022/6/25.
 */
public class Strings {
    private Strings() {
        // private
    }

    public static int indexOf(String str, String target) {
        return RabinKarpAlgorithm.search(str, target);
    }

    public static List<String> split(String str, Function<Character, Boolean> isSplitChar) {
        if (str.length() == 0) return new ArrayList<>();
        List<String> ans = new ArrayList<>(8);
        int start = -1;
        for (int i = 0; i < str.length(); i++) {
            if (isSplitChar.apply(str.charAt(i))) {
                if (start != -1) {
                    ans.add(str.substring(start, i));
                    start = -1;
                }
            } else {
                if (start == -1)
                    start = i;
            }
        }
        if (start != -1) {
            ans.add(str.substring(start));
        }
        return ans;
    }

    public static String codePointSubstring(String src, int beginIndexMax, int endIndexMax) {
        if (endIndexMax > src.length()) {
            throw new StringIndexOutOfBoundsException();
        }
        int beginIndex = beginIndexMax <= 0 ? beginIndexMax : codePointStart(src, beginIndexMax);
        int endIndex = endIndexMax == src.length() ? endIndexMax : codePointStart(src, endIndexMax);
        return src.substring(beginIndex, endIndex);
    }

    public static int codePointStart(String src, int index) {
        return src.offsetByCodePoints(index + 1, -1);
    }
}
