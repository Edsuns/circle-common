package io.github.edsuns.util;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Edsuns@qq.com on 2022/11/3.
 */
public final class SerializedLambdas {
    private SerializedLambdas() {
        // private
    }

    public static <T, R> java.lang.invoke.SerializedLambda getLambda(SerializableFunction<T, R> lambda) {
        return getSerializedLambda(lambda);
    }

    public static <T> java.lang.invoke.SerializedLambda getLambda(SerializableConsumer<T> lambda) {
        return getSerializedLambda(lambda);
    }

    public static java.lang.invoke.SerializedLambda getSerializedLambda(Serializable lambda) {
        if (!lambda.getClass().isSynthetic()) {
            throw new IllegalArgumentException("require synthetic");
        }
        try {
            Method writeReplace = lambda.getClass().getDeclaredMethod("writeReplace");
            writeReplace.setAccessible(true);
            return (java.lang.invoke.SerializedLambda) writeReplace.invoke(lambda);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            return SerializedLambda.getSerializedLambda(lambda);
        }
    }
}
