package io.github.edsuns.lang;

import org.springframework.http.HttpStatus;

/**
 * Created by Edsuns@qq.com on 2022/10/29.
 */
public final class SystemErr extends RequestErr {

    private SystemErr(String message) {
        super(message, MODULE_ID, HttpStatus.BAD_REQUEST);
    }

    public static final long MODULE_ID = 1L;

    public static SystemErr INTERNAL_ERROR = new SystemErr("internal error");

    public static Obj<?> TEST = Obj.error(new SystemErr("should call constructor on every field"));

    public static SystemErr IO_ERROR = new SystemErr("io error");
}
