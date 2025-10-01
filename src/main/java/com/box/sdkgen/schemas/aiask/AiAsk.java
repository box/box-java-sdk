package com.box.sdkgen.schemas.aiask;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.aiagentask.AiAgentAsk;
import com.box.sdkgen.schemas.aiagentreference.AiAgentReference;
import com.box.sdkgen.schemas.aiaskagent.AiAskAgent;
import com.box.sdkgen.schemas.aidialoguehistory.AiDialogueHistory;
import com.box.sdkgen.schemas.aiitemask.AiItemAsk;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Objects;

/** AI ask request object. */
@JsonFilter("nullablePropertyFilter")
public class AiAsk extends SerializableObject {

  /**
   * Box AI handles text documents with text representations up to 1MB in size, or a maximum of 25
   * files, whichever comes first. If the text file size exceeds 1MB, the first 1MB of text
   * representation will be processed. Box AI handles image documents with a resolution of 1024 x
   * 1024 pixels, with a maximum of 5 images or 5 pages for multi-page images. If the number of
   * image or image pages exceeds 5, the first 5 images or pages will be processed. If you set mode
   * parameter to `single_item_qa`, the items array can have one element only. Currently Box AI does
   * not support multi-modal requests. If both images and text are sent Box AI will only process the
   * text.
   */
  @JsonDeserialize(using = AiAskModeField.AiAskModeFieldDeserializer.class)
  @JsonSerialize(using = AiAskModeField.AiAskModeFieldSerializer.class)
  protected final EnumWrapper<AiAskModeField> mode;

  /**
   * The prompt provided by the client to be answered by the LLM. The prompt's length is limited to
   * 10000 characters.
   */
  protected final String prompt;

  /** The items to be processed by the LLM, often files. */
  protected final List<AiItemAsk> items;

  /**
   * The history of prompts and answers previously passed to the LLM. This provides additional
   * context to the LLM in generating the response.
   */
  @JsonProperty("dialogue_history")
  protected List<AiDialogueHistory> dialogueHistory;

  /** A flag to indicate whether citations should be returned. */
  @JsonProperty("include_citations")
  protected Boolean includeCitations;

  @JsonProperty("ai_agent")
  protected AiAskAgent aiAgent;

  public AiAsk(AiAskModeField mode, String prompt, List<AiItemAsk> items) {
    super();
    this.mode = new EnumWrapper<AiAskModeField>(mode);
    this.prompt = prompt;
    this.items = items;
  }

  public AiAsk(
      @JsonProperty("mode") EnumWrapper<AiAskModeField> mode,
      @JsonProperty("prompt") String prompt,
      @JsonProperty("items") List<AiItemAsk> items) {
    super();
    this.mode = mode;
    this.prompt = prompt;
    this.items = items;
  }

  protected AiAsk(Builder builder) {
    super();
    this.mode = builder.mode;
    this.prompt = builder.prompt;
    this.items = builder.items;
    this.dialogueHistory = builder.dialogueHistory;
    this.includeCitations = builder.includeCitations;
    this.aiAgent = builder.aiAgent;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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

  public AiAskAgent getAiAgent() {
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

  public static class Builder extends NullableFieldTracker {

    protected final EnumWrapper<AiAskModeField> mode;

    protected final String prompt;

    protected final List<AiItemAsk> items;

    protected List<AiDialogueHistory> dialogueHistory;

    protected Boolean includeCitations;

    protected AiAskAgent aiAgent;

    public Builder(AiAskModeField mode, String prompt, List<AiItemAsk> items) {
      super();
      this.mode = new EnumWrapper<AiAskModeField>(mode);
      this.prompt = prompt;
      this.items = items;
    }

    public Builder(EnumWrapper<AiAskModeField> mode, String prompt, List<AiItemAsk> items) {
      super();
      this.mode = mode;
      this.prompt = prompt;
      this.items = items;
    }

    public Builder dialogueHistory(List<AiDialogueHistory> dialogueHistory) {
      this.dialogueHistory = dialogueHistory;
      return this;
    }

    public Builder includeCitations(Boolean includeCitations) {
      this.includeCitations = includeCitations;
      return this;
    }

    public Builder aiAgent(AiAgentReference aiAgent) {
      this.aiAgent = new AiAskAgent(aiAgent);
      return this;
    }

    public Builder aiAgent(AiAgentAsk aiAgent) {
      this.aiAgent = new AiAskAgent(aiAgent);
      return this;
    }

    public Builder aiAgent(AiAskAgent aiAgent) {
      this.aiAgent = aiAgent;
      return this;
    }

    public AiAsk build() {
      return new AiAsk(this);
    }
  }
}
