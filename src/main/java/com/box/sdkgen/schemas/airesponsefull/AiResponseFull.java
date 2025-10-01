package com.box.sdkgen.schemas.airesponsefull;

import com.box.sdkgen.schemas.aiagentinfo.AiAgentInfo;
import com.box.sdkgen.schemas.aicitation.AiCitation;
import com.box.sdkgen.schemas.airesponse.AiResponse;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

/** AI ask response. */
@JsonFilter("nullablePropertyFilter")
public class AiResponseFull extends AiResponse {

  /** The citations of the LLM's answer reference. */
  protected List<AiCitation> citations;

  public AiResponseFull(
      @JsonProperty("answer") String answer, @JsonProperty("created_at") OffsetDateTime createdAt) {
    super(answer, createdAt);
  }

  protected AiResponseFull(Builder builder) {
    super(builder);
    this.citations = builder.citations;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public List<AiCitation> getCitations() {
    return citations;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AiResponseFull casted = (AiResponseFull) o;
    return Objects.equals(answer, casted.answer)
        && Objects.equals(createdAt, casted.createdAt)
        && Objects.equals(completionReason, casted.completionReason)
        && Objects.equals(aiAgentInfo, casted.aiAgentInfo)
        && Objects.equals(citations, casted.citations);
  }

  @Override
  public int hashCode() {
    return Objects.hash(answer, createdAt, completionReason, aiAgentInfo, citations);
  }

  @Override
  public String toString() {
    return "AiResponseFull{"
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
        + ", "
        + "citations='"
        + citations
        + '\''
        + "}";
  }

  public static class Builder extends AiResponse.Builder {

    protected List<AiCitation> citations;

    public Builder(String answer, OffsetDateTime createdAt) {
      super(answer, createdAt);
    }

    public Builder citations(List<AiCitation> citations) {
      this.citations = citations;
      return this;
    }

    @Override
    public Builder completionReason(String completionReason) {
      this.completionReason = completionReason;
      return this;
    }

    @Override
    public Builder aiAgentInfo(AiAgentInfo aiAgentInfo) {
      this.aiAgentInfo = aiAgentInfo;
      return this;
    }

    public AiResponseFull build() {
      return new AiResponseFull(this);
    }
  }
}
