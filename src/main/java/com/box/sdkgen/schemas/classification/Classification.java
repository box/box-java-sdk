package com.box.sdkgen.schemas.classification;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/**
 * An instance of the classification metadata template, containing the classification applied to the
 * file or folder.
 *
 * <p>To get more details about the classification applied to an item, request the classification
 * metadata template.
 */
@JsonFilter("nullablePropertyFilter")
public class Classification extends SerializableObject {

  /** The name of the classification applied to the item. */
  @JsonProperty("Box__Security__Classification__Key")
  protected String boxSecurityClassificationKey;

  /**
   * The identifier of the item that this metadata instance has been attached to. This combines the
   * `type` and the `id` of the parent in the form `{type}_{id}`.
   */
  @JsonProperty("$parent")
  protected String parent;

  /** The value will always be `securityClassification-6VMVochwUWo`. */
  @JsonDeserialize(
      using = ClassificationTemplateField.ClassificationTemplateFieldDeserializer.class)
  @JsonSerialize(using = ClassificationTemplateField.ClassificationTemplateFieldSerializer.class)
  @JsonProperty("$template")
  protected EnumWrapper<ClassificationTemplateField> template;

  /**
   * The scope of the enterprise that this classification has been applied for.
   *
   * <p>This will be in the format `enterprise_{enterprise_id}`.
   */
  @JsonProperty("$scope")
  protected String scope;

  /**
   * The version of the metadata instance. This version starts at 0 and increases every time a
   * classification is updated.
   */
  @JsonProperty("$version")
  protected Long version;

  /**
   * The unique ID of this classification instance. This will be include the name of the
   * classification template and a unique ID.
   */
  @JsonProperty("$type")
  protected String type;

  /**
   * The version of the metadata template. This version starts at 0 and increases every time the
   * template is updated. This is mostly for internal use.
   */
  @JsonProperty("$typeVersion")
  protected Double typeVersion;

  /** Whether an end user can change the classification. */
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
