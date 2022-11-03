package io.github.edsuns.util;

import java.io.Serializable;
import java.util.function.Function;

/**
 * Created by Edsuns@qq.com on 2022/11/3.
 */
@FunctionalInterface
public interface SerializableFunction<T, R> extends Function<T, R>, Serializable {
}
