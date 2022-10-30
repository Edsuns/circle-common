package io.github.edsuns.sync;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Supplier;

/**
 * Created by Edsuns@qq.com on 2022/7/22.
 */
public class SingleFlight {

    private final ConcurrentMap<String, Call<?>> calls;

    public SingleFlight() {
        this(new ConcurrentHashMap<>());
    }

    public SingleFlight(ConcurrentMap<String, Call<?>> calls) {
        this.calls = calls;
    }

    @SuppressWarnings("unchecked")
    public <T> T call(String key, Supplier<T> fn) {
        if (key == null || fn == null) throw new NullPointerException();
        Call<T> call = (Call<T>) calls.computeIfAbsent(key, k -> new Call<>(fn));
        T val = call.get();
        calls.remove(key);
        return val;
    }

    private static class Call<T> {
        final Supplier<T> fn;
        volatile T val;

        Call(Supplier<T> fn) {
            this.fn = fn;
        }

        T get() {
            T v = val;
            if (v == null) {
                synchronized (this) {
                    v = val;
                    if (v == null) {
                        v = val = fn.get();
                    }
                }
            }
            return v;
        }
    }
}
