package com.box.sdk;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import java.text.ParseException;
import java.util.Date;

/** AI response to a user request. */
public class BoxAIResponse extends BoxJSONObject {
  private String answer;
  private String completionReason;
  private Date createdAt;

  /** Constructs a BoxAIResponse object. */
  public BoxAIResponse(String answer, String completionReason, Date createdAt) {
    super();
    this.answer = answer;
    this.completionReason = completionReason;
    this.createdAt = createdAt;
  }

  /**
   * Constructs a BoxAIResponse from a JSON string.
   *
   * @param json the JSON encoded upload email.
   */
  public BoxAIResponse(String json) {
    super(json);
  }

  /**
   * Constructs an BoxAIResponse object using an already parsed JSON object.
   *
   * @param jsonObject the parsed JSON object.
   */
  BoxAIResponse(JsonObject jsonObject) {
    super(jsonObject);
  }

  /**
   * Gets the answer of the AI.
   *
   * @return the answer of the AI.
   */
  public String getAnswer() {
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
        this.answer = value.asString();
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
