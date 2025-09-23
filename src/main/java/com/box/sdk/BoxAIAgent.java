package com.box.sdk;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

public abstract class BoxAIAgent extends BoxJSONObject {
  /**
   * The type of the AI agent. Value can be "ai_agent_ask" or "ai_agent_text_gen",
   * "ai_agent_extract", "ai_agent_extract_structured".
   */
  private String type;

  /**
   * Constructs an AI agent with default settings.
   *
   * @param type The type of the AI agent. Value can be "ai_agent_ask", "ai_agent_text_gen",
   *     "ai_agent_extract", "ai_agent_extract_structured".
   */
  public BoxAIAgent(String type) {
    super();
    this.type = type;
  }

  /**
   * Constructs an AI agent with default settings.
   *
   * @param jsonObject JSON object representing the AI agent.
   */
  public BoxAIAgent(JsonObject jsonObject) {
    super(jsonObject);
  }

  /**
   * Constructs an AI agent with default settings.
   *
   * @param jsonObject JSON object representing the AI agent.
   */
  public static BoxAIAgent parse(JsonObject jsonObject) {
    String type = jsonObject.get("type").asString();
    if (type.equals(BoxAIAgentAsk.TYPE)) {
      return new BoxAIAgentAsk(jsonObject);
    } else if (type.equals(BoxAIAgentTextGen.TYPE)) {
      return new BoxAIAgentTextGen(jsonObject);
    } else if (type.equals(BoxAIAgentExtract.TYPE)) {
      return new BoxAIAgentExtract(jsonObject);
    } else if (type.equals(BoxAIAgentExtractStructured.TYPE)) {
      return new BoxAIAgentExtractStructured(jsonObject);
    } else {
      throw new IllegalArgumentException("Invalid AI agent type: " + type);
    }
  }

  /**
   * Gets the type of the AI agent.
   *
   * @return The type of the AI agent.
   */
  public String getType() {
    return type;
  }

  /**
   * Sets the type of the AI agent.
   *
   * @param type The type of the AI agent.
   */
  public void setType(String type) {
    this.type = type;
  }

  @Override
  void parseJSONMember(JsonObject.Member member) {
    super.parseJSONMember(member);
    String memberName = member.getName();
    JsonValue value = member.getValue();
    if (memberName.equals("type")) {
      this.type = value.asString();
    }
  }

  public JsonObject getJSONObject() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("type", this.type);
    return jsonObject;
  }

  /** The type of the AI agent for asking questions. */
  public enum Mode {
    /** The type of AI agent used to handle queries. */
    ASK("ask"),
    /** The type of AI agent used for generating text. */
    TEXT_GEN("text_gen"),
    /** The type of AI agent used for extracting metadata freeform. */
    EXTRACT("extract"),
    /** The type of AI agent used for extracting metadata structured. */
    EXTRACT_STRUCTURED("extract_structured");

    private final String value;

    Mode(String value) {
      this.value = value;
    }

    static BoxAIAgent.Mode fromJSONValue(String value) {
      if (value.equals("ask")) {
        return ASK;
      } else if (value.equals("text_gen")) {
        return TEXT_GEN;
      } else if (value.equals("extract")) {
        return EXTRACT;
      } else if (value.equals("extract_structured")) {
        return EXTRACT_STRUCTURED;
      } else {
        throw new IllegalArgumentException("Invalid AI agent mode: " + value);
      }
    }

    String toJSONValue() {
      return this.value;
    }

    @Override
    public String toString() {
      return this.value;
    }
  }
}
