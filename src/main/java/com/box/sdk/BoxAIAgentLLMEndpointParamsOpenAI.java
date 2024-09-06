package com.box.sdk;

import com.box.sdk.internal.utils.JsonUtils;
import com.eclipsesource.json.JsonObject;

/**
 * Represents the AI LLM endpoint params OpenAI object.
 */
public class BoxAIAgentLLMEndpointParamsOpenAI extends BoxAIAgentLLMEndpointParams {

    /**
     * The type of the LLM endpoint parameters.
     */
    public static final String TYPE = "openai_params";

    /**
     * Number between -2.0 and 2.0. Positive values penalize new tokens based on their existing frequency in the text
     * so far, decreasing the model's likelihood to repeat the same line verbatim.
     */
    private double frequencyPenalty;
    /**
     * Number between -2.0 and 2.0. Positive values penalize new tokens based on whether they appear in the text so far,
     * increasing the model's likelihood to talk about new topics.
     */
    private double presencePenalty;
    /**
     * Up to 4 sequences where the API will stop generating further tokens.
     */
    private String stop;
    /**
     * What sampling temperature to use, between 0 and 2. Higher values like 0.8 will make the output more random,
     * while lower values like 0.2 will make it more focused and deterministic.
     * We generally recommend altering this or top_p but not both.
     */
    private double temperature;
    /**
     * An alternative to sampling with temperature, called nucleus sampling, where the model considers the results of
     * the tokens with top_p probability mass. So 0.1 means only the tokens comprising the top 10% probability mass
     * are considered. We generally recommend altering this or temperature but not both.
     */
    private double topP;

    /**
     * Constructs an AI agent with default settings.
     * @param frequencyPenalty Number between -2.0 and 2.0. Positive values penalize new tokens based on their existing frequency in the text
     *                         so far, decreasing the model's likelihood to repeat the same line verbatim.
     * @param presencePenalty Number between -2.0 and 2.0. Positive values penalize new tokens based on whether they appear in the text so far,
     *                        increasing the model's likelihood to talk about new topics.
     * @param stop Up to 4 sequences where the API will stop generating further tokens.
     * @param temperature What sampling temperature to use, between 0 and 2. Higher values like 0.8 will make the output more random,
     *                   while lower values like 0.2 will make it more focused and deterministic.
     *                   We generally recommend altering this or top_p but not both.
     * @param topP An alternative to sampling with temperature, called nucleus sampling, where the model considers the results of
     *             the tokens with top_p probability mass. So 0.1 means only the tokens comprising the top 10% probability mass
     *             are considered. We generally recommend altering this or temperature but not both.
     */
    public BoxAIAgentLLMEndpointParamsOpenAI(double frequencyPenalty,
                                             double presencePenalty,
                                             String stop,
                                             double temperature,
                                             double topP) {
        super(TYPE);
        this.frequencyPenalty = frequencyPenalty;
        this.presencePenalty = presencePenalty;
        this.stop = stop;
        this.temperature = temperature;
        this.topP = topP;
    }

    /**
     * Constructs an AI agent with default settings.
     * @param jsonObject JSON object representing the AI agent.
     */
    public BoxAIAgentLLMEndpointParamsOpenAI(JsonObject jsonObject) {
        super(jsonObject);
    }

    /**
     * Gets the frequency penalty.
     * @return The frequency penalty.
     */
    public double getFrequencyPenalty() {
        return frequencyPenalty;
    }

    /**
     * Sets the frequency penalty.
     * @param frequencyPenalty The frequency penalty.
     */
    public void setFrequencyPenalty(double frequencyPenalty) {
        this.frequencyPenalty = frequencyPenalty;
    }

    /**
     * Gets the presence penalty.
     * @return The presence penalty.
     */
    public double getPresencePenalty() {
        return presencePenalty;
    }

    /**
     * Sets the presence penalty.
     * @param presencePenalty The presence penalty.
     */
    public void setPresencePenalty(double presencePenalty) {
        this.presencePenalty = presencePenalty;
    }

    /**
     * Gets the stop.
     * @return The stop.
     */
    public String getStop() {
        return stop;
    }

    /**
     * Sets the stop.
     * @param stop The stop.
     */
    public void setStop(String stop) {
        this.stop = stop;
    }

    /**
     * Gets the temperature.
     * @return The temperature.
     */
    public double getTemperature() {
        return temperature;
    }

    /**
     * Sets the temperature.
     * @param temperature The temperature.
     */
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    /**
     * Gets the top-P.
     * @return The top-P.
     */
    public double getTopP() {
        return topP;
    }

    /**
     * Sets the top-P.
     * @param topP The top-P.
     */
    public void setTopP(double topP) {
        this.topP = topP;
    }

    @Override
    void parseJSONMember(JsonObject.Member member) {
        super.parseJSONMember(member);
        String memberName = member.getName();
        switch (memberName) {
            case "frequency_penalty":
                this.frequencyPenalty = member.getValue().asDouble();
                break;
            case "presence_penalty":
                this.presencePenalty = member.getValue().asDouble();
                break;
            case "stop":
                this.stop = member.getValue().asString();
                break;
            case "temperature":
                this.temperature = member.getValue().asDouble();
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
        JsonUtils.addIfNotNull(jsonObject, "frequency_penalty", this.frequencyPenalty);
        JsonUtils.addIfNotNull(jsonObject, "presence_penalty", this.presencePenalty);
        JsonUtils.addIfNotNull(jsonObject, "stop", this.stop);
        JsonUtils.addIfNotNull(jsonObject, "temperature", this.temperature);
        JsonUtils.addIfNotNull(jsonObject, "top_p", this.topP);
        return jsonObject;
    }
}
