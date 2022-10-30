package io.github.edsuns.lang;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

/**
 * Created by Edsuns@qq.com on 2022/10/29.
 */
@SuppressWarnings("rawtypes")
public class ObjStdSerializer extends StdSerializer<Obj> {

    public ObjStdSerializer() {
        super(Obj.class);
    }

    @Override
    public void serialize(Obj obj, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        if (obj.isError()) {
            jsonGenerator.writeObjectField("error", ((Obj<?>) obj).getError());
        } else {
            jsonGenerator.writeObjectField("value", obj.getValue());
        }
        jsonGenerator.writeEndObject();
    }
}
