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

/** AI Extract Structured Request object. */
@JsonFilter("nullablePropertyFilter")
public class AiExtractStructured extends SerializableObject {

  /** The items to be processed by the LLM. Currently you can use files only. */
  protected final List<AiItemBase> items;

  /**
   * The metadata template containing the fields to extract. For your request to work, you must
   * provide either `metadata_template` or `fields`, but not both.
   */
  @JsonProperty("metadata_template")
  protected AiExtractStructuredMetadataTemplateField metadataTemplate;

  /**
   * The fields to be extracted from the provided items. For your request to work, you must provide
   * either `metadata_template` or `fields`, but not both.
   */
  protected List<AiExtractStructuredFieldsField> fields;

  /** A flag to indicate whether confidence scores for every extracted field should be returned. */
  @JsonProperty("include_confidence_score")
  protected Boolean includeConfidenceScore;

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
    this.includeConfidenceScore = builder.includeConfidenceScore;
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

  public Boolean getIncludeConfidenceScore() {
    return includeConfidenceScore;
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
        && Objects.equals(includeConfidenceScore, casted.includeConfidenceScore)
        && Objects.equals(aiAgent, casted.aiAgent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(items, metadataTemplate, fields, includeConfidenceScore, aiAgent);
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
        + "includeConfidenceScore='"
        + includeConfidenceScore
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

    protected Boolean includeConfidenceScore;

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

    public Builder includeConfidenceScore(Boolean includeConfidenceScore) {
      this.includeConfidenceScore = includeConfidenceScore;
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
