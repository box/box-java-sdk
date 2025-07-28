package com.box.sdkgen.schemas.aiextract;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.aiagentextract.AiAgentExtract;
import com.box.sdkgen.schemas.aiagentextractoraiagentreference.AiAgentExtractOrAiAgentReference;
import com.box.sdkgen.schemas.aiagentreference.AiAgentReference;
import com.box.sdkgen.schemas.aiitembase.AiItemBase;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class AiExtract extends SerializableObject {

  protected final String prompt;

  protected final List<AiItemBase> items;

  @JsonProperty("ai_agent")
  protected AiAgentExtractOrAiAgentReference aiAgent;

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

  public AiAgentExtractOrAiAgentReference getAiAgent() {
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

    protected AiAgentExtractOrAiAgentReference aiAgent;

    public Builder(String prompt, List<AiItemBase> items) {
      super();
      this.prompt = prompt;
      this.items = items;
    }

    public Builder aiAgent(AiAgentExtract aiAgent) {
      this.aiAgent = new AiAgentExtractOrAiAgentReference(aiAgent);
      return this;
    }

    public Builder aiAgent(AiAgentReference aiAgent) {
      this.aiAgent = new AiAgentExtractOrAiAgentReference(aiAgent);
      return this;
    }

    public Builder aiAgent(AiAgentExtractOrAiAgentReference aiAgent) {
      this.aiAgent = aiAgent;
      return this;
    }

    public AiExtract build() {
      return new AiExtract(this);
    }
  }
}
