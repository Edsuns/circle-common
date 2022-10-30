package io.github.edsuns.lang;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

/**
 * Created by Edsuns@qq.com on 2022/10/30.
 */
public class ErrorTraceStdSerializer extends StdSerializer<ErrorTrace> {

    protected ErrorTraceStdSerializer() {
        super(ErrorTrace.class);
    }

    @Override
    public void serialize(ErrorTrace errorTrace, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("name", errorTrace.getName());
        jsonGenerator.writeStringField("message", errorTrace.getMessage());
        jsonGenerator.writeNumberField("moduleId", errorTrace.getModuleId());
        jsonGenerator.writeEndObject();
    }
}
