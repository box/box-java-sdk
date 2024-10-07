package com.box.sdk;

import com.box.sdk.internal.utils.JsonUtils;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Represents an AI Agent used for extraction.
 */
@BoxResourceType("ai_agent_extract_structured")
public class BoxAIAgentExtractStructured extends BoxAIAgent {

    /**
     * The type of the AI Ask agent.
     */
    public static final String TYPE = "ai_agent_extract_structured";

    /**
     * AI agent tool used to handle basic text.
     */
    private BoxAIAgentAskBasicText basicText;
    /**
     * AI agent tool used to handle longer text.
     */
    private BoxAIAgentAskLongText longText;

    /**
     * Constructs an AI agent with default settings.
     * @param basicText AI agent tool used to handle basic text.
     * @param longText AI agent tool used to handle longer text.
     */
    public BoxAIAgentExtractStructured(BoxAIAgentAskBasicText basicText,
                                       BoxAIAgentAskLongText longText) {
        super(TYPE);
        this.basicText = basicText;
        this.longText = longText;
    }

    /**
     * Constructs an AI agent with default settings.
     * @param jsonObject JSON object representing the AI agent.
     */
    public BoxAIAgentExtractStructured(JsonObject jsonObject) {
        super(jsonObject);
    }

    /**
     * Gets the AI agent tool used to handle basic text.
     * @return The AI agent tool used to handle basic text.
     */
    public BoxAIAgentAskBasicText getBasicText() {
        return basicText;
    }

    /**
     * Sets the AI agent tool used to handle basic text.
     * @param basicText The AI agent tool used to handle basic text.
     */
    public void setBasicText(BoxAIAgentAskBasicText basicText) {
        this.basicText = basicText;
    }

    /**
     * Gets the AI agent tool used to handle longer text.
     * @return The AI agent tool used to handle longer text.
     */
    public BoxAIAgentAskLongText getLongText() {
        return longText;
    }

    /**
     * Sets the AI agent tool used to handle longer text.
     * @param longText The AI agent tool used to handle longer text.
     */
    public void setLongText(BoxAIAgentAskLongText longText) {
        this.longText = longText;
    }

    @Override
    void parseJSONMember(JsonObject.Member member) {
        super.parseJSONMember(member);
        String memberName = member.getName();
        JsonValue memberValue = member.getValue();
        try {
            switch (memberName) {
                case "basic_text":
                    this.basicText = new BoxAIAgentAskBasicText(memberValue.asObject());
                    break;
                case "long_text":
                    this.longText = new BoxAIAgentAskLongText(memberValue.asObject());
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            throw new BoxAPIException("Could not parse JSON response.", e);
        }
    }

    @Override
    public JsonObject getJSONObject() {
        JsonObject jsonObject = new JsonObject();
        JsonUtils.addIfNotNull(jsonObject, "type", this.getType());
        JsonUtils.addIfNotNull(jsonObject, "basic_text", this.basicText.getJSONObject());
        JsonUtils.addIfNotNull(jsonObject, "long_text", this.longText.getJSONObject());
        return jsonObject;
    }
}

