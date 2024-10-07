package com.box.sdk;

import com.box.sdk.internal.utils.JsonUtils;
import com.eclipsesource.json.JsonObject;

/**
 * Represents the AI LLM endpoint params Google object.
 */
public class BoxAIAgentLLMEndpointParamsGoogle extends BoxAIAgentLLMEndpointParams {

    /**
     * The type of the LLM endpoint parameters.
     */
    public static final String TYPE = "google_params";

    /**
     * The temperature is used for sampling during response generation, which occurs when top-P and top-K are applied.
     * Temperature controls the degree of randomness in token selection.
     */
    private Double temperature;
    /**
     * Top-K changes how the model selects tokens for output.
     * A top-K of 1 means the next selected token is the most probable among all tokens in the model's vocabulary
     * (also called greedy decoding), while a top-K of 3 means that the next token is selected from among the three
     * most probable tokens by using temperature.
     */
    private Integer topK;
    /**
     * Top-P changes how the model selects tokens for output.
     * Tokens are selected from the most (see top-K) to least probable until the sum of their probabilities equals the
     * top-P value.
     */
    private Double topP;

    /**
     * Constructs an AI agent with default settings.
     * @param temperature The temperature is used for sampling during response generation, which occurs when top-P and top-K are applied.
     *                    Temperature controls the degree of randomness in token selection.
     * @param topK Top-K changes how the model selects tokens for output.
     *             A top-K of 1 means the next selected token is the most probable among all tokens in the model's vocabulary
     *             (also called greedy decoding), while a top-K of 3 means that the next token is selected from among the three
     *             most probable tokens by using temperature.
     * @param topP Top-P changes how the model selects tokens for output.
     *             Tokens are selected from the most (see top-K) to least probable until the sum of their probabilities equals the
     *             top-P value.
     */
    public BoxAIAgentLLMEndpointParamsGoogle(Double temperature, Integer topK, Double topP) {
        super(TYPE);
        this.temperature = temperature;
        this.topK = topK;
        this.topP = topP;
    }

    /**
     * Constructs an AI agent with default settings.
     * @param jsonObject JSON object representing the AI agent.
     */
    public BoxAIAgentLLMEndpointParamsGoogle(JsonObject jsonObject) {
        super(jsonObject);
    }

    /**
     * Gets the temperature used for sampling during response generation, which occurs when top-P and top-K are applied.
     * @return The temperature used for sampling during response generation, which occurs when top-P and top-K are applied.
     */
    public Double getTemperature() {
        return temperature;
    }

    /**
     * Sets the temperature used for sampling during response generation, which occurs when top-P and top-K are applied.
     * @param temperature The temperature used for sampling during response generation, which occurs when top-P and top-K are applied.
     */
    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    /**
     * Gets the top-K value.
     * @return The top-K value.
     */
    public Integer getTopK() {
        return topK;
    }

    /**
     * Sets the top-K value.
     * @param topK The top-K value.
     */
    public void setTopK(Integer topK) {
        this.topK = topK;
    }

    /**
     * Gets the top-P value.
     * @return The top-P value.
     */
    public Double getTopP() {
        return topP;
    }

    /**
     * Sets the top-P value.
     * @param topP The top-P value.
     */
    public void setTopP(Double topP) {
        this.topP = topP;
    }

    @Override
    void parseJSONMember(JsonObject.Member member) {
        super.parseJSONMember(member);
        String memberName = member.getName();
        switch (memberName) {
            case "temperature":
                this.temperature = member.getValue().asDouble();
                break;
            case "top_k":
                this.topK = member.getValue().asInt();
                break;
            case "top_p":
                this.topP = member.getValue().asDouble();
                break;
            default:
                break;
        }
    }

    public JsonObject getJSONObject() {
        JsonObject jsonObject = new JsonObject();
        JsonUtils.addIfNotNull(jsonObject, "type", this.getType());
        JsonUtils.addIfNotNull(jsonObject, "temperature", this.temperature);
        JsonUtils.addIfNotNull(jsonObject, "top_k", this.topK);
        JsonUtils.addIfNotNull(jsonObject, "top_p", this.topP);
        return jsonObject;
    }
}
