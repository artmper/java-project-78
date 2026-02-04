package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {
    private int containsCount = 0;

    @Override
    public StringSchema required() {
        addCheck("required", value -> value != null && !value.isEmpty());

        return this;
    }

    public StringSchema minLength(int length) {
        addCheck("minLength", value -> value == null || value.length() >= length);

        return this;
    }

    public StringSchema contains(String str) {
        addCheck("contains" + containsCount++, value -> value == null || value.contains(str));

        return this;
    }
}
