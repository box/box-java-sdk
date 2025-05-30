package com.box.sdkgen.schemas.aitextgen;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.aiagentreferenceoraiagenttextgen.AiAgentReferenceOrAiAgentTextGen;
import com.box.sdkgen.schemas.aidialoguehistory.AiDialogueHistory;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class AiTextGen extends SerializableObject {

  protected final String prompt;

  protected final List<AiTextGenItemsField> items;

  @JsonProperty("dialogue_history")
  protected List<AiDialogueHistory> dialogueHistory;

  @JsonProperty("ai_agent")
  protected AiAgentReferenceOrAiAgentTextGen aiAgent;

  public AiTextGen(
      @JsonProperty("prompt") String prompt,
      @JsonProperty("items") List<AiTextGenItemsField> items) {
    super();
    this.prompt = prompt;
    this.items = items;
  }

  protected AiTextGen(AiTextGenBuilder builder) {
    super();
    this.prompt = builder.prompt;
    this.items = builder.items;
    this.dialogueHistory = builder.dialogueHistory;
    this.aiAgent = builder.aiAgent;
  }

  public String getPrompt() {
    return prompt;
  }

  public List<AiTextGenItemsField> getItems() {
    return items;
  }

  public List<AiDialogueHistory> getDialogueHistory() {
    return dialogueHistory;
  }

  public AiAgentReferenceOrAiAgentTextGen getAiAgent() {
    return aiAgent;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AiTextGen casted = (AiTextGen) o;
    return Objects.equals(prompt, casted.prompt)
        && Objects.equals(items, casted.items)
        && Objects.equals(dialogueHistory, casted.dialogueHistory)
        && Objects.equals(aiAgent, casted.aiAgent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(prompt, items, dialogueHistory, aiAgent);
  }

  @Override
  public String toString() {
    return "AiTextGen{"
        + "prompt='"
        + prompt
        + '\''
        + ", "
        + "items='"
        + items
        + '\''
        + ", "
        + "dialogueHistory='"
        + dialogueHistory
        + '\''
        + ", "
        + "aiAgent='"
        + aiAgent
        + '\''
        + "}";
  }

  public static class AiTextGenBuilder {

    protected final String prompt;

    protected final List<AiTextGenItemsField> items;

    protected List<AiDialogueHistory> dialogueHistory;

    protected AiAgentReferenceOrAiAgentTextGen aiAgent;

    public AiTextGenBuilder(String prompt, List<AiTextGenItemsField> items) {
      this.prompt = prompt;
      this.items = items;
    }

    public AiTextGenBuilder dialogueHistory(List<AiDialogueHistory> dialogueHistory) {
      this.dialogueHistory = dialogueHistory;
      return this;
    }

    public AiTextGenBuilder aiAgent(AiAgentReferenceOrAiAgentTextGen aiAgent) {
      this.aiAgent = aiAgent;
      return this;
    }

    public AiTextGen build() {
      return new AiTextGen(this);
    }
  }
}
