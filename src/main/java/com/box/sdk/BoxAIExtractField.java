package com.box.sdk;

import com.box.sdk.internal.utils.JsonUtils;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import java.util.List;

public class BoxAIExtractField extends BoxJSONObject {
    /**
     * The type of the field. It include but is not limited to string, float, date, enum, and multiSelect.
     */
    private String type;
    /**
     * The description of the field.
     */
    private String description;
    /**
     * The display name of the field.
     */
    private String displayName;
    /**
     * The key of the field.
     */
    private String key;
    /**
     * A list of options for this field.
     * This is most often used in combination with the enum and multiSelect field types.
     */
    private List<BoxAIExtractFieldOption> options;
    /**
     * The prompt of the field.
     */
    private String prompt;

    /**
     * Constructs a BoxAIExtractField object with a given type, description, display name, key, options, and prompt.
     *
     * @param type        the type of the field.
     * @param description the description of the field.
     * @param displayName the display name of the field.
     * @param key         the key of the field.
     * @param options     a list of options for this field.
     * @param prompt      the prompt of the field.
     */
    public BoxAIExtractField(String type,
                             String description,
                             String displayName,
                             String key, List<BoxAIExtractFieldOption> options,
                             String prompt) {
        this.type = type;
        this.description = description;
        this.displayName = displayName;
        this.key = key;
        this.options = options;
        this.prompt = prompt;
    }

    public JsonObject getJSONObject() {
        JsonObject jsonObject = new JsonObject();
        JsonUtils.addIfNotNull(jsonObject, "type", this.type);
        JsonUtils.addIfNotNull(jsonObject, "description", this.description);
        JsonUtils.addIfNotNull(jsonObject, "displayName", this.displayName);
        JsonUtils.addIfNotNull(jsonObject, "key", this.key);
        if (this.options != null) {
            JsonArray options = new JsonArray();
            for (BoxAIExtractFieldOption option : this.options) {
                options.add(option.getJSONObject());
            }
            jsonObject.add("options", options);
        }
        JsonUtils.addIfNotNull(jsonObject, "prompt", this.prompt);
        return jsonObject;
    }
}
