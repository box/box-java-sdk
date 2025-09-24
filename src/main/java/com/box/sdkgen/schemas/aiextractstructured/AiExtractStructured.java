package com.box.sdkgen.schemas.aiextractstructured;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.aiagentextractstructured.AiAgentExtractStructured;
import com.box.sdkgen.schemas.aiagentreference.AiAgentReference;
import com.box.sdkgen.schemas.aiextractstructuredagent.AiExtractStructuredAgent;
import com.box.sdkgen.schemas.aiitembase.AiItemBase;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class AiExtractStructured extends SerializableObject {

  protected final List<AiItemBase> items;

  @JsonProperty("metadata_template")
  protected AiExtractStructuredMetadataTemplateField metadataTemplate;

  protected List<AiExtractStructuredFieldsField> fields;

  @JsonProperty("ai_agent")
  protected AiExtractStructuredAgent aiAgent;

  public AiExtractStructured(@JsonProperty("items") List<AiItemBase> items) {
    super();
    this.items = items;
  }

  protected AiExtractStructured(Builder builder) {
    super();
    this.items = builder.items;
    this.metadataTemplate = builder.metadataTemplate;
    this.fields = builder.fields;
    this.aiAgent = builder.aiAgent;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public List<AiItemBase> getItems() {
    return items;
  }

  public AiExtractStructuredMetadataTemplateField getMetadataTemplate() {
    return metadataTemplate;
  }

  public List<AiExtractStructuredFieldsField> getFields() {
    return fields;
  }

  public AiExtractStructuredAgent getAiAgent() {
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
    AiExtractStructured casted = (AiExtractStructured) o;
    return Objects.equals(items, casted.items)
        && Objects.equals(metadataTemplate, casted.metadataTemplate)
        && Objects.equals(fields, casted.fields)
        && Objects.equals(aiAgent, casted.aiAgent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(items, metadataTemplate, fields, aiAgent);
  }

  @Override
  public String toString() {
    return "AiExtractStructured{"
        + "items='"
        + items
        + '\''
        + ", "
        + "metadataTemplate='"
        + metadataTemplate
        + '\''
        + ", "
        + "fields='"
        + fields
        + '\''
        + ", "
        + "aiAgent='"
        + aiAgent
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final List<AiItemBase> items;

    protected AiExtractStructuredMetadataTemplateField metadataTemplate;

    protected List<AiExtractStructuredFieldsField> fields;

    protected AiExtractStructuredAgent aiAgent;

    public Builder(List<AiItemBase> items) {
      super();
      this.items = items;
    }

    public Builder metadataTemplate(AiExtractStructuredMetadataTemplateField metadataTemplate) {
      this.metadataTemplate = metadataTemplate;
      return this;
    }

    public Builder fields(List<AiExtractStructuredFieldsField> fields) {
      this.fields = fields;
      return this;
    }

    public Builder aiAgent(AiAgentReference aiAgent) {
      this.aiAgent = new AiExtractStructuredAgent(aiAgent);
      return this;
    }

    public Builder aiAgent(AiAgentExtractStructured aiAgent) {
      this.aiAgent = new AiExtractStructuredAgent(aiAgent);
      return this;
    }

    public Builder aiAgent(AiExtractStructuredAgent aiAgent) {
      this.aiAgent = aiAgent;
      return this;
    }

    public AiExtractStructured build() {
      return new AiExtractStructured(this);
    }
  }
}
