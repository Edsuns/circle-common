package io.github.edsuns.lang;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by Edsuns@qq.com on 2022/6/25.
 */
class StringsTest {

    @Test
    void codePointSubstring() {
        String sub;

        sub = Strings.codePointSubstring("a\uD867\uDE3D✿ﾟb", 0, 3);
        assertEquals("a\uD867\uDE3D", sub);

        sub = Strings.codePointSubstring("a\uD867\uDE3D✿ﾟb", 0, 6);
        assertEquals("a\uD867\uDE3D✿ﾟb", sub);

        assertThrows(StringIndexOutOfBoundsException.class,
                () -> Strings.codePointSubstring("a\uD867\uDE3D✿ﾟb", 3, 7));
    }

    @Test
    void split() {
        assertEquals(0, Strings.split("", c -> !Character.isLetter(c)).size());
        assertEquals(0, Strings.split(",,,,", c -> !Character.isLetter(c)).size());
        assertEquals(List.of("a", "b", "c"), Strings.split(",a,b,,c,,", c -> !Character.isLetter(c)));
        assertEquals(List.of("a", "b", "c", "d"), Strings.split(",a,b,,c,,d", c -> !Character.isLetter(c)));
    }
}