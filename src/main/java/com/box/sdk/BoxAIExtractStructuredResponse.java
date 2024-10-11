package com.box.sdk;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;

/**
 * AI response to a user request.
 */
public class BoxAIExtractStructuredResponse extends BoxJSONObject {
    private final JsonObject sourceJson;

    /**
     * Constructs a BoxAIResponse object.
     */
    public BoxAIExtractStructuredResponse(String json) {
        super(json);
        this.sourceJson = Json.parse(json).asObject();
    }

    /**
     * Constructs an BoxAIResponse object using an already parsed JSON object.
     *
     * @param jsonObject the parsed JSON object.
     */
    BoxAIExtractStructuredResponse(JsonObject jsonObject) {
        super(jsonObject);
        this.sourceJson = jsonObject;
    }

    /**
     * Gets the source JSON of the AI response.
     *
     * @return the source JSON of the AI response.
     */
    public JsonObject getSourceJson() {
        return sourceJson;
    }
}
