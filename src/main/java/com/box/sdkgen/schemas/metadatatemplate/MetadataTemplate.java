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

/** A template for metadata that can be applied to files and folders. */
@JsonFilter("nullablePropertyFilter")
public class MetadataTemplate extends SerializableObject {

  /** The ID of the metadata template. */
  protected final String id;

  /** The value will always be `metadata_template`. */
  @JsonDeserialize(using = MetadataTemplateTypeField.MetadataTemplateTypeFieldDeserializer.class)
  @JsonSerialize(using = MetadataTemplateTypeField.MetadataTemplateTypeFieldSerializer.class)
  protected EnumWrapper<MetadataTemplateTypeField> type;

  /**
   * The scope of the metadata template can either be `global` or `enterprise_*`. The `global` scope
   * is used for templates that are available to any Box enterprise. The `enterprise_*` scope
   * represents templates that have been created within a specific enterprise, where `*` will be the
   * ID of that enterprise.
   */
  protected String scope;

  /**
   * A unique identifier for the template. This identifier is unique across the `scope` of the
   * enterprise to which the metadata template is being applied, yet is not necessarily unique
   * across different enterprises.
   */
  protected String templateKey;

  /** The display name of the template. This can be seen in the Box web app and mobile apps. */
  protected String displayName;

  /**
   * Defines if this template is visible in the Box web app UI, or if it is purely intended for
   * usage through the API.
   */
  protected Boolean hidden;

  /**
   * An ordered list of template fields which are part of the template. Each field can be a regular
   * text field, date field, number field, as well as a single or multi-select list.
   */
  protected List<MetadataTemplateFieldsField> fields;

  /** Whether or not to include the metadata when a file or folder is copied. */
  protected Boolean copyInstanceOnItemCopy;

  public MetadataTemplate(@JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type =
        new EnumWrapper<MetadataTemplateTypeField>(MetadataTemplateTypeField.METADATA_TEMPLATE);
  }

  protected MetadataTemplate(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.scope = builder.scope;
    this.templateKey = builder.templateKey;
    this.displayName = builder.displayName;
    this.hidden = builder.hidden;
    this.fields = builder.fields;
    this.copyInstanceOnItemCopy = builder.copyInstanceOnItemCopy;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<MetadataTemplateTypeField> getType() {
    return type;
  }

  public String getScope() {
    return scope;
  }

  public String getTemplateKey() {
    return templateKey;
  }

  public String getDisplayName() {
    return displayName;
  }

  public Boolean getHidden() {
    return hidden;
  }

  public List<MetadataTemplateFieldsField> getFields() {
    return fields;
  }

  public Boolean getCopyInstanceOnItemCopy() {
    return copyInstanceOnItemCopy;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MetadataTemplate casted = (MetadataTemplate) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(scope, casted.scope)
        && Objects.equals(templateKey, casted.templateKey)
        && Objects.equals(displayName, casted.displayName)
        && Objects.equals(hidden, casted.hidden)
        && Objects.equals(fields, casted.fields)
        && Objects.equals(copyInstanceOnItemCopy, casted.copyInstanceOnItemCopy);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id, type, scope, templateKey, displayName, hidden, fields, copyInstanceOnItemCopy);
  }

  @Override
  public String toString() {
    return "MetadataTemplate{"
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
        + "fields='"
        + fields
        + '\''
        + ", "
        + "copyInstanceOnItemCopy='"
        + copyInstanceOnItemCopy
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String id;

    protected EnumWrapper<MetadataTemplateTypeField> type;

    protected String scope;

    protected String templateKey;

    protected String displayName;

    protected Boolean hidden;

    protected List<MetadataTemplateFieldsField> fields;

    protected Boolean copyInstanceOnItemCopy;

    public Builder(String id) {
      super();
      this.id = id;
    }

    public Builder type(MetadataTemplateTypeField type) {
      this.type = new EnumWrapper<MetadataTemplateTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<MetadataTemplateTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder scope(String scope) {
      this.scope = scope;
      return this;
    }

    public Builder templateKey(String templateKey) {
      this.templateKey = templateKey;
      return this;
    }

    public Builder displayName(String displayName) {
      this.displayName = displayName;
      return this;
    }

    public Builder hidden(Boolean hidden) {
      this.hidden = hidden;
      return this;
    }

    public Builder fields(List<MetadataTemplateFieldsField> fields) {
      this.fields = fields;
      return this;
    }

    public Builder copyInstanceOnItemCopy(Boolean copyInstanceOnItemCopy) {
      this.copyInstanceOnItemCopy = copyInstanceOnItemCopy;
      return this;
    }

    public MetadataTemplate build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<MetadataTemplateTypeField>(MetadataTemplateTypeField.METADATA_TEMPLATE);
      }
      return new MetadataTemplate(this);
    }
  }
}
