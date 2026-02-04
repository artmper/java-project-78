package hexlet.code.schemas;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    Map<String, Predicate<T>> checks = new LinkedHashMap<>();

    public BaseSchema<T> required() {
        addCheck("required", Objects::nonNull);

        return this;
    }

    public final void addCheck(String checkName, Predicate<T> predicate) {
        checks.put(checkName, predicate);
    }

    public final boolean isValid(T value) {
        for (Predicate<T> check : checks.values()) {
            if (!check.test(value)) {
                return false;
            }
        }

        return true;
    }
}
