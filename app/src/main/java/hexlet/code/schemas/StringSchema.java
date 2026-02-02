package hexlet.code.schemas;

public class StringSchema {
    private boolean isRequired;
    private int minLength;
    private String substring;

    public StringSchema() {
        this.isRequired = false;
        this.minLength = 0;
        this.substring = "";
    }

    public StringSchema required() {
        if (!isRequired) {
            isRequired = true;
        }
        return this;
    }

    public StringSchema minLength(int length) {
        minLength = length;
        return this;
    }

    public StringSchema contains(String str) {
        substring = str;
        return this;
    }

    public boolean isValid(String text) {
        if (!isRequired) {
            if ("".equals(text) || text == null) {
                return true;
            } else if (text.length() < minLength) {
                return false;
            } else {
                return text.contains(substring);
            }
        }

        if ("".equals(text) || text == null) {
            return false;
        } else if (text.length() < minLength) {
            return false;
        } else {
            return text.contains(substring);
        }

    }
}
