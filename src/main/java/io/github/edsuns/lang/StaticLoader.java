package io.github.edsuns.lang;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Edsuns@qq.com on 2022/10/30.
 */
class StaticLoader<T> {

    private static final ConcurrentMap<Class<?>, StaticLoader<?>> CACHE = new ConcurrentHashMap<>();

    private Class<? extends T> clz;
    private List<Field> fields;
    private int idx = 0;

    StaticLoader(Class<? extends T> clz, List<Field> fields) {
        this.clz = clz;
        this.fields = fields;
    }

    Field currentStaticField() {
        if (idx >= fields.size()) {
            throw new IllegalStateException("cause by illegal usage");
        }
        Field field = fields.get(idx);
        idx++;
        if (idx == fields.size()) {
            close();
        }
        return field;
    }

    private void close() {
        CACHE.remove(clz);
        this.clz = null;
        this.fields = null;
    }

    static <T> StaticLoader<T> get(T obj) {
        if (obj instanceof Class) throw new IllegalArgumentException("obj instanceof Class");
        @SuppressWarnings("unchecked")
        Class<? extends T> clz = (Class<? extends T>) obj.getClass();
        return get(clz);
    }

    @SuppressWarnings("unchecked")
    static <T> StaticLoader<T> get(Class<?> clz) {
        return (StaticLoader<T>) CACHE.computeIfAbsent(clz, k -> new StaticLoader<>(clz, getStaticFields(clz)));
    }

    private static List<Field> getStaticFields(Class<?> clz) {
        List<Field> result = new ArrayList<>();
        Field[] fields = clz.getDeclaredFields();
        for (Field field : fields) {
            int modifiers = field.getModifiers();
            if (Modifier.isStatic(modifiers) && (
                    field.getType().isAssignableFrom(clz) || field.getType() == Obj.class
            )) {
                result.add(field);
            }
        }
        return result;
    }
}
