package com.box.sdk;


import com.box.sdk.internal.utils.JsonUtils;
import com.eclipsesource.json.JsonObject;

public class BoxAIExtractFieldOption extends BoxJSONObject {
    /**
     * A unique identifier for the option.
     */
    final String key;

    /**
     * Constructs a BoxAIExtractFieldOption object with a given key.
     *
     * @param key the key of the field option.
     */
    public BoxAIExtractFieldOption(String key) {
        this.key = key;
    }

    public JsonObject getJSONObject() {
        JsonObject jsonObject = new JsonObject();
        JsonUtils.addIfNotNull(jsonObject, "key", this.key);
        return jsonObject;
    }
}
