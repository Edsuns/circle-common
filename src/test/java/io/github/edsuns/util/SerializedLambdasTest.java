package io.github.edsuns.util;

import org.junit.jupiter.api.Test;

import java.lang.invoke.SerializedLambda;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Edsuns@qq.com on 2022/11/3.
 */
public class SerializedLambdasTest {

    @Test
    void test() {
        class Some {
            private String name;

            String getName() {
                return name;
            }
        }
        SerializedLambda lambda = SerializedLambdas.getSerializedLambda(Some::getName);
        assertEquals(Some.class.getName(), lambda.getImplClass().replace('/', '.'));
        assertEquals("getName", lambda.getImplMethodName());
    }
}
