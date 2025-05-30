package com.box.sdkgen.schemas.aiextractstructuredresponse;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.aiagentinfo.AiAgentInfo;
import com.box.sdkgen.schemas.aiextractresponse.AiExtractResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class AiExtractStructuredResponse extends SerializableObject {

  protected final AiExtractResponse answer;

  @JsonProperty("created_at")
  protected final String createdAt;

  @JsonProperty("completion_reason")
  protected String completionReason;

  @JsonProperty("ai_agent_info")
  protected AiAgentInfo aiAgentInfo;

  public AiExtractStructuredResponse(
      @JsonProperty("answer") AiExtractResponse answer,
      @JsonProperty("created_at") String createdAt) {
    super();
    this.answer = answer;
    this.createdAt = createdAt;
  }

  protected AiExtractStructuredResponse(AiExtractStructuredResponseBuilder builder) {
    super();
    this.answer = builder.answer;
    this.createdAt = builder.createdAt;
    this.completionReason = builder.completionReason;
    this.aiAgentInfo = builder.aiAgentInfo;
  }

  public AiExtractResponse getAnswer() {
    return answer;
  }

  public String getCreatedAt() {
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

  public static class AiExtractStructuredResponseBuilder {

    protected final AiExtractResponse answer;

    protected final String createdAt;

    protected String completionReason;

    protected AiAgentInfo aiAgentInfo;

    public AiExtractStructuredResponseBuilder(AiExtractResponse answer, String createdAt) {
      this.answer = answer;
      this.createdAt = createdAt;
    }

    public AiExtractStructuredResponseBuilder completionReason(String completionReason) {
      this.completionReason = completionReason;
      return this;
    }

    public AiExtractStructuredResponseBuilder aiAgentInfo(AiAgentInfo aiAgentInfo) {
      this.aiAgentInfo = aiAgentInfo;
      return this;
    }

    public AiExtractStructuredResponse build() {
      return new AiExtractStructuredResponse(this);
    }
  }
}
