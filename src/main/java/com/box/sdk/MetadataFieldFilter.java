package com.box.sdk;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Filter for matching against a metadata field.
 */
public class MetadataFieldFilter {

    private String field;
    private JsonValue value;

    /**
     * Create a filter for matching against a string metadata field.
     * @param field the field to match against.
     * @param value the value to match against.
     */
    public MetadataFieldFilter(String field, String value) {

        this.field = field;
        this.value = JsonValue.valueOf(value);
    }

    /**
     * Create a filter for matching against a metadata field defined in JSON.
     * @param jsonObj the JSON object to construct the filter from.
     */
    public MetadataFieldFilter(JsonObject jsonObj) {
        this.field = jsonObj.get("field").asString();

        JsonValue value = jsonObj.get("value");
        this.value = value;
    }

    /**
     * Get the JSON representation of the metadata field filter.
     * @return the JSON object representing the filter.
     */
    public JsonObject getJsonObject() {

        JsonObject obj = new JsonObject();
        obj.add("field", this.field);

        obj.add("value", this.value);

        return obj;
    }
}
