package io.github.edsuns.util;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Edsuns@qq.com on 2022/11/3.
 */
public final class SerializedLambdas {
    private SerializedLambdas() {
        // private
    }

    public static <T, R> SerializedLambda getLambda(SerializableFunction<T, R> lambda) {
        return getSerializedLambda(lambda);
    }

    public static <T> SerializedLambda getLambda(SerializableConsumer<T> lambda) {
        return getSerializedLambda(lambda);
    }

    public static SerializedLambda getSerializedLambda(Serializable lambda) {
        if (!lambda.getClass().isSynthetic()) {
            throw new IllegalArgumentException("require synthetic");
        }
        try {
            Method writeReplace = lambda.getClass().getDeclaredMethod("writeReplace");
            writeReplace.setAccessible(true);
            return (SerializedLambda) writeReplace.invoke(lambda);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new InternalError("impossible error", e);
        }
    }
}
