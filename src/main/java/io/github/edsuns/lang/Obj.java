package io.github.edsuns.lang;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.annotation.Nonnull;

import static java.util.Objects.requireNonNull;

/**
 * @author edsuns@qq.com
 * @date 2022/10/28 15:51
 */
@JsonSerialize(using = ObjStdSerializer.class)
public class Obj<T> extends Arg<T, ErrorTrace> {

    @SuppressWarnings("unused")
    Obj() {
        super();
    }

    protected Obj(T value, ErrorTrace error) {
        super(value, error);
    }

    public static <T> Obj<T> of(@Nonnull T value) {
        return new Obj<>(value, null);
    }

    public static <T> Obj<T> of(T value, @Nonnull ErrorTrace error) {
        requireNonNull(error);
        return new Obj<>(value, error);
    }

    public static <T> Obj<T> error(@Nonnull ErrorTrace error) {
        return new Obj<>(null, error);
    }
}
