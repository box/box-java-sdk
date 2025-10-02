package com.box.sdkgen.schemas.aitextgen;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.aiagentreference.AiAgentReference;
import com.box.sdkgen.schemas.aiagenttextgen.AiAgentTextGen;
import com.box.sdkgen.schemas.aidialoguehistory.AiDialogueHistory;
import com.box.sdkgen.schemas.aitextgenagent.AiTextGenAgent;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

/** AI text gen request object. */
@JsonFilter("nullablePropertyFilter")
public class AiTextGen extends SerializableObject {

  /**
   * The prompt provided by the client to be answered by the LLM. The prompt's length is limited to
   * 10000 characters.
   */
  protected final String prompt;

  /**
   * The items to be processed by the LLM, often files. The array can include **exactly one**
   * element.
   *
   * <p>**Note**: Box AI handles documents with text representations up to 1MB in size. If the file
   * size exceeds 1MB, the first 1MB of text representation will be processed.
   */
  protected final List<AiTextGenItemsField> items;

  /**
   * The history of prompts and answers previously passed to the LLM. This parameter provides the
   * additional context to the LLM when generating the response.
   */
  @JsonProperty("dialogue_history")
  protected List<AiDialogueHistory> dialogueHistory;

  @JsonProperty("ai_agent")
  protected AiTextGenAgent aiAgent;

  public AiTextGen(
      @JsonProperty("prompt") String prompt,
      @JsonProperty("items") List<AiTextGenItemsField> items) {
    super();
    this.prompt = prompt;
    this.items = items;
  }

  protected AiTextGen(Builder builder) {
    super();
    this.prompt = builder.prompt;
    this.items = builder.items;
    this.dialogueHistory = builder.dialogueHistory;
    this.aiAgent = builder.aiAgent;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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

  public AiTextGenAgent getAiAgent() {
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

  public static class Builder extends NullableFieldTracker {

    protected final String prompt;

    protected final List<AiTextGenItemsField> items;

    protected List<AiDialogueHistory> dialogueHistory;

    protected AiTextGenAgent aiAgent;

    public Builder(String prompt, List<AiTextGenItemsField> items) {
      super();
      this.prompt = prompt;
      this.items = items;
    }

    public Builder dialogueHistory(List<AiDialogueHistory> dialogueHistory) {
      this.dialogueHistory = dialogueHistory;
      return this;
    }

    public Builder aiAgent(AiAgentReference aiAgent) {
      this.aiAgent = new AiTextGenAgent(aiAgent);
      return this;
    }

    public Builder aiAgent(AiAgentTextGen aiAgent) {
      this.aiAgent = new AiTextGenAgent(aiAgent);
      return this;
    }

    public Builder aiAgent(AiTextGenAgent aiAgent) {
      this.aiAgent = aiAgent;
      return this;
    }

    public AiTextGen build() {
      return new AiTextGen(this);
    }
  }
}
