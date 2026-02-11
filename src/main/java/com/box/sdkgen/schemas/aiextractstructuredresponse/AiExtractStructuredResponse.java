package com.box.sdkgen.schemas.aiextractstructuredresponse;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.schemas.aiagentinfo.AiAgentInfo;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Objects;

/** AI extract structured response. */
@JsonFilter("nullablePropertyFilter")
public class AiExtractStructuredResponse extends SerializableObject {

  protected final Map<String, Object> answer;

  /** The ISO date formatted timestamp of when the answer to the prompt was created. */
  @JsonProperty("created_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected final OffsetDateTime createdAt;

  /** The reason the response finishes. */
  @JsonProperty("completion_reason")
  protected String completionReason;

  /**
   * The confidence score levels and numeric values for each extracted field as a JSON dictionary.
   * This can be empty if no field could be extracted.
   */
  @JsonProperty("confidence_score")
  protected Map<String, Object> confidenceScore;

  @JsonProperty("ai_agent_info")
  protected AiAgentInfo aiAgentInfo;

  public AiExtractStructuredResponse(
      @JsonProperty("answer") Map<String, Object> answer,
      @JsonProperty("created_at") OffsetDateTime createdAt) {
    super();
    this.answer = answer;
    this.createdAt = createdAt;
  }

  protected AiExtractStructuredResponse(Builder builder) {
    super();
    this.answer = builder.answer;
    this.createdAt = builder.createdAt;
    this.completionReason = builder.completionReason;
    this.confidenceScore = builder.confidenceScore;
    this.aiAgentInfo = builder.aiAgentInfo;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public Map<String, Object> getAnswer() {
    return answer;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public String getCompletionReason() {
    return completionReason;
  }

  public Map<String, Object> getConfidenceScore() {
    return confidenceScore;
  }

  public AiAgentInfo getAiAgentInfo() {
    return aiAgentInfo;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AiExtractStructuredResponse casted = (AiExtractStructuredResponse) o;
    return Objects.equals(answer, casted.answer)
        && Objects.equals(createdAt, casted.createdAt)
        && Objects.equals(completionReason, casted.completionReason)
        && Objects.equals(confidenceScore, casted.confidenceScore)
        && Objects.equals(aiAgentInfo, casted.aiAgentInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(answer, createdAt, completionReason, confidenceScore, aiAgentInfo);
  }

  @Override
  public String toString() {
    return "AiExtractStructuredResponse{"
        + "answer='"
        + answer
        + '\''
        + ", "
        + "createdAt='"
        + createdAt
        + '\''
        + ", "
        + "completionReason='"
        + completionReason
        + '\''
        + ", "
        + "confidenceScore='"
        + confidenceScore
        + '\''
        + ", "
        + "aiAgentInfo='"
        + aiAgentInfo
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final Map<String, Object> answer;

    protected final OffsetDateTime createdAt;

    protected String completionReason;

    protected Map<String, Object> confidenceScore;

    protected AiAgentInfo aiAgentInfo;

    public Builder(Map<String, Object> answer, OffsetDateTime createdAt) {
      super();
      this.answer = answer;
      this.createdAt = createdAt;
    }

    public Builder completionReason(String completionReason) {
      this.completionReason = completionReason;
      return this;
    }

    public Builder confidenceScore(Map<String, Object> confidenceScore) {
      this.confidenceScore = confidenceScore;
      return this;
    }

    public Builder aiAgentInfo(AiAgentInfo aiAgentInfo) {
      this.aiAgentInfo = aiAgentInfo;
      return this;
    }

    public AiExtractStructuredResponse build() {
      return new AiExtractStructuredResponse(this);
    }
  }
}
