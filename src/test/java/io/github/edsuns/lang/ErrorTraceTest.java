package io.github.edsuns.lang;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Created by Edsuns@qq.com on 2022/10/30.
 */
public class ErrorTraceTest {

    @Test
    void autoFillName() {
        assertNotNull(SystemErr.INTERNAL_ERROR);
        assertEquals("TEST", SystemErr.TEST.getError().getName());
        assertEquals("IO_ERROR", SystemErr.IO_ERROR.getName());
    }
}
