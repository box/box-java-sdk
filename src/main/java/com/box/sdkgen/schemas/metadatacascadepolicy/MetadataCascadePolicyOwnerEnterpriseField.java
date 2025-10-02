package com.box.sdkgen.schemas.metadatacascadepolicy;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class MetadataCascadePolicyOwnerEnterpriseField extends SerializableObject {

  /** The value will always be `enterprise`. */
  @JsonDeserialize(
      using =
          MetadataCascadePolicyOwnerEnterpriseTypeField
              .MetadataCascadePolicyOwnerEnterpriseTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          MetadataCascadePolicyOwnerEnterpriseTypeField
              .MetadataCascadePolicyOwnerEnterpriseTypeFieldSerializer.class)
  protected EnumWrapper<MetadataCascadePolicyOwnerEnterpriseTypeField> type;

  /** The ID of the enterprise that owns the policy. */
  protected String id;

  public MetadataCascadePolicyOwnerEnterpriseField() {
    super();
  }

  protected MetadataCascadePolicyOwnerEnterpriseField(Builder builder) {
    super();
    this.type = builder.type;
    this.id = builder.id;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<MetadataCascadePolicyOwnerEnterpriseTypeField> getType() {
    return type;
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
    MetadataCascadePolicyOwnerEnterpriseField casted =
        (MetadataCascadePolicyOwnerEnterpriseField) o;
    return Objects.equals(type, casted.type) && Objects.equals(id, casted.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id);
  }

  @Override
  public String toString() {
    return "MetadataCascadePolicyOwnerEnterpriseField{"
        + "type='"
        + type
        + '\''
        + ", "
        + "id='"
        + id
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<MetadataCascadePolicyOwnerEnterpriseTypeField> type;

    protected String id;

    public Builder type(MetadataCascadePolicyOwnerEnterpriseTypeField type) {
      this.type = new EnumWrapper<MetadataCascadePolicyOwnerEnterpriseTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<MetadataCascadePolicyOwnerEnterpriseTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public MetadataCascadePolicyOwnerEnterpriseField build() {
      return new MetadataCascadePolicyOwnerEnterpriseField(this);
    }
  }
}
