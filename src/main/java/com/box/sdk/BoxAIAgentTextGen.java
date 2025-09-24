package com.box.sdk;

import com.box.sdk.internal.utils.JsonUtils;
import com.eclipsesource.json.JsonObject;

/** Represents an AI Agent used for generating text. */
@BoxResourceType("ai_agent_text_gen")
public class BoxAIAgentTextGen extends BoxAIAgent {

  /** The type of the AI Agent for generating text. */
  public static final String TYPE = "ai_agent_text_gen";

  /** The basic generator used for the AI Agent for generating text. */
  private BoxAIAgentTextGenBasicGen basicGen;

  /**
   * Constructs an AI agent with default settings.
   *
   * @param basicGen The basic generator used for the AI Agent for generating text.
   */
  public BoxAIAgentTextGen(BoxAIAgentTextGenBasicGen basicGen) {
    super(TYPE);
    this.basicGen = basicGen;
  }

  /**
   * Constructs an AI agent with default settings.
   *
   * @param jsonObject JSON object representing the AI agent.
   */
  public BoxAIAgentTextGen(JsonObject jsonObject) {
    super(jsonObject);
  }

  /**
   * Gets the basic generator used for the AI Agent for generating text.
   *
   * @return The basic generator used for the AI Agent for generating text.
   */
  public BoxAIAgentTextGenBasicGen getBasicGen() {
    return basicGen;
  }

  /**
   * Sets the basic generator used for the AI Agent for generating text.
   *
   * @param basicGen The basic generator used for the AI Agent for generating text.
   */
  public void setBasicGen(BoxAIAgentTextGenBasicGen basicGen) {
    this.basicGen = basicGen;
  }

  @Override
  void parseJSONMember(JsonObject.Member member) {
    super.parseJSONMember(member);
    String memberName = member.getName();
    if (memberName.equals("basic_gen")) {
      this.basicGen = new BoxAIAgentTextGenBasicGen(member.getValue().asObject());
    }
  }

  public JsonObject getJSONObject() {
    JsonObject jsonObject = new JsonObject();
    JsonUtils.addIfNotNull(jsonObject, "type", this.getType());
    if (this.basicGen != null) {
      jsonObject.add("basic_gen", this.basicGen.getJSONObject());
    }
    return jsonObject;
  }
}
