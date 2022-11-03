package io.github.edsuns.util;

import java.io.Serializable;
import java.util.function.Consumer;

/**
 * Created by Edsuns@qq.com on 2022/11/3.
 */
public interface SerializableConsumer<T> extends Consumer<T>, Serializable {
}
