package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestStringSchema {
    StringSchema schema;
    Validator validator;

    @BeforeEach
    void beforeEach() {
        validator = new Validator();
        schema = validator.string();
    }

    @Test
    void testIsValid1() {
        boolean actual = schema.isValid(null);
        assertTrue(actual);
    }

    @Test
    void testIsValid2() {
        boolean actual = schema.required().isValid(null);
        assertFalse(actual);
    }

    @Test
    void testIsValid3() {
        boolean actual1 = schema.required().minLength(5).isValid("What");
        boolean actual2 = schema.minLength(3).isValid("What");
        assertFalse(actual1);
        assertTrue(actual2);
    }

    @Test
    void testIsValid4() {
        boolean actual1 = schema.required().contains("wh").isValid("what does the fox say");
        boolean actual2 = schema.contains("what").isValid("what does the fox say");
        boolean actual3 = schema.contains("whatthe").isValid("what does the fox say");

        assertTrue(actual1);
        assertTrue(actual2);
        assertFalse(actual3);
    }
}
