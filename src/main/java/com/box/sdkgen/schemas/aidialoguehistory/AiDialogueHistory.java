package com.box.sdkgen.schemas.aidialoguehistory;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class AiDialogueHistory extends SerializableObject {

  protected String prompt;

  protected String answer;

  @JsonProperty("created_at")
  protected String createdAt;

  public AiDialogueHistory() {
    super();
  }

  protected AiDialogueHistory(AiDialogueHistoryBuilder builder) {
    super();
    this.prompt = builder.prompt;
    this.answer = builder.answer;
    this.createdAt = builder.createdAt;
  }

  public String getPrompt() {
    return prompt;
  }

  public String getAnswer() {
    return answer;
  }

  public String getCreatedAt() {
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

  public static class AiDialogueHistoryBuilder {

    protected String prompt;

    protected String answer;

    protected String createdAt;

    public AiDialogueHistoryBuilder prompt(String prompt) {
      this.prompt = prompt;
      return this;
    }

    public AiDialogueHistoryBuilder answer(String answer) {
      this.answer = answer;
      return this;
    }

    public AiDialogueHistoryBuilder createdAt(String createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public AiDialogueHistory build() {
      return new AiDialogueHistory(this);
    }
  }
}
