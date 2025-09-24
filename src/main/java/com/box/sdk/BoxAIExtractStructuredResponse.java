package com.box.sdk;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import java.text.ParseException;
import java.util.Date;

/** AI response to a user request. */
public class BoxAIExtractStructuredResponse extends BoxJSONObject {
  private final JsonObject sourceJson;
  private JsonObject answer;
  private String completionReason;
  private Date createdAt;

  /** Constructs a BoxAIResponse object. */
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

  /**
   * Gets the answer of the AI.
   *
   * @return the answer of the AI.
   */
  public JsonObject getAnswer() {
    return answer;
  }

  /**
   * Gets reason the response finishes.
   *
   * @return the reason the response finishes.
   */
  public String getCompletionReason() {
    return completionReason;
  }

  /**
   * Gets the ISO date formatted timestamp of when the answer to the prompt was created.
   *
   * @return The ISO date formatted timestamp of when the answer to the prompt was created.
   */
  public Date getCreatedAt() {
    return createdAt;
  }

  /** {@inheritDoc} */
  @Override
  void parseJSONMember(JsonObject.Member member) {
    JsonValue value = member.getValue();
    String memberName = member.getName();
    try {
      if (memberName.equals("answer")) {
        this.answer = value.asObject();
      } else if (memberName.equals("completion_reason")) {
        this.completionReason = value.asString();
      } else if (memberName.equals("created_at")) {
        this.createdAt = BoxDateFormat.parse(value.asString());
      }
    } catch (ParseException e) {
      assert false : "A ParseException indicates a bug in the SDK.";
    }
  }
}
