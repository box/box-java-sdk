package com.box.sdk;

import com.box.sdk.internal.utils.JsonUtils;
import com.eclipsesource.json.JsonObject;

/**
 * Represents the strategy used for the AI Agent for calculating embeddings.
 */
public class BoxAIAgentEmbeddingsStrategy extends BoxJSONObject {
    /**
     * The ID of the strategy used for the AI Agent for calculating embeddings.
     */
    private String id;
    /**
     * The number of tokens per chunk used for the AI Agent for calculating embeddings.
     */
    private int numTokensPerChunk;

    /**
     * Constructs an AI agent with default settings.
     * @param id The ID of the strategy used for the AI Agent for calculating embeddings.
     * @param numTokensPerChunk The number of tokens per chunk used for the AI Agent for calculating embeddings.
     */
    public BoxAIAgentEmbeddingsStrategy(String id, int numTokensPerChunk) {
        this.id = id;
        this.numTokensPerChunk = numTokensPerChunk;
    }

    /**
     * Constructs an AI agent with default settings.
     * @param jsonObject JSON object representing the AI agent.
     */
    public BoxAIAgentEmbeddingsStrategy(JsonObject jsonObject) {
        super(jsonObject);
    }

    /**
     * Gets the ID of the strategy used for the AI Agent for calculating embeddings.
     * @return The ID of the strategy used for the AI Agent for calculating embeddings.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the strategy used for the AI Agent for calculating embeddings.
     * @param id The ID of the strategy used for the AI Agent for calculating embeddings.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the number of tokens per chunk used for the AI Agent for calculating embeddings.
     * @return The number of tokens per chunk used for the AI Agent for calculating embeddings.
     */
    public int getNumTokensPerChunk() {
        return numTokensPerChunk;
    }

    /**
     * Sets the number of tokens per chunk used for the AI Agent for calculating embeddings.
     * @param numTokensPerChunk The number of tokens per chunk used for the AI Agent for calculating embeddings.
     */
    public void setNumTokensPerChunk(int numTokensPerChunk) {
        this.numTokensPerChunk = numTokensPerChunk;
    }

    @Override
    void parseJSONMember(JsonObject.Member member) {
        super.parseJSONMember(member);
        String memberName = member.getName();
        if (memberName.equals("id")) {
            this.id = member.getValue().asString();
        } else if (memberName.equals("num_tokens_per_chunk")) {
            this.numTokensPerChunk = member.getValue().asInt();
        }
    }

    public JsonObject getJSONObject() {
        JsonObject jsonObject = new JsonObject();
        JsonUtils.addIfNotNull(jsonObject, "id", this.id);
        JsonUtils.addIfNotNull(jsonObject, "num_tokens_per_chunk", this.numTokensPerChunk);
        return jsonObject;
    }
}
