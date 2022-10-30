package io.github.edsuns.lang;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Edsuns@qq.com on 2022/10/29.
 */
public class ArgTest {

    @Test
    void errorObj() {
        Obj<Integer> obj = Obj.error(SystemErr.INTERNAL_ERROR);
        assertTrue(obj.isError());
        assertEquals(SystemErr.INTERNAL_ERROR, obj.getError());
        assertThrows(NullPointerException.class, obj::getValue);
        assertThrows(ArgException.class, obj::unwrap);
        assertThrows(ArgException.class, () -> obj.map(String::valueOf).unwrap());
        assertThrows(NullPointerException.class, () -> obj.map(String::valueOf).getValue());

        assertThrows(IllegalStateException.class,
                () -> obj.map(String::valueOf, "mapped error")
                        .unwrap(err -> {
                            assertEquals(err, "mapped error");
                            return new IllegalStateException(err);
                        }));
    }

    @Test
    void nonNullObj() {
        Obj<Integer> obj = Obj.of(1);
        assertFalse(obj.isError());
        assertEquals(1, obj.getValue());
        assertThrows(NullPointerException.class, obj::getError);
        assertEquals(1, obj.unwrap());

        assertThrows(NullPointerException.class, () -> obj.map(val -> null).unwrap());

        assertEquals("1", obj.map(String::valueOf).getValue());
        assertEquals("1", obj.map(String::valueOf).unwrap());
        assertEquals("1", obj.map(String::valueOf, "mapped error").unwrap(IllegalArgumentException::new));

        assertThrows(ArgException.class,
                () -> obj.flatMap(val -> Arg.error(SystemErr.INTERNAL_ERROR)).unwrap());
    }

    @Test
    void equals() {
        assertEquals(Obj.of(1), Obj.of(1));
        assertEquals(Arg.notNull(1), Obj.of(1));

        assertNotEquals(Optional.of(1), Obj.of(1));
    }

    @Test
    void jsonNotNullObj() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        Obj<Integer> obj = Obj.of(1);
        String json = mapper.writeValueAsString(obj);
        @SuppressWarnings("unchecked")
        Obj<Integer> deserialize = (Obj<Integer>) mapper.readValue(json, Obj.class);
        assertEquals(obj, deserialize);
        assertFalse(deserialize.isError());
        assertEquals(1, deserialize.getValue());
    }

    @Test
    void jsonNotErrorObj() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        Obj<Integer> obj = Obj.error(SystemErr.INTERNAL_ERROR);
        String json = mapper.writeValueAsString(obj);
        @SuppressWarnings("unchecked")
        Obj<Integer> deserialize = (Obj<Integer>) mapper.readValue(json, Obj.class);
        assertEquals(obj, deserialize);
        assertTrue(deserialize.isError());
        assertEquals(SystemErr.INTERNAL_ERROR, deserialize.getError());
    }

    @Test
    void cast() {
        Obj<Integer> errObj = SystemErr.TEST.cast();
        assertTrue(errObj.isError());
    }
}
