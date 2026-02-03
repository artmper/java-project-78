package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer> {

    @Override
    public NumberSchema required() {
        super.required();

        return this;
    }

    public NumberSchema positive() {
        addCheck("positive", value -> value == null || value > 0);

        return this;
    }

    public NumberSchema range(int min, int max) {
        addCheck("range", value -> value == null || (value >= min && value <= max));

        return this;
    }
}
