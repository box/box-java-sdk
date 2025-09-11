package com.box.sdkgen.schemas.aidialoguehistory;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.OffsetDateTime;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class AiDialogueHistory extends SerializableObject {

  protected String prompt;

  protected String answer;

  @JsonProperty("created_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime createdAt;

  public AiDialogueHistory() {
    super();
  }

  protected AiDialogueHistory(Builder builder) {
    super();
    this.prompt = builder.prompt;
    this.answer = builder.answer;
    this.createdAt = builder.createdAt;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getPrompt() {
    return prompt;
  }

  public String getAnswer() {
    return answer;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AiDialogueHistory casted = (AiDialogueHistory) o;
    return Objects.equals(prompt, casted.prompt)
        && Objects.equals(answer, casted.answer)
        && Objects.equals(createdAt, casted.createdAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(prompt, answer, createdAt);
  }

  @Override
  public String toString() {
    return "AiDialogueHistory{"
        + "prompt='"
        + prompt
        + '\''
        + ", "
        + "answer='"
        + answer
        + '\''
        + ", "
        + "createdAt='"
        + createdAt
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String prompt;

    protected String answer;

    protected OffsetDateTime createdAt;

    public Builder prompt(String prompt) {
      this.prompt = prompt;
      return this;
    }

    public Builder answer(String answer) {
      this.answer = answer;
      return this;
    }

    public Builder createdAt(OffsetDateTime createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public AiDialogueHistory build() {
      return new AiDialogueHistory(this);
    }
  }
}
