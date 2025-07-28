package com.box.sdkgen.schemas.aiextractstructuredresponse;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.schemas.aiagentinfo.AiAgentInfo;
import com.box.sdkgen.schemas.aiextractresponse.AiExtractResponse;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Date;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class AiExtractStructuredResponse extends SerializableObject {

  protected final AiExtractResponse answer;

  @JsonProperty("created_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected final Date createdAt;

  @JsonProperty("completion_reason")
  protected String completionReason;

  @JsonProperty("ai_agent_info")
  protected AiAgentInfo aiAgentInfo;

  public AiExtractStructuredResponse(
      @JsonProperty("answer") AiExtractResponse answer,
      @JsonProperty("created_at") Date createdAt) {
    super();
    this.answer = answer;
    this.createdAt = createdAt;
  }

  protected AiExtractStructuredResponse(Builder builder) {
    super();
    this.answer = builder.answer;
    this.createdAt = builder.createdAt;
    this.completionReason = builder.completionReason;
    this.aiAgentInfo = builder.aiAgentInfo;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public AiExtractResponse getAnswer() {
    return answer;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public String getCompletionReason() {
    return completionReason;
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
        && Objects.equals(aiAgentInfo, casted.aiAgentInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(answer, createdAt, completionReason, aiAgentInfo);
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
        + "aiAgentInfo='"
        + aiAgentInfo
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final AiExtractResponse answer;

    protected final Date createdAt;

    protected String completionReason;

    protected AiAgentInfo aiAgentInfo;

    public Builder(AiExtractResponse answer, Date createdAt) {
      super();
      this.answer = answer;
      this.createdAt = createdAt;
    }

    public Builder completionReason(String completionReason) {
      this.completionReason = completionReason;
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
