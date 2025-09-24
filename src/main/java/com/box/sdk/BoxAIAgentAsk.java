package com.box.sdk;

import com.box.sdk.internal.utils.JsonUtils;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/** Represents an AI Agent used to handle queries. */
@BoxResourceType("ai_agent_ask")
public class BoxAIAgentAsk extends BoxAIAgent {

  /** The type of the AI Ask agent. */
  public static final String TYPE = "ai_agent_ask";

  /** AI agent tool used to handle basic text. */
  private BoxAIAgentAskBasicText basicText;
  /** AI agent tool used to handle basic text. */
  private BoxAIAgentAskBasicText basicTextMulti;
  /** AI agent tool used to handle longer text. */
  private BoxAIAgentAskLongText longText;
  /** AI agent tool used to handle longer text. */
  private BoxAIAgentAskLongText longTextMulti;

  /**
   * Constructs an AI agent with default settings.
   *
   * @param basicText AI agent tool used to handle basic text.
   * @param basicTextMulti AI agent tool used to handle basic text.
   * @param longText AI agent tool used to handle longer text.
   * @param longTextMulti AI agent tool used to handle longer text.
   */
  public BoxAIAgentAsk(
      BoxAIAgentAskBasicText basicText,
      BoxAIAgentAskBasicText basicTextMulti,
      BoxAIAgentAskLongText longText,
      BoxAIAgentAskLongText longTextMulti) {
    super(TYPE);
    this.basicText = basicText;
    this.basicTextMulti = basicTextMulti;
    this.longText = longText;
    this.longTextMulti = longTextMulti;
  }

  /**
   * Constructs an AI agent with default settings.
   *
   * @param jsonObject JSON object representing the AI agent.
   */
  public BoxAIAgentAsk(JsonObject jsonObject) {
    super(jsonObject);
  }

  /**
   * Gets the AI agent tool used to handle basic text.
   *
   * @return The AI agent tool used to handle basic text.
   */
  public BoxAIAgentAskBasicText getBasicText() {
    return basicText;
  }

  /**
   * Sets the AI agent tool used to handle basic text.
   *
   * @param basicText The AI agent tool used to handle basic text.
   */
  public void setBasicText(BoxAIAgentAskBasicText basicText) {
    this.basicText = basicText;
  }

  /**
   * Gets the AI agent tool used to handle basic text.
   *
   * @return The AI agent tool used to handle basic text.
   */
  public BoxAIAgentAskBasicText getBasicTextMulti() {
    return basicTextMulti;
  }

  /**
   * Sets the AI agent tool used to handle basic text.
   *
   * @param basicTextMulti The AI agent tool used to handle basic text.
   */
  public void setBasicTextMulti(BoxAIAgentAskBasicText basicTextMulti) {
    this.basicTextMulti = basicTextMulti;
  }

  /**
   * Gets the AI agent tool used to handle longer text.
   *
   * @return The AI agent tool used to handle longer text.
   */
  public BoxAIAgentAskLongText getLongText() {
    return longText;
  }

  /**
   * Sets the AI agent tool used to handle longer text.
   *
   * @param longText The AI agent tool used to handle longer text.
   */
  public void setLongText(BoxAIAgentAskLongText longText) {
    this.longText = longText;
  }

  /**
   * Gets the AI agent tool used to handle longer text.
   *
   * @return The AI agent tool used to handle longer text.
   */
  public BoxAIAgentAskLongText getLongTextMulti() {
    return longTextMulti;
  }

  /**
   * Sets the AI agent tool used to handle longer text.
   *
   * @param longTextMulti The AI agent tool used to handle longer text.
   */
  public void setLongTextMulti(BoxAIAgentAskLongText longTextMulti) {
    this.longTextMulti = longTextMulti;
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
        case "basic_text_multi":
          this.basicTextMulti = new BoxAIAgentAskBasicText(memberValue.asObject());
          break;
        case "long_text":
          this.longText = new BoxAIAgentAskLongText(memberValue.asObject());
          break;
        case "long_text_multi":
          this.longTextMulti = new BoxAIAgentAskLongText(memberValue.asObject());
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
    if (this.basicText != null) {
      jsonObject.add("basic_text", this.basicText.getJSONObject());
    }
    if (this.basicTextMulti != null) {
      jsonObject.add("basic_text_multi", this.basicTextMulti.getJSONObject());
    }
    if (this.longText != null) {
      jsonObject.add("long_text", this.longText.getJSONObject());
    }
    if (this.longTextMulti != null) {
      jsonObject.add("long_text_multi", this.longTextMulti.getJSONObject());
    }
    return jsonObject;
  }
}
