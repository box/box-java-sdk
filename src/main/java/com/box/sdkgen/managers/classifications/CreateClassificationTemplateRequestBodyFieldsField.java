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
public class CreateClassificationTemplateRequestBodyFieldsField extends SerializableObject {

  /** The type of the field that is always enum. */
  @JsonDeserialize(
      using =
          CreateClassificationTemplateRequestBodyFieldsTypeField
              .CreateClassificationTemplateRequestBodyFieldsTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          CreateClassificationTemplateRequestBodyFieldsTypeField
              .CreateClassificationTemplateRequestBodyFieldsTypeFieldSerializer.class)
  protected EnumWrapper<CreateClassificationTemplateRequestBodyFieldsTypeField> type;

  /** Defines classifications available in the enterprise. */
  @JsonDeserialize(
      using =
          CreateClassificationTemplateRequestBodyFieldsKeyField
              .CreateClassificationTemplateRequestBodyFieldsKeyFieldDeserializer.class)
  @JsonSerialize(
      using =
          CreateClassificationTemplateRequestBodyFieldsKeyField
              .CreateClassificationTemplateRequestBodyFieldsKeyFieldSerializer.class)
  protected EnumWrapper<CreateClassificationTemplateRequestBodyFieldsKeyField> key;

  /** A display name for the classification. */
  @JsonDeserialize(
      using =
          CreateClassificationTemplateRequestBodyFieldsDisplayNameField
              .CreateClassificationTemplateRequestBodyFieldsDisplayNameFieldDeserializer.class)
  @JsonSerialize(
      using =
          CreateClassificationTemplateRequestBodyFieldsDisplayNameField
              .CreateClassificationTemplateRequestBodyFieldsDisplayNameFieldSerializer.class)
  protected EnumWrapper<CreateClassificationTemplateRequestBodyFieldsDisplayNameField> displayName;

  /** Determines if the classification template is hidden or available on web and mobile devices. */
  protected Boolean hidden;

  /** The actual list of classifications that are present on this template. */
  protected final List<CreateClassificationTemplateRequestBodyFieldsOptionsField> options;

  public CreateClassificationTemplateRequestBodyFieldsField(
      @JsonProperty("options")
          List<CreateClassificationTemplateRequestBodyFieldsOptionsField> options) {
    super();
    this.options = options;
    this.type =
        new EnumWrapper<CreateClassificationTemplateRequestBodyFieldsTypeField>(
            CreateClassificationTemplateRequestBodyFieldsTypeField.ENUM);
    this.key =
        new EnumWrapper<CreateClassificationTemplateRequestBodyFieldsKeyField>(
            CreateClassificationTemplateRequestBodyFieldsKeyField
                .BOX__SECURITY__CLASSIFICATION__KEY);
    this.displayName =
        new EnumWrapper<CreateClassificationTemplateRequestBodyFieldsDisplayNameField>(
            CreateClassificationTemplateRequestBodyFieldsDisplayNameField.CLASSIFICATION);
  }

  protected CreateClassificationTemplateRequestBodyFieldsField(Builder builder) {
    super();
    this.type = builder.type;
    this.key = builder.key;
    this.displayName = builder.displayName;
    this.hidden = builder.hidden;
    this.options = builder.options;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<CreateClassificationTemplateRequestBodyFieldsTypeField> getType() {
    return type;
  }

  public EnumWrapper<CreateClassificationTemplateRequestBodyFieldsKeyField> getKey() {
    return key;
  }

  public EnumWrapper<CreateClassificationTemplateRequestBodyFieldsDisplayNameField>
      getDisplayName() {
    return displayName;
  }

  public Boolean getHidden() {
    return hidden;
  }

  public List<CreateClassificationTemplateRequestBodyFieldsOptionsField> getOptions() {
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
    CreateClassificationTemplateRequestBodyFieldsField casted =
        (CreateClassificationTemplateRequestBodyFieldsField) o;
    return Objects.equals(type, casted.type)
        && Objects.equals(key, casted.key)
        && Objects.equals(displayName, casted.displayName)
        && Objects.equals(hidden, casted.hidden)
        && Objects.equals(options, casted.options);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, key, displayName, hidden, options);
  }

  @Override
  public String toString() {
    return "CreateClassificationTemplateRequestBodyFieldsField{"
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

    protected EnumWrapper<CreateClassificationTemplateRequestBodyFieldsTypeField> type;

    protected EnumWrapper<CreateClassificationTemplateRequestBodyFieldsKeyField> key;

    protected EnumWrapper<CreateClassificationTemplateRequestBodyFieldsDisplayNameField>
        displayName;

    protected Boolean hidden;

    protected final List<CreateClassificationTemplateRequestBodyFieldsOptionsField> options;

    public Builder(List<CreateClassificationTemplateRequestBodyFieldsOptionsField> options) {
      super();
      this.options = options;
    }

    public Builder type(CreateClassificationTemplateRequestBodyFieldsTypeField type) {
      this.type = new EnumWrapper<CreateClassificationTemplateRequestBodyFieldsTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<CreateClassificationTemplateRequestBodyFieldsTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder key(CreateClassificationTemplateRequestBodyFieldsKeyField key) {
      this.key = new EnumWrapper<CreateClassificationTemplateRequestBodyFieldsKeyField>(key);
      return this;
    }

    public Builder key(EnumWrapper<CreateClassificationTemplateRequestBodyFieldsKeyField> key) {
      this.key = key;
      return this;
    }

    public Builder displayName(
        CreateClassificationTemplateRequestBodyFieldsDisplayNameField displayName) {
      this.displayName =
          new EnumWrapper<CreateClassificationTemplateRequestBodyFieldsDisplayNameField>(
              displayName);
      return this;
    }

    public Builder displayName(
        EnumWrapper<CreateClassificationTemplateRequestBodyFieldsDisplayNameField> displayName) {
      this.displayName = displayName;
      return this;
    }

    public Builder hidden(Boolean hidden) {
      this.hidden = hidden;
      return this;
    }

    public CreateClassificationTemplateRequestBodyFieldsField build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<CreateClassificationTemplateRequestBodyFieldsTypeField>(
                CreateClassificationTemplateRequestBodyFieldsTypeField.ENUM);
      }
      if (this.key == null) {
        this.key =
            new EnumWrapper<CreateClassificationTemplateRequestBodyFieldsKeyField>(
                CreateClassificationTemplateRequestBodyFieldsKeyField
                    .BOX__SECURITY__CLASSIFICATION__KEY);
      }
      if (this.displayName == null) {
        this.displayName =
            new EnumWrapper<CreateClassificationTemplateRequestBodyFieldsDisplayNameField>(
                CreateClassificationTemplateRequestBodyFieldsDisplayNameField.CLASSIFICATION);
      }
      return new CreateClassificationTemplateRequestBodyFieldsField(this);
    }
  }
}
