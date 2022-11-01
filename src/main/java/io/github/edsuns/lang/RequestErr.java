package io.github.edsuns.lang;

import org.springframework.http.HttpStatus;

/**
 * Created by Edsuns@qq.com on 2022/10/30.
 */
public abstract class RequestErr extends ErrorTrace {

    private static final long serialVersionUID = -2389865799389747381L;
    private final HttpStatus status;

    protected RequestErr(String message, long moduleId, HttpStatus status) {
        super(message, moduleId);
        if (status.is2xxSuccessful() || status.is3xxRedirection())
            throw new IllegalArgumentException("status");
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
