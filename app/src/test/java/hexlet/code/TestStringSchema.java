package hexlet.code;

import hexlet.code.schemas.StringSchema;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestStringSchema {
    private static StringSchema schema;

    @BeforeEach
    void beforeEach() {
        Validator validator = new Validator();
        schema = validator.string();
    }

    @Test
    void testIsValid1() {
        boolean actual = schema.isValid(null);
        assertTrue(actual);
    }

    @Test
    void testIsValidEmpty() {
        boolean actual1 = schema.isValid("");
        boolean actual2 = schema.required().isValid("");
        assertTrue(actual1);
        assertFalse(actual2);
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

    @Test
    void testIsValid5() {
        boolean actual1 = schema.minLength(10).isValid("          ");
        boolean actual2 = schema.minLength(3).contains(" the ").isValid("What the f");
        boolean actual3 = schema.required().isValid("      ");

        assertTrue(actual1);
        assertTrue(actual2);
        assertFalse(actual3);
    }
}
