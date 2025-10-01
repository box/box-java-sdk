package com.box.sdkgen.schemas.metadatatemplate;

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
public class MetadataTemplateFieldsField extends SerializableObject {

  /**
   * The type of field. The basic fields are a `string` field for text, a `float` field for numbers,
   * and a `date` fields to present the user with a date-time picker.
   *
   * <p>Additionally, metadata templates support an `enum` field for a basic list of items, and `
   * multiSelect` field for a similar list of items where the user can select more than one value.
   *
   * <p>**Note**: The `integer` value is deprecated. It is still present in the response, but cannot
   * be used in the POST request.
   */
  @JsonDeserialize(
      using = MetadataTemplateFieldsTypeField.MetadataTemplateFieldsTypeFieldDeserializer.class)
  @JsonSerialize(
      using = MetadataTemplateFieldsTypeField.MetadataTemplateFieldsTypeFieldSerializer.class)
  protected final EnumWrapper<MetadataTemplateFieldsTypeField> type;

  /**
   * A unique identifier for the field. The identifier must be unique within the template to which
   * it belongs.
   */
  protected final String key;

  /** The display name of the field as it is shown to the user in the web and mobile apps. */
  protected final String displayName;

  /** A description of the field. This is not shown to the user. */
  protected String description;

  /**
   * Whether this field is hidden in the UI for the user and can only be set through the API
   * instead.
   */
  protected Boolean hidden;

  /**
   * A list of options for this field. This is used in combination with the `enum` and `multiSelect`
   * field types.
   */
  protected List<MetadataTemplateFieldsOptionsField> options;

  /** The unique ID of the metadata template field. */
  protected String id;

  public MetadataTemplateFieldsField(
      MetadataTemplateFieldsTypeField type, String key, String displayName) {
    super();
    this.type = new EnumWrapper<MetadataTemplateFieldsTypeField>(type);
    this.key = key;
    this.displayName = displayName;
  }

  public MetadataTemplateFieldsField(
      @JsonProperty("type") EnumWrapper<MetadataTemplateFieldsTypeField> type,
      @JsonProperty("key") String key,
      @JsonProperty("displayName") String displayName) {
    super();
    this.type = type;
    this.key = key;
    this.displayName = displayName;
  }

  protected MetadataTemplateFieldsField(Builder builder) {
    super();
    this.type = builder.type;
    this.key = builder.key;
    this.displayName = builder.displayName;
    this.description = builder.description;
    this.hidden = builder.hidden;
    this.options = builder.options;
    this.id = builder.id;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<MetadataTemplateFieldsTypeField> getType() {
    return type;
  }

  public String getKey() {
    return key;
  }

  public String getDisplayName() {
    return displayName;
  }

  public String getDescription() {
    return description;
  }

  public Boolean getHidden() {
    return hidden;
  }

  public List<MetadataTemplateFieldsOptionsField> getOptions() {
    return options;
  }

  public String getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MetadataTemplateFieldsField casted = (MetadataTemplateFieldsField) o;
    return Objects.equals(type, casted.type)
        && Objects.equals(key, casted.key)
        && Objects.equals(displayName, casted.displayName)
        && Objects.equals(description, casted.description)
        && Objects.equals(hidden, casted.hidden)
        && Objects.equals(options, casted.options)
        && Objects.equals(id, casted.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, key, displayName, description, hidden, options, id);
  }

  @Override
  public String toString() {
    return "MetadataTemplateFieldsField{"
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
        + "description='"
        + description
        + '\''
        + ", "
        + "hidden='"
        + hidden
        + '\''
        + ", "
        + "options='"
        + options
        + '\''
        + ", "
        + "id='"
        + id
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final EnumWrapper<MetadataTemplateFieldsTypeField> type;

    protected final String key;

    protected final String displayName;

    protected String description;

    protected Boolean hidden;

    protected List<MetadataTemplateFieldsOptionsField> options;

    protected String id;

    public Builder(MetadataTemplateFieldsTypeField type, String key, String displayName) {
      super();
      this.type = new EnumWrapper<MetadataTemplateFieldsTypeField>(type);
      this.key = key;
      this.displayName = displayName;
    }

    public Builder(
        EnumWrapper<MetadataTemplateFieldsTypeField> type, String key, String displayName) {
      super();
      this.type = type;
      this.key = key;
      this.displayName = displayName;
    }

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Builder hidden(Boolean hidden) {
      this.hidden = hidden;
      return this;
    }

    public Builder options(List<MetadataTemplateFieldsOptionsField> options) {
      this.options = options;
      return this;
    }

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public MetadataTemplateFieldsField build() {
      return new MetadataTemplateFieldsField(this);
    }
  }
}
