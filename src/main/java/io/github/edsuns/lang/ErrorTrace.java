package io.github.edsuns.lang;

import java.io.Serializable;
import java.util.Objects;
import javax.annotation.Nonnull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by Edsuns@qq.com on 2022/10/29.
 */
@JsonSerialize(using = ErrorTraceStdSerializer.class)
@JsonDeserialize(using = ErrorTraceStdDeserializer.class)
public abstract class ErrorTrace implements Serializable {

    private static final long serialVersionUID = 1639925119803949339L;

    private final String name;
    private final String message;
    private final long moduleId;

    protected ErrorTrace(String message, long moduleId) {
        this.name = StaticLoader.get(this).currentStaticField().getName();
        this.message = message;
        this.moduleId = moduleId;
    }

    protected ErrorTrace(Class<?> container, String message, long moduleId) {
        this.name = StaticLoader.get(container).currentStaticField().getName();
        this.message = message;
        this.moduleId = moduleId;
    }

    ErrorTrace(String name, String message, long moduleId) {
        this.name = name;
        this.message = message;
        this.moduleId = moduleId;
    }

    @Nonnull
    public String getName() {
        return this.name;
    }

    /**
     * description of the error
     */
    @Nonnull
    public String getMessage() {
        return this.message;
    }

    /**
     * generated global unique id for easier tracing, recommend using static variables
     */
    public long getModuleId() {
        return moduleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ErrorTrace)) return false;
        ErrorTrace that = (ErrorTrace) o;
        return moduleId == that.moduleId && name.equals(that.name) && message.equals(that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, message, moduleId);
    }

    @Override
    public String toString() {
        return "ErrorTrace{" +
                "name=" + getName() +
                ", moduleId=" + getModuleId() +
                ", message=" + getMessage() +
                '}';
    }
}
