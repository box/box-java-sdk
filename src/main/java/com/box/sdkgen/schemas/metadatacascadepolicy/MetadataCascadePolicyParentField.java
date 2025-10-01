package com.box.sdkgen.schemas.metadatacascadepolicy;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class MetadataCascadePolicyParentField extends SerializableObject {

  /** The value will always be `folder`. */
  @JsonDeserialize(
      using =
          MetadataCascadePolicyParentTypeField.MetadataCascadePolicyParentTypeFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          MetadataCascadePolicyParentTypeField.MetadataCascadePolicyParentTypeFieldSerializer.class)
  protected EnumWrapper<MetadataCascadePolicyParentTypeField> type;

  /** The ID of the folder the policy is applied to. */
  protected String id;

  public MetadataCascadePolicyParentField() {
    super();
  }

  protected MetadataCascadePolicyParentField(Builder builder) {
    super();
    this.type = builder.type;
    this.id = builder.id;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<MetadataCascadePolicyParentTypeField> getType() {
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
    MetadataCascadePolicyParentField casted = (MetadataCascadePolicyParentField) o;
    return Objects.equals(type, casted.type) && Objects.equals(id, casted.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id);
  }

  @Override
  public String toString() {
    return "MetadataCascadePolicyParentField{"
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

    protected EnumWrapper<MetadataCascadePolicyParentTypeField> type;

    protected String id;

    public Builder type(MetadataCascadePolicyParentTypeField type) {
      this.type = new EnumWrapper<MetadataCascadePolicyParentTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<MetadataCascadePolicyParentTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public MetadataCascadePolicyParentField build() {
      return new MetadataCascadePolicyParentField(this);
    }
  }
}
