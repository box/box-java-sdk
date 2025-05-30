package com.box.sdkgen.schemas.metadatatemplate;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Objects;

public class MetadataTemplate extends SerializableObject {

  protected final String id;

  @JsonDeserialize(using = MetadataTemplateTypeField.MetadataTemplateTypeFieldDeserializer.class)
  @JsonSerialize(using = MetadataTemplateTypeField.MetadataTemplateTypeFieldSerializer.class)
  protected EnumWrapper<MetadataTemplateTypeField> type;

  protected String scope;

  protected String templateKey;

  protected String displayName;

  protected Boolean hidden;

  protected List<MetadataTemplateFieldsField> fields;

  protected Boolean copyInstanceOnItemCopy;

  public MetadataTemplate(@JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type =
        new EnumWrapper<MetadataTemplateTypeField>(MetadataTemplateTypeField.METADATA_TEMPLATE);
  }

  protected MetadataTemplate(MetadataTemplateBuilder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.scope = builder.scope;
    this.templateKey = builder.templateKey;
    this.displayName = builder.displayName;
    this.hidden = builder.hidden;
    this.fields = builder.fields;
    this.copyInstanceOnItemCopy = builder.copyInstanceOnItemCopy;
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

  public static class MetadataTemplateBuilder {

    protected final String id;

    protected EnumWrapper<MetadataTemplateTypeField> type;

    protected String scope;

    protected String templateKey;

    protected String displayName;

    protected Boolean hidden;

    protected List<MetadataTemplateFieldsField> fields;

    protected Boolean copyInstanceOnItemCopy;

    public MetadataTemplateBuilder(String id) {
      this.id = id;
      this.type =
          new EnumWrapper<MetadataTemplateTypeField>(MetadataTemplateTypeField.METADATA_TEMPLATE);
    }

    public MetadataTemplateBuilder type(MetadataTemplateTypeField type) {
      this.type = new EnumWrapper<MetadataTemplateTypeField>(type);
      return this;
    }

    public MetadataTemplateBuilder type(EnumWrapper<MetadataTemplateTypeField> type) {
      this.type = type;
      return this;
    }

    public MetadataTemplateBuilder scope(String scope) {
      this.scope = scope;
      return this;
    }

    public MetadataTemplateBuilder templateKey(String templateKey) {
      this.templateKey = templateKey;
      return this;
    }

    public MetadataTemplateBuilder displayName(String displayName) {
      this.displayName = displayName;
      return this;
    }

    public MetadataTemplateBuilder hidden(Boolean hidden) {
      this.hidden = hidden;
      return this;
    }

    public MetadataTemplateBuilder fields(List<MetadataTemplateFieldsField> fields) {
      this.fields = fields;
      return this;
    }

    public MetadataTemplateBuilder copyInstanceOnItemCopy(Boolean copyInstanceOnItemCopy) {
      this.copyInstanceOnItemCopy = copyInstanceOnItemCopy;
      return this;
    }

    public MetadataTemplate build() {
      return new MetadataTemplate(this);
    }
  }
}
