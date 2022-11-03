package io.github.edsuns.util;

import java.io.*;
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

    public static <T, R> SerializedLambda getSerializedLambda(SerializableFunction<T, R> func) {
        try {
            Method writeReplace = func.getClass().getDeclaredMethod("writeReplace");
            writeReplace.setAccessible(true);
            return (SerializedLambda) writeReplace.invoke(func);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ignored) {
        }
        try {
            return (SerializedLambda) deserialize(serialize(func));
        } catch (IOException | ClassNotFoundException ex) {
            throw new InternalError("impossible error", ex);
        }
    }

    public static byte[] serialize(Serializable obj) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new ObjectOutputStream(out).writeObject(obj);
        return out.toByteArray();
    }

    public static Serializable deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        return (Serializable) new ObjectInputStream(new ByteArrayInputStream(bytes)).readObject();
    }
}
