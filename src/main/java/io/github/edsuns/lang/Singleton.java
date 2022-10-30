package io.github.edsuns.lang;

/**
 * Created by Edsuns@qq.com on 2022/6/25.
 */
public abstract class Singleton<T> {

    private volatile T value;

    /**
     * get the lazy-load singleton
     */
    public T get() {
        T val = value;
        if (val == null) {
            synchronized (this) {
                val = value;
                if (val == null) {
                    value = val = init();
                }
            }
        }
        return val;
    }

    /**
     * override this method to implement lazy-load initialization
     */
    protected abstract T init();
}
