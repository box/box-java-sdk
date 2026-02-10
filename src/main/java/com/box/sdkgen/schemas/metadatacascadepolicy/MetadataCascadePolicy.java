package com.box.sdkgen.schemas.metadatacascadepolicy;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/**
 * A metadata cascade policy automatically applies a metadata template instance to all the files and
 * folders within the targeted folder.
 */
@JsonFilter("nullablePropertyFilter")
public class MetadataCascadePolicy extends SerializableObject {

  /** The ID of the metadata cascade policy object. */
  protected final String id;

  /** The value will always be `metadata_cascade_policy`. */
  @JsonDeserialize(
      using = MetadataCascadePolicyTypeField.MetadataCascadePolicyTypeFieldDeserializer.class)
  @JsonSerialize(
      using = MetadataCascadePolicyTypeField.MetadataCascadePolicyTypeFieldSerializer.class)
  protected EnumWrapper<MetadataCascadePolicyTypeField> type;

  /** The enterprise that owns this policy. */
  @JsonProperty("owner_enterprise")
  protected MetadataCascadePolicyOwnerEnterpriseField ownerEnterprise;

  /** Represent the folder the policy is applied to. */
  protected MetadataCascadePolicyParentField parent;

  /**
   * The scope of the metadata cascade policy can either be `global` or `enterprise_*`. The `global`
   * scope is used for policies that are available to any Box enterprise. The `enterprise_*` scope
   * represents policies that have been created within a specific enterprise, where `*` will be the
   * ID of that enterprise.
   */
  protected String scope;

  /**
   * The key of the template that is cascaded down to the folder's children.
   *
   * <p>In many cases the template key is automatically derived of its display name, for example
   * `Contract Template` would become `contractTemplate`. In some cases the creator of the template
   * will have provided its own template key.
   *
   * <p>Please [list the templates for an enterprise][list], or get all instances on a [file][file]
   * or [folder][folder] to inspect a template's key.
   *
   * <p>[list]: https://developer.box.com/reference/get-metadata-templates-enterprise [file]:
   * https://developer.box.com/reference/get-files-id-metadata [folder]:
   * https://developer.box.com/reference/get-folders-id-metadata
   */
  protected String templateKey;

  public MetadataCascadePolicy(@JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type =
        new EnumWrapper<MetadataCascadePolicyTypeField>(
            MetadataCascadePolicyTypeField.METADATA_CASCADE_POLICY);
  }

  protected MetadataCascadePolicy(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.ownerEnterprise = builder.ownerEnterprise;
    this.parent = builder.parent;
    this.scope = builder.scope;
    this.templateKey = builder.templateKey;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<MetadataCascadePolicyTypeField> getType() {
    return type;
  }

  public MetadataCascadePolicyOwnerEnterpriseField getOwnerEnterprise() {
    return ownerEnterprise;
  }

  public MetadataCascadePolicyParentField getParent() {
    return parent;
  }

  public String getScope() {
    return scope;
  }

  public String getTemplateKey() {
    return templateKey;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MetadataCascadePolicy casted = (MetadataCascadePolicy) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(ownerEnterprise, casted.ownerEnterprise)
        && Objects.equals(parent, casted.parent)
        && Objects.equals(scope, casted.scope)
        && Objects.equals(templateKey, casted.templateKey);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, ownerEnterprise, parent, scope, templateKey);
  }

  @Override
  public String toString() {
    return "MetadataCascadePolicy{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "ownerEnterprise='"
        + ownerEnterprise
        + '\''
        + ", "
        + "parent='"
        + parent
        + '\''
        + ", "
        + "scope='"
        + scope
        + '\''
        + ", "
        + "templateKey='"
        + templateKey
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String id;

    protected EnumWrapper<MetadataCascadePolicyTypeField> type;

    protected MetadataCascadePolicyOwnerEnterpriseField ownerEnterprise;

    protected MetadataCascadePolicyParentField parent;

    protected String scope;

    protected String templateKey;

    public Builder(String id) {
      super();
      this.id = id;
    }

    public Builder type(MetadataCascadePolicyTypeField type) {
      this.type = new EnumWrapper<MetadataCascadePolicyTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<MetadataCascadePolicyTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder ownerEnterprise(MetadataCascadePolicyOwnerEnterpriseField ownerEnterprise) {
      this.ownerEnterprise = ownerEnterprise;
      return this;
    }

    public Builder parent(MetadataCascadePolicyParentField parent) {
      this.parent = parent;
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

    public MetadataCascadePolicy build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<MetadataCascadePolicyTypeField>(
                MetadataCascadePolicyTypeField.METADATA_CASCADE_POLICY);
      }
      return new MetadataCascadePolicy(this);
    }
  }
}
