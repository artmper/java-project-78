package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestNumberSchema {

    NumberSchema schema;
    Validator validator;

    @BeforeEach
    void beforeEach() {
        validator = new Validator();
        schema = validator.number();
    }

    @Test
    void testIsValid1() {
        boolean actual1 = schema.isValid(5);
        boolean actual2 = schema.isValid(null);

        assertTrue(actual1);
        assertTrue(actual2);
    }

    @Test
    void testIsValid2() {
        boolean actual = schema.positive().isValid(null);

        assertTrue(actual);
    }

    @Test
    void testIsValid3() {
        boolean actual1 = schema.required().isValid(null);
        boolean actual2 = schema.required().isValid(15);

        assertFalse(actual1);
        assertTrue(actual2);
    }

    @Test
    void testIsValid4() {
        boolean actual = schema.required().positive().isValid(-3);

        assertFalse(actual);
    }

    @Test
    void testIsValid5() {
        boolean actual = schema.required().range(12, 24).isValid(17);

        assertTrue(actual);
    }
}
