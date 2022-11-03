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
        SerializedLambda lambda = SerializedLambdas.getLambda(Object::toString);
        assertEquals(Object.class.getName(), lambda.getImplClass().replace('/', '.'));
        assertEquals("toString", lambda.getImplMethodName());

        SerializedLambda bySerialize = io.github.edsuns.util.SerializedLambda.getSerializedLambda(
                (SerializableFunction<Object, String>) Object::toString);
        assertEquals("toString", bySerialize.getImplMethodName());

        SerializableConsumer<?> println = System.out::println;
        assertEquals("println", SerializedLambdas.getLambda(println).getImplMethodName());
    }
}
