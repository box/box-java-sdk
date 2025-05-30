package com.box.sdkgen.schemas.aiextractstructured;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class AiExtractStructuredMetadataTemplateField extends SerializableObject {

  @JsonProperty("template_key")
  protected String templateKey;

  @JsonDeserialize(
      using =
          AiExtractStructuredMetadataTemplateTypeField
              .AiExtractStructuredMetadataTemplateTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          AiExtractStructuredMetadataTemplateTypeField
              .AiExtractStructuredMetadataTemplateTypeFieldSerializer.class)
  protected EnumWrapper<AiExtractStructuredMetadataTemplateTypeField> type;

  protected String scope;

  public AiExtractStructuredMetadataTemplateField() {
    super();
  }

  protected AiExtractStructuredMetadataTemplateField(
      AiExtractStructuredMetadataTemplateFieldBuilder builder) {
    super();
    this.templateKey = builder.templateKey;
    this.type = builder.type;
    this.scope = builder.scope;
  }

  public String getTemplateKey() {
    return templateKey;
  }

  public EnumWrapper<AiExtractStructuredMetadataTemplateTypeField> getType() {
    return type;
  }

  public String getScope() {
    return scope;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AiExtractStructuredMetadataTemplateField casted = (AiExtractStructuredMetadataTemplateField) o;
    return Objects.equals(templateKey, casted.templateKey)
        && Objects.equals(type, casted.type)
        && Objects.equals(scope, casted.scope);
  }

  @Override
  public int hashCode() {
    return Objects.hash(templateKey, type, scope);
  }

  @Override
  public String toString() {
    return "AiExtractStructuredMetadataTemplateField{"
        + "templateKey='"
        + templateKey
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "scope='"
        + scope
        + '\''
        + "}";
  }

  public static class AiExtractStructuredMetadataTemplateFieldBuilder {

    protected String templateKey;

    protected EnumWrapper<AiExtractStructuredMetadataTemplateTypeField> type;

    protected String scope;

    public AiExtractStructuredMetadataTemplateFieldBuilder templateKey(String templateKey) {
      this.templateKey = templateKey;
      return this;
    }

    public AiExtractStructuredMetadataTemplateFieldBuilder type(
        AiExtractStructuredMetadataTemplateTypeField type) {
      this.type = new EnumWrapper<AiExtractStructuredMetadataTemplateTypeField>(type);
      return this;
    }

    public AiExtractStructuredMetadataTemplateFieldBuilder type(
        EnumWrapper<AiExtractStructuredMetadataTemplateTypeField> type) {
      this.type = type;
      return this;
    }

    public AiExtractStructuredMetadataTemplateFieldBuilder scope(String scope) {
      this.scope = scope;
      return this;
    }

    public AiExtractStructuredMetadataTemplateField build() {
      return new AiExtractStructuredMetadataTemplateField(this);
    }
  }
}
