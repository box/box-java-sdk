package com.box.sdk;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Filter for matching against a metadata field.
 */
public class MetadataFieldFilter {

    private String field;
    private String stringValue;
    private int intValue;

    /**
     * Create a filter for matching against a string metadata field.
     * @param field the field to match against.
     * @param value the value to match against.
     */
    public MetadataFieldFilter(String field, String value) {

        this.field = field;
        this.stringValue = value;
    }

    /**
     * Create a filter for matching against a numeric metadata field.
     * @param field the field to match against.
     * @param value the value to match against.
     */
    public MetadataFieldFilter(String field, int value) {

        this.field = field;
        this.intValue = value;
    }

    /**
     * Create a filter for matching against a metadata field defined in JSON.
     * @param jsonObj the JSON object to construct the filter from.
     */
    public MetadataFieldFilter(JsonObject jsonObj) {
        this.field = jsonObj.get("field").asString();

        JsonValue value = jsonObj.get("value");
        if (value.isString()) {
            this.stringValue = value.asString();
        } else {
            this.intValue = value.asInt();
        }
    }

    /**
     * Get the JSON representation of the metadata field filter.
     * @return the JSON object representing the filter.
     */
    public JsonObject getJsonObject() {

        JsonObject obj = new JsonObject();
        obj.add("field", this.field);

        if (this.stringValue != null) {
            obj.add("value", this.stringValue);
        } else {
            obj.add("value", this.intValue);
        }

        return obj;
    }
}
