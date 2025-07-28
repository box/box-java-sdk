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

@JsonFilter("nullablePropertyFilter")
public class ClassificationTemplateFieldsField extends SerializableObject {

  protected final String id;

  @JsonDeserialize(
      using =
          ClassificationTemplateFieldsTypeField.ClassificationTemplateFieldsTypeFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          ClassificationTemplateFieldsTypeField.ClassificationTemplateFieldsTypeFieldSerializer
              .class)
  protected EnumWrapper<ClassificationTemplateFieldsTypeField> type;

  @JsonDeserialize(
      using =
          ClassificationTemplateFieldsKeyField.ClassificationTemplateFieldsKeyFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          ClassificationTemplateFieldsKeyField.ClassificationTemplateFieldsKeyFieldSerializer.class)
  protected EnumWrapper<ClassificationTemplateFieldsKeyField> key;

  @JsonDeserialize(
      using =
          ClassificationTemplateFieldsDisplayNameField
              .ClassificationTemplateFieldsDisplayNameFieldDeserializer.class)
  @JsonSerialize(
      using =
          ClassificationTemplateFieldsDisplayNameField
              .ClassificationTemplateFieldsDisplayNameFieldSerializer.class)
  protected EnumWrapper<ClassificationTemplateFieldsDisplayNameField> displayName;

  protected Boolean hidden;

  protected final List<ClassificationTemplateFieldsOptionsField> options;

  public ClassificationTemplateFieldsField(
      @JsonProperty("id") String id,
      @JsonProperty("options") List<ClassificationTemplateFieldsOptionsField> options) {
    super();
    this.id = id;
    this.options = options;
    this.type =
        new EnumWrapper<ClassificationTemplateFieldsTypeField>(
            ClassificationTemplateFieldsTypeField.ENUM);
    this.key =
        new EnumWrapper<ClassificationTemplateFieldsKeyField>(
            ClassificationTemplateFieldsKeyField.BOX__SECURITY__CLASSIFICATION__KEY);
    this.displayName =
        new EnumWrapper<ClassificationTemplateFieldsDisplayNameField>(
            ClassificationTemplateFieldsDisplayNameField.CLASSIFICATION);
  }

  protected ClassificationTemplateFieldsField(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.key = builder.key;
    this.displayName = builder.displayName;
    this.hidden = builder.hidden;
    this.options = builder.options;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<ClassificationTemplateFieldsTypeField> getType() {
    return type;
  }

  public EnumWrapper<ClassificationTemplateFieldsKeyField> getKey() {
    return key;
  }

  public EnumWrapper<ClassificationTemplateFieldsDisplayNameField> getDisplayName() {
    return displayName;
  }

  public Boolean getHidden() {
    return hidden;
  }

  public List<ClassificationTemplateFieldsOptionsField> getOptions() {
    return options;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ClassificationTemplateFieldsField casted = (ClassificationTemplateFieldsField) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(key, casted.key)
        && Objects.equals(displayName, casted.displayName)
        && Objects.equals(hidden, casted.hidden)
        && Objects.equals(options, casted.options);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, key, displayName, hidden, options);
  }

  @Override
  public String toString() {
    return "ClassificationTemplateFieldsField{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "key='"
        + key
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
        + "options='"
        + options
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String id;

    protected EnumWrapper<ClassificationTemplateFieldsTypeField> type;

    protected EnumWrapper<ClassificationTemplateFieldsKeyField> key;

    protected EnumWrapper<ClassificationTemplateFieldsDisplayNameField> displayName;

    protected Boolean hidden;

    protected final List<ClassificationTemplateFieldsOptionsField> options;

    public Builder(String id, List<ClassificationTemplateFieldsOptionsField> options) {
      super();
      this.id = id;
      this.options = options;
      this.type =
          new EnumWrapper<ClassificationTemplateFieldsTypeField>(
              ClassificationTemplateFieldsTypeField.ENUM);
      this.key =
          new EnumWrapper<ClassificationTemplateFieldsKeyField>(
              ClassificationTemplateFieldsKeyField.BOX__SECURITY__CLASSIFICATION__KEY);
      this.displayName =
          new EnumWrapper<ClassificationTemplateFieldsDisplayNameField>(
              ClassificationTemplateFieldsDisplayNameField.CLASSIFICATION);
    }

    public Builder type(ClassificationTemplateFieldsTypeField type) {
      this.type = new EnumWrapper<ClassificationTemplateFieldsTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<ClassificationTemplateFieldsTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder key(ClassificationTemplateFieldsKeyField key) {
      this.key = new EnumWrapper<ClassificationTemplateFieldsKeyField>(key);
      return this;
    }

    public Builder key(EnumWrapper<ClassificationTemplateFieldsKeyField> key) {
      this.key = key;
      return this;
    }

    public Builder displayName(ClassificationTemplateFieldsDisplayNameField displayName) {
      this.displayName = new EnumWrapper<ClassificationTemplateFieldsDisplayNameField>(displayName);
      return this;
    }

    public Builder displayName(
        EnumWrapper<ClassificationTemplateFieldsDisplayNameField> displayName) {
      this.displayName = displayName;
      return this;
    }

    public Builder hidden(Boolean hidden) {
      this.hidden = hidden;
      return this;
    }

    public ClassificationTemplateFieldsField build() {
      return new ClassificationTemplateFieldsField(this);
    }
  }
}
