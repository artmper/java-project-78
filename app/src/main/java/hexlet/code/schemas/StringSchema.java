package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String, StringSchema> {
    private int minLength;
    private String substring = "";

    public StringSchema minLength(int length) {
        minLength = length;
        return this;
    }

    public StringSchema contains(String str) {
        substring = str;
        return this;
    }

    @Override
    public boolean isValid(String value) {
        if (!this.isRequired) {
            if ("".equals(value) || value == null) {
                return true;
            } else if (value.length() < minLength) {
                return false;
            } else {
                return value.contains(substring);
            }
        }

        if ("".equals(value) || value == null) {
            return false;
        } else if (value.length() < minLength) {
            return false;
        } else {
            return value.contains(substring);
        }
    }
}
