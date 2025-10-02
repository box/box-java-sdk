package com.box.sdkgen.schemas.aiextract;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.aiagentextract.AiAgentExtract;
import com.box.sdkgen.schemas.aiagentreference.AiAgentReference;
import com.box.sdkgen.schemas.aiextractagent.AiExtractAgent;
import com.box.sdkgen.schemas.aiitembase.AiItemBase;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

/** AI metadata freeform extraction request object. */
@JsonFilter("nullablePropertyFilter")
public class AiExtract extends SerializableObject {

  /**
   * The prompt provided to a Large Language Model (LLM) in the request. The prompt can be up to
   * 10000 characters long and it can be an XML or a JSON schema.
   */
  protected final String prompt;

  /** The items that LLM will process. Currently, you can use files only. */
  protected final List<AiItemBase> items;

  @JsonProperty("ai_agent")
  protected AiExtractAgent aiAgent;

  public AiExtract(
      @JsonProperty("prompt") String prompt, @JsonProperty("items") List<AiItemBase> items) {
    super();
    this.prompt = prompt;
    this.items = items;
  }

  protected AiExtract(Builder builder) {
    super();
    this.prompt = builder.prompt;
    this.items = builder.items;
    this.aiAgent = builder.aiAgent;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getPrompt() {
    return prompt;
  }

  public List<AiItemBase> getItems() {
    return items;
  }

  public AiExtractAgent getAiAgent() {
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
    AiExtract casted = (AiExtract) o;
    return Objects.equals(prompt, casted.prompt)
        && Objects.equals(items, casted.items)
        && Objects.equals(aiAgent, casted.aiAgent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(prompt, items, aiAgent);
  }

  @Override
  public String toString() {
    return "AiExtract{"
        + "prompt='"
        + prompt
        + '\''
        + ", "
        + "items='"
        + items
        + '\''
        + ", "
        + "aiAgent='"
        + aiAgent
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String prompt;

    protected final List<AiItemBase> items;

    protected AiExtractAgent aiAgent;

    public Builder(String prompt, List<AiItemBase> items) {
      super();
      this.prompt = prompt;
      this.items = items;
    }

    public Builder aiAgent(AiAgentReference aiAgent) {
      this.aiAgent = new AiExtractAgent(aiAgent);
      return this;
    }

    public Builder aiAgent(AiAgentExtract aiAgent) {
      this.aiAgent = new AiExtractAgent(aiAgent);
      return this;
    }

    public Builder aiAgent(AiExtractAgent aiAgent) {
      this.aiAgent = aiAgent;
      return this;
    }

    public AiExtract build() {
      return new AiExtract(this);
    }
  }
}
