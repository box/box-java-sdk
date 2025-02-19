package com.box.sdk;

import com.box.sdk.internal.utils.JsonUtils;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Represents the embeddings used by an AI agent.
 */
public class BoxAIAgentEmbeddings extends BoxJSONObject {
    /**
     * The model used for the AI Agent for calculating embeddings.
     */
    private String model;
    /**
     * The strategy used for the AI Agent for calculating embeddings.
     */
    private BoxAIAgentEmbeddingsStrategy strategy;

    /**
     * Constructs an AI agent with default settings.
     * @param model The model used for the AI Agent for calculating embeddings.
     * @param strategy The strategy used for the AI Agent for calculating embeddings.
     */
    public BoxAIAgentEmbeddings(String model, BoxAIAgentEmbeddingsStrategy strategy) {
        this.model = model;
        this.strategy = strategy;
    }

    /**
     * Constructs an AI agent with default settings.
     * @param jsonObject JSON object representing the AI agent.
     */
    public BoxAIAgentEmbeddings(JsonObject jsonObject) {
        super(jsonObject);
    }

    /**
     * Gets the model used for the AI Agent for calculating embeddings.
     * @return The model used for the AI Agent for calculating embeddings.
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets the model used for the AI Agent for calculating embeddings.
     * @param model The model used for the AI Agent for calculating embeddings.
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Gets the strategy used for the AI Agent for calculating embeddings.
     * @return The strategy used for the AI Agent for calculating embeddings.
     */
    public BoxAIAgentEmbeddingsStrategy getStrategy() {
        return strategy;
    }

    /**
     * Sets the strategy used for the AI Agent for calculating embeddings.
     * @param strategy The strategy used for the AI Agent for calculating embeddings.
     */
    public void setStrategy(BoxAIAgentEmbeddingsStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    void parseJSONMember(JsonObject.Member member) {
        super.parseJSONMember(member);
        String memberName = member.getName();
        JsonValue value = member.getValue();
        switch (memberName) {
            case "model":
                this.model = member.getValue().asString();
                break;
            case "strategy":
                this.strategy = new BoxAIAgentEmbeddingsStrategy(value.asObject());
                break;
            default:
                break;
        }
    }

    public JsonObject getJSONObject() {
        JsonObject jsonObject = new JsonObject();
        JsonUtils.addIfNotNull(jsonObject, "model", this.model);
        if (this.strategy != null) {
            jsonObject.add("strategy", this.strategy.getJSONObject());
        }
        return jsonObject;
    }
}
