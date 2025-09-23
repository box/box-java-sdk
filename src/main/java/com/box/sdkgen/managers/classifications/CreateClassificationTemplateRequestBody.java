package com.box.sdkgen.managers.classifications;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CreateClassificationTemplateRequestBody extends SerializableObject {

  @JsonDeserialize(
      using =
          CreateClassificationTemplateRequestBodyScopeField
              .CreateClassificationTemplateRequestBodyScopeFieldDeserializer.class)
  @JsonSerialize(
      using =
          CreateClassificationTemplateRequestBodyScopeField
              .CreateClassificationTemplateRequestBodyScopeFieldSerializer.class)
  protected EnumWrapper<CreateClassificationTemplateRequestBodyScopeField> scope;

  @JsonDeserialize(
      using =
          CreateClassificationTemplateRequestBodyTemplateKeyField
              .CreateClassificationTemplateRequestBodyTemplateKeyFieldDeserializer.class)
  @JsonSerialize(
      using =
          CreateClassificationTemplateRequestBodyTemplateKeyField
              .CreateClassificationTemplateRequestBodyTemplateKeyFieldSerializer.class)
  protected EnumWrapper<CreateClassificationTemplateRequestBodyTemplateKeyField> templateKey;

  @JsonDeserialize(
      using =
          CreateClassificationTemplateRequestBodyDisplayNameField
              .CreateClassificationTemplateRequestBodyDisplayNameFieldDeserializer.class)
  @JsonSerialize(
      using =
          CreateClassificationTemplateRequestBodyDisplayNameField
              .CreateClassificationTemplateRequestBodyDisplayNameFieldSerializer.class)
  protected EnumWrapper<CreateClassificationTemplateRequestBodyDisplayNameField> displayName;

  protected Boolean hidden;

  protected Boolean copyInstanceOnItemCopy;

  protected final List<CreateClassificationTemplateRequestBodyFieldsField> fields;

  public CreateClassificationTemplateRequestBody(
      @JsonProperty("fields") List<CreateClassificationTemplateRequestBodyFieldsField> fields) {
    super();
    this.fields = fields;
    this.scope =
        new EnumWrapper<CreateClassificationTemplateRequestBodyScopeField>(
            CreateClassificationTemplateRequestBodyScopeField.ENTERPRISE);
    this.templateKey =
        new EnumWrapper<CreateClassificationTemplateRequestBodyTemplateKeyField>(
            CreateClassificationTemplateRequestBodyTemplateKeyField
                .SECURITYCLASSIFICATION_6VMVOCHWUWO);
    this.displayName =
        new EnumWrapper<CreateClassificationTemplateRequestBodyDisplayNameField>(
            CreateClassificationTemplateRequestBodyDisplayNameField.CLASSIFICATION);
  }

  protected CreateClassificationTemplateRequestBody(Builder builder) {
    super();
    this.scope = builder.scope;
    this.templateKey = builder.templateKey;
    this.displayName = builder.displayName;
    this.hidden = builder.hidden;
    this.copyInstanceOnItemCopy = builder.copyInstanceOnItemCopy;
    this.fields = builder.fields;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<CreateClassificationTemplateRequestBodyScopeField> getScope() {
    return scope;
  }

  public EnumWrapper<CreateClassificationTemplateRequestBodyTemplateKeyField> getTemplateKey() {
    return templateKey;
  }

  public EnumWrapper<CreateClassificationTemplateRequestBodyDisplayNameField> getDisplayName() {
    return displayName;
  }

  public Boolean getHidden() {
    return hidden;
  }

  public Boolean getCopyInstanceOnItemCopy() {
    return copyInstanceOnItemCopy;
  }

  public List<CreateClassificationTemplateRequestBodyFieldsField> getFields() {
    return fields;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateClassificationTemplateRequestBody casted = (CreateClassificationTemplateRequestBody) o;
    return Objects.equals(scope, casted.scope)
        && Objects.equals(templateKey, casted.templateKey)
        && Objects.equals(displayName, casted.displayName)
        && Objects.equals(hidden, casted.hidden)
        && Objects.equals(copyInstanceOnItemCopy, casted.copyInstanceOnItemCopy)
        && Objects.equals(fields, casted.fields);
  }

  @Override
  public int hashCode() {
    return Objects.hash(scope, templateKey, displayName, hidden, copyInstanceOnItemCopy, fields);
  }

  @Override
  public String toString() {
    return "CreateClassificationTemplateRequestBody{"
        + "scope='"
        + scope
        + '\''
        + ", "
        + "templateKey='"
        + templateKey
        + '\''
        + ", "
        + "displayName='"
        + displayName
        + '\''
        + ", "
        + "hidden='"
        + hidden
        + '\''
        + ", "
        + "copyInstanceOnItemCopy='"
        + copyInstanceOnItemCopy
        + '\''
        + ", "
        + "fields='"
        + fields
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<CreateClassificationTemplateRequestBodyScopeField> scope;

    protected EnumWrapper<CreateClassificationTemplateRequestBodyTemplateKeyField> templateKey;

    protected EnumWrapper<CreateClassificationTemplateRequestBodyDisplayNameField> displayName;

    protected Boolean hidden;

    protected Boolean copyInstanceOnItemCopy;

    protected final List<CreateClassificationTemplateRequestBodyFieldsField> fields;

    public Builder(List<CreateClassificationTemplateRequestBodyFieldsField> fields) {
      super();
      this.fields = fields;
      this.scope =
          new EnumWrapper<CreateClassificationTemplateRequestBodyScopeField>(
              CreateClassificationTemplateRequestBodyScopeField.ENTERPRISE);
      this.templateKey =
          new EnumWrapper<CreateClassificationTemplateRequestBodyTemplateKeyField>(
              CreateClassificationTemplateRequestBodyTemplateKeyField
                  .SECURITYCLASSIFICATION_6VMVOCHWUWO);
      this.displayName =
          new EnumWrapper<CreateClassificationTemplateRequestBodyDisplayNameField>(
              CreateClassificationTemplateRequestBodyDisplayNameField.CLASSIFICATION);
    }

    public Builder scope(CreateClassificationTemplateRequestBodyScopeField scope) {
      this.scope = new EnumWrapper<CreateClassificationTemplateRequestBodyScopeField>(scope);
      return this;
    }

    public Builder scope(EnumWrapper<CreateClassificationTemplateRequestBodyScopeField> scope) {
      this.scope = scope;
      return this;
    }

    public Builder templateKey(
        CreateClassificationTemplateRequestBodyTemplateKeyField templateKey) {
      this.templateKey =
          new EnumWrapper<CreateClassificationTemplateRequestBodyTemplateKeyField>(templateKey);
      return this;
    }

    public Builder templateKey(
        EnumWrapper<CreateClassificationTemplateRequestBodyTemplateKeyField> templateKey) {
      this.templateKey = templateKey;
      return this;
    }

    public Builder displayName(
        CreateClassificationTemplateRequestBodyDisplayNameField displayName) {
      this.displayName =
          new EnumWrapper<CreateClassificationTemplateRequestBodyDisplayNameField>(displayName);
      return this;
    }

    public Builder displayName(
        EnumWrapper<CreateClassificationTemplateRequestBodyDisplayNameField> displayName) {
      this.displayName = displayName;
      return this;
    }

    public Builder hidden(Boolean hidden) {
      this.hidden = hidden;
      return this;
    }

    public Builder copyInstanceOnItemCopy(Boolean copyInstanceOnItemCopy) {
      this.copyInstanceOnItemCopy = copyInstanceOnItemCopy;
      return this;
    }

    public CreateClassificationTemplateRequestBody build() {
      return new CreateClassificationTemplateRequestBody(this);
    }
  }
}
