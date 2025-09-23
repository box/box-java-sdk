package com.box.sdkgen.schemas.metadatacascadepolicy;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class MetadataCascadePolicy extends SerializableObject {

  protected final String id;

  @JsonDeserialize(
      using = MetadataCascadePolicyTypeField.MetadataCascadePolicyTypeFieldDeserializer.class)
  @JsonSerialize(
      using = MetadataCascadePolicyTypeField.MetadataCascadePolicyTypeFieldSerializer.class)
  protected EnumWrapper<MetadataCascadePolicyTypeField> type;

  @JsonProperty("owner_enterprise")
  protected MetadataCascadePolicyOwnerEnterpriseField ownerEnterprise;

  protected MetadataCascadePolicyParentField parent;

  protected String scope;

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
      this.type =
          new EnumWrapper<MetadataCascadePolicyTypeField>(
              MetadataCascadePolicyTypeField.METADATA_CASCADE_POLICY);
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
      return new MetadataCascadePolicy(this);
    }
  }
}
