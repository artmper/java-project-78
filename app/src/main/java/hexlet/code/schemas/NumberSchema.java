package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer, NumberSchema> {
    private boolean isPositive = false;
    private int lowerBound = Integer.MIN_VALUE;
    private int upperBound = Integer.MAX_VALUE;

    public NumberSchema positive() {
        if(!isPositive) {
            isPositive = true;
        }
        return this;
    }

    public NumberSchema range(int lower, int upper) {
        lowerBound = lower;
        upperBound = upper;
        return this;
    }

    @Override
    public boolean isValid(Integer value) {
        if (!this.isRequired) {
            if (value == null) {
                return true;
            } else if (!isPositive && value > 0) {
                return true;
            } else if (!isPositive && value < 0){
                return true;
            } else if (isPositive && value < 0) {
                return false;
            } else if (isPositive && value > 0) {
                return true;
            } else {
                return (lowerBound <= value && upperBound >= value);
            }
        }

        if (value == null) {
            return false;
        } else if (!isPositive && value > 0) {
            return true;
        } else if (!isPositive && value < 0){
            return true;
        } else if (isPositive && value < 0) {
            return false;
        } else if (isPositive && value > 0) {
            return true;
        } else {
            return (lowerBound <= value && upperBound >= value);
        }
    }
}
