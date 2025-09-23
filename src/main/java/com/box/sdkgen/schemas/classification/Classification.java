package com.box.sdkgen.schemas.classification;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class Classification extends SerializableObject {

  @JsonProperty("Box__Security__Classification__Key")
  protected String boxSecurityClassificationKey;

  @JsonProperty("$parent")
  protected String parent;

  @JsonDeserialize(
      using = ClassificationTemplateField.ClassificationTemplateFieldDeserializer.class)
  @JsonSerialize(using = ClassificationTemplateField.ClassificationTemplateFieldSerializer.class)
  @JsonProperty("$template")
  protected EnumWrapper<ClassificationTemplateField> template;

  @JsonProperty("$scope")
  protected String scope;

  @JsonProperty("$version")
  protected Long version;

  @JsonProperty("$type")
  protected String type;

  @JsonProperty("$typeVersion")
  protected Double typeVersion;

  @JsonProperty("$canEdit")
  protected Boolean canEdit;

  public Classification() {
    super();
  }

  protected Classification(Builder builder) {
    super();
    this.boxSecurityClassificationKey = builder.boxSecurityClassificationKey;
    this.parent = builder.parent;
    this.template = builder.template;
    this.scope = builder.scope;
    this.version = builder.version;
    this.type = builder.type;
    this.typeVersion = builder.typeVersion;
    this.canEdit = builder.canEdit;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getBoxSecurityClassificationKey() {
    return boxSecurityClassificationKey;
  }

  public String getParent() {
    return parent;
  }

  public EnumWrapper<ClassificationTemplateField> getTemplate() {
    return template;
  }

  public String getScope() {
    return scope;
  }

  public Long getVersion() {
    return version;
  }

  public String getType() {
    return type;
  }

  public Double getTypeVersion() {
    return typeVersion;
  }

  public Boolean getCanEdit() {
    return canEdit;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Classification casted = (Classification) o;
    return Objects.equals(boxSecurityClassificationKey, casted.boxSecurityClassificationKey)
        && Objects.equals(parent, casted.parent)
        && Objects.equals(template, casted.template)
        && Objects.equals(scope, casted.scope)
        && Objects.equals(version, casted.version)
        && Objects.equals(type, casted.type)
        && Objects.equals(typeVersion, casted.typeVersion)
        && Objects.equals(canEdit, casted.canEdit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        boxSecurityClassificationKey, parent, template, scope, version, type, typeVersion, canEdit);
  }

  @Override
  public String toString() {
    return "Classification{"
        + "boxSecurityClassificationKey='"
        + boxSecurityClassificationKey
        + '\''
        + ", "
        + "parent='"
        + parent
        + '\''
        + ", "
        + "template='"
        + template
        + '\''
        + ", "
        + "scope='"
        + scope
        + '\''
        + ", "
        + "version='"
        + version
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "typeVersion='"
        + typeVersion
        + '\''
        + ", "
        + "canEdit='"
        + canEdit
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String boxSecurityClassificationKey;

    protected String parent;

    protected EnumWrapper<ClassificationTemplateField> template;

    protected String scope;

    protected Long version;

    protected String type;

    protected Double typeVersion;

    protected Boolean canEdit;

    public Builder boxSecurityClassificationKey(String boxSecurityClassificationKey) {
      this.boxSecurityClassificationKey = boxSecurityClassificationKey;
      return this;
    }

    public Builder parent(String parent) {
      this.parent = parent;
      return this;
    }

    public Builder template(ClassificationTemplateField template) {
      this.template = new EnumWrapper<ClassificationTemplateField>(template);
      return this;
    }

    public Builder template(EnumWrapper<ClassificationTemplateField> template) {
      this.template = template;
      return this;
    }

    public Builder scope(String scope) {
      this.scope = scope;
      return this;
    }

    public Builder version(Long version) {
      this.version = version;
      return this;
    }

    public Builder type(String type) {
      this.type = type;
      return this;
    }

    public Builder typeVersion(Double typeVersion) {
      this.typeVersion = typeVersion;
      return this;
    }

    public Builder canEdit(Boolean canEdit) {
      this.canEdit = canEdit;
      return this;
    }

    public Classification build() {
      return new Classification(this);
    }
  }
}
