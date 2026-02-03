package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestMapSchema {
    MapSchema schema;
    Validator validator;

    @BeforeEach
    void beforeEach() {
        validator = new Validator();
        schema = validator.map();
    }

    @Test
    void testIsValid1() {
        boolean actual = schema.isValid(null);

        assertTrue(actual);
    }

    @Test
    void testIsValid2() {
        boolean actual1 = schema.required().isValid(null);
        boolean actual2 = schema.isValid(new HashMap<>());

        assertFalse(actual1);
        assertTrue(actual2);
    }

    @Test
    void testIsValid3() {
        boolean actual1 = schema.required().isValid(null);
        boolean actual2 = schema.isValid(new HashMap<>());

        assertFalse(actual1);
        assertTrue(actual2);
    }

    @Test
    void testIsValid4() {
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        boolean actual = schema.required().isValid(data);

        assertTrue(actual);
    }

    @Test
    void testSizeOf() {
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        boolean actual1 = schema.required().sizeof(2).isValid(data);

        data.put("key2", "value2");
        boolean actual2 = schema.isValid(data);

        assertFalse(actual1);
        assertTrue(actual2);
    }

    @Test
    void testShape() {
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", validator.string().required());
        schemas.put("lastName", validator.string().required().minLength(2));
        schema.shape(schemas);

        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        boolean actual1 = schema.isValid(human1);

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        boolean actual2 = schema.isValid(human2);

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        boolean actual3 = schema.isValid(human3);

        assertTrue(actual1);
        assertFalse(actual2);
        assertFalse(actual3);
    }
}
