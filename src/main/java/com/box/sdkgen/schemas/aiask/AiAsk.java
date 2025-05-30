package com.box.sdkgen.schemas.aiask;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.aiagentaskoraiagentreference.AiAgentAskOrAiAgentReference;
import com.box.sdkgen.schemas.aidialoguehistory.AiDialogueHistory;
import com.box.sdkgen.schemas.aiitemask.AiItemAsk;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Objects;

public class AiAsk extends SerializableObject {

  @JsonDeserialize(using = AiAskModeField.AiAskModeFieldDeserializer.class)
  @JsonSerialize(using = AiAskModeField.AiAskModeFieldSerializer.class)
  protected final EnumWrapper<AiAskModeField> mode;

  protected final String prompt;

  protected final List<AiItemAsk> items;

  @JsonProperty("dialogue_history")
  protected List<AiDialogueHistory> dialogueHistory;

  @JsonProperty("include_citations")
  protected Boolean includeCitations;

  @JsonProperty("ai_agent")
  protected AiAgentAskOrAiAgentReference aiAgent;

  public AiAsk(
      @JsonProperty("mode") EnumWrapper<AiAskModeField> mode,
      @JsonProperty("prompt") String prompt,
      @JsonProperty("items") List<AiItemAsk> items) {
    super();
    this.mode = mode;
    this.prompt = prompt;
    this.items = items;
  }

  public AiAsk(AiAskModeField mode, String prompt, List<AiItemAsk> items) {
    super();
    this.mode = new EnumWrapper<AiAskModeField>(mode);
    this.prompt = prompt;
    this.items = items;
  }

  protected AiAsk(AiAskBuilder builder) {
    super();
    this.mode = builder.mode;
    this.prompt = builder.prompt;
    this.items = builder.items;
    this.dialogueHistory = builder.dialogueHistory;
    this.includeCitations = builder.includeCitations;
    this.aiAgent = builder.aiAgent;
  }

  public EnumWrapper<AiAskModeField> getMode() {
    return mode;
  }

  public String getPrompt() {
    return prompt;
  }

  public List<AiItemAsk> getItems() {
    return items;
  }

  public List<AiDialogueHistory> getDialogueHistory() {
    return dialogueHistory;
  }

  public Boolean getIncludeCitations() {
    return includeCitations;
  }

  public AiAgentAskOrAiAgentReference getAiAgent() {
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
    AiAsk casted = (AiAsk) o;
    return Objects.equals(mode, casted.mode)
        && Objects.equals(prompt, casted.prompt)
        && Objects.equals(items, casted.items)
        && Objects.equals(dialogueHistory, casted.dialogueHistory)
        && Objects.equals(includeCitations, casted.includeCitations)
        && Objects.equals(aiAgent, casted.aiAgent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mode, prompt, items, dialogueHistory, includeCitations, aiAgent);
  }

  @Override
  public String toString() {
    return "AiAsk{"
        + "mode='"
        + mode
        + '\''
        + ", "
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
        + "includeCitations='"
        + includeCitations
        + '\''
        + ", "
        + "aiAgent='"
        + aiAgent
        + '\''
        + "}";
  }

  public static class AiAskBuilder {

    protected final EnumWrapper<AiAskModeField> mode;

    protected final String prompt;

    protected final List<AiItemAsk> items;

    protected List<AiDialogueHistory> dialogueHistory;

    protected Boolean includeCitations;

    protected AiAgentAskOrAiAgentReference aiAgent;

    public AiAskBuilder(EnumWrapper<AiAskModeField> mode, String prompt, List<AiItemAsk> items) {
      this.mode = mode;
      this.prompt = prompt;
      this.items = items;
    }

    public AiAskBuilder(AiAskModeField mode, String prompt, List<AiItemAsk> items) {
      this.mode = new EnumWrapper<AiAskModeField>(mode);
      this.prompt = prompt;
      this.items = items;
    }

    public AiAskBuilder dialogueHistory(List<AiDialogueHistory> dialogueHistory) {
      this.dialogueHistory = dialogueHistory;
      return this;
    }

    public AiAskBuilder includeCitations(Boolean includeCitations) {
      this.includeCitations = includeCitations;
      return this;
    }

    public AiAskBuilder aiAgent(AiAgentAskOrAiAgentReference aiAgent) {
      this.aiAgent = aiAgent;
      return this;
    }

    public AiAsk build() {
      return new AiAsk(this);
    }
  }
}
