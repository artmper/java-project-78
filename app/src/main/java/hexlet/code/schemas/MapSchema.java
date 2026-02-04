package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema<Map<String, String>> {

    @Override
    public MapSchema required() {
        super.required();

        return this;
    }

    public MapSchema sizeof(int mapSize) {
        addCheck("sizeof", value -> value == null || value.size() == mapSize);

        return this;
    }

    public void shape(Map<String, BaseSchema<String>> schemas) {
        addCheck("shape", map -> {
            if (map == null) {
                return true;
            }

            for (Map.Entry<String, BaseSchema<String>> entry : schemas.entrySet()) {
                String key = entry.getKey();
                BaseSchema<String> schema = entry.getValue();
                String value = map.get(key);

                if (!schema.isValid(value)) {
                    return false;
                }
            }

            return true;
        });

    }
}
