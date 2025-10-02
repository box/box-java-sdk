package com.box.sdkgen.schemas.classificationtemplate;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Objects;

/** A metadata template that holds the security classifications defined by an enterprise. */
@JsonFilter("nullablePropertyFilter")
public class ClassificationTemplate extends SerializableObject {

  /** The ID of the classification template. */
  protected final String id;

  /** The value will always be `metadata_template`. */
  @JsonDeserialize(
      using = ClassificationTemplateTypeField.ClassificationTemplateTypeFieldDeserializer.class)
  @JsonSerialize(
      using = ClassificationTemplateTypeField.ClassificationTemplateTypeFieldSerializer.class)
  protected EnumWrapper<ClassificationTemplateTypeField> type;

  /**
   * The scope of the classification template. This is in the format `enterprise_{id}` where the
   * `id` is the enterprise ID.
   */
  protected final String scope;

  /** The value will always be `securityClassification-6VMVochwUWo`. */
  @JsonDeserialize(
      using =
          ClassificationTemplateTemplateKeyField.ClassificationTemplateTemplateKeyFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          ClassificationTemplateTemplateKeyField.ClassificationTemplateTemplateKeyFieldSerializer
              .class)
  protected EnumWrapper<ClassificationTemplateTemplateKeyField> templateKey;

  /** The name of this template as shown in web and mobile interfaces. */
  @JsonDeserialize(
      using =
          ClassificationTemplateDisplayNameField.ClassificationTemplateDisplayNameFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          ClassificationTemplateDisplayNameField.ClassificationTemplateDisplayNameFieldSerializer
              .class)
  protected EnumWrapper<ClassificationTemplateDisplayNameField> displayName;

  /** Determines if the template is always available in web and mobile interfaces. */
  protected Boolean hidden;

  /** Determines if classifications are copied along when the file or folder is copied. */
  protected Boolean copyInstanceOnItemCopy;

  /**
   * A list of fields for this classification template. This includes only one field, the
   * `Box__Security__Classification__Key`, which defines the different classifications available in
   * this enterprise.
   */
  protected final List<ClassificationTemplateFieldsField> fields;

  public ClassificationTemplate(
      @JsonProperty("id") String id,
      @JsonProperty("scope") String scope,
      @JsonProperty("fields") List<ClassificationTemplateFieldsField> fields) {
    super();
    this.id = id;
    this.scope = scope;
    this.fields = fields;
    this.type =
        new EnumWrapper<ClassificationTemplateTypeField>(
            ClassificationTemplateTypeField.METADATA_TEMPLATE);
    this.templateKey =
        new EnumWrapper<ClassificationTemplateTemplateKeyField>(
            ClassificationTemplateTemplateKeyField.SECURITYCLASSIFICATION_6VMVOCHWUWO);
    this.displayName =
        new EnumWrapper<ClassificationTemplateDisplayNameField>(
            ClassificationTemplateDisplayNameField.CLASSIFICATION);
  }

  protected ClassificationTemplate(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.scope = builder.scope;
    this.templateKey = builder.templateKey;
    this.displayName = builder.displayName;
    this.hidden = builder.hidden;
    this.copyInstanceOnItemCopy = builder.copyInstanceOnItemCopy;
    this.fields = builder.fields;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<ClassificationTemplateTypeField> getType() {
    return type;
  }

  public String getScope() {
    return scope;
  }

  public EnumWrapper<ClassificationTemplateTemplateKeyField> getTemplateKey() {
    return templateKey;
  }

  public EnumWrapper<ClassificationTemplateDisplayNameField> getDisplayName() {
    return displayName;
  }

  public Boolean getHidden() {
    return hidden;
  }

  public Boolean getCopyInstanceOnItemCopy() {
    return copyInstanceOnItemCopy;
  }

  public List<ClassificationTemplateFieldsField> getFields() {
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
    ClassificationTemplate casted = (ClassificationTemplate) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(scope, casted.scope)
        && Objects.equals(templateKey, casted.templateKey)
        && Objects.equals(displayName, casted.displayName)
        && Objects.equals(hidden, casted.hidden)
        && Objects.equals(copyInstanceOnItemCopy, casted.copyInstanceOnItemCopy)
        && Objects.equals(fields, casted.fields);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id, type, scope, templateKey, displayName, hidden, copyInstanceOnItemCopy, fields);
  }

  @Override
  public String toString() {
    return "ClassificationTemplate{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
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

    protected final String id;

    protected EnumWrapper<ClassificationTemplateTypeField> type;

    protected final String scope;

    protected EnumWrapper<ClassificationTemplateTemplateKeyField> templateKey;

    protected EnumWrapper<ClassificationTemplateDisplayNameField> displayName;

    protected Boolean hidden;

    protected Boolean copyInstanceOnItemCopy;

    protected final List<ClassificationTemplateFieldsField> fields;

    public Builder(String id, String scope, List<ClassificationTemplateFieldsField> fields) {
      super();
      this.id = id;
      this.scope = scope;
      this.fields = fields;
      this.type =
          new EnumWrapper<ClassificationTemplateTypeField>(
              ClassificationTemplateTypeField.METADATA_TEMPLATE);
      this.templateKey =
          new EnumWrapper<ClassificationTemplateTemplateKeyField>(
              ClassificationTemplateTemplateKeyField.SECURITYCLASSIFICATION_6VMVOCHWUWO);
      this.displayName =
          new EnumWrapper<ClassificationTemplateDisplayNameField>(
              ClassificationTemplateDisplayNameField.CLASSIFICATION);
    }

    public Builder type(ClassificationTemplateTypeField type) {
      this.type = new EnumWrapper<ClassificationTemplateTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<ClassificationTemplateTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder templateKey(ClassificationTemplateTemplateKeyField templateKey) {
      this.templateKey = new EnumWrapper<ClassificationTemplateTemplateKeyField>(templateKey);
      return this;
    }

    public Builder templateKey(EnumWrapper<ClassificationTemplateTemplateKeyField> templateKey) {
      this.templateKey = templateKey;
      return this;
    }

    public Builder displayName(ClassificationTemplateDisplayNameField displayName) {
      this.displayName = new EnumWrapper<ClassificationTemplateDisplayNameField>(displayName);
      return this;
    }

    public Builder displayName(EnumWrapper<ClassificationTemplateDisplayNameField> displayName) {
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

    public ClassificationTemplate build() {
      return new ClassificationTemplate(this);
    }
  }
}
