package com.box.sdk;

import com.box.sdk.internal.utils.JsonUtils;
import com.eclipsesource.json.JsonObject;

/** The parameters for the LLM endpoint specific to OpenAI / Google models. */
public class BoxAIAgentLLMEndpointParams extends BoxJSONObject {
  /** The type of the LLM endpoint parameters. Value can be "google_params" or "openai_params". */
  private String type;

  /** Constructs LLM endpoint parameters with default settings. */
  public BoxAIAgentLLMEndpointParams(String type) {
    super();
    this.type = type;
  }

  /**
   * Constructs LLM endpoint parameters with default settings.
   *
   * @param jsonObject JSON object representing the LLM endpoint parameters.
   */
  public BoxAIAgentLLMEndpointParams(JsonObject jsonObject) {
    super(jsonObject);
  }

  /**
   * Parses a JSON object representing LLM endpoint parameters.
   *
   * @param jsonObject JSON object representing the LLM endpoint parameters.
   * @return The LLM endpoint parameters parsed from the JSON object.
   */
  public static BoxAIAgentLLMEndpointParams parse(JsonObject jsonObject) {
    String type = jsonObject.get("type").asString();
    switch (type) {
      case BoxAIAgentLLMEndpointParamsGoogle.TYPE:
        return new BoxAIAgentLLMEndpointParamsGoogle(jsonObject);
      case BoxAIAgentLLMEndpointParamsOpenAI.TYPE:
        return new BoxAIAgentLLMEndpointParamsOpenAI(jsonObject);
      default:
        throw new IllegalArgumentException("Invalid LLM endpoint params type: " + type);
    }
  }

  /**
   * Gets the type of the LLM endpoint parameters.
   *
   * @return The type of the LLM endpoint parameters.
   */
  public String getType() {
    return type;
  }

  /**
   * Sets the type of the LLM endpoint parameters.
   *
   * @param type The type of the LLM endpoint parameters.
   */
  public void setType(String type) {
    this.type = type;
  }

  @Override
  void parseJSONMember(JsonObject.Member member) {
    super.parseJSONMember(member);
    String memberName = member.getName();
    if (memberName.equals("type")) {
      this.type = member.getValue().asString();
    }
  }

  public JsonObject getJSONObject() {
    JsonObject jsonObject = new JsonObject();
    JsonUtils.addIfNotNull(jsonObject, "type", this.type);
    return jsonObject;
  }
}
