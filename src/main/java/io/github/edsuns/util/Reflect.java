package io.github.edsuns.util;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by Edsuns@qq.com on 2022/10/30.
 */
public final class Reflect {

    public static Class<?> getGenericType(Field field) {
        Type type = field.getGenericType();
        if (type instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType) type;
            return (Class<?>) pType.getActualTypeArguments()[0];
        } else {
            return field.getType();
        }
    }
}
