package com.box.sdkgen.schemas.collaborationallowlistentry;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CollaborationAllowlistEntryEnterpriseField extends SerializableObject {

  protected String id;

  @JsonDeserialize(
      using =
          CollaborationAllowlistEntryEnterpriseTypeField
              .CollaborationAllowlistEntryEnterpriseTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          CollaborationAllowlistEntryEnterpriseTypeField
              .CollaborationAllowlistEntryEnterpriseTypeFieldSerializer.class)
  protected EnumWrapper<CollaborationAllowlistEntryEnterpriseTypeField> type;

  protected String name;

  public CollaborationAllowlistEntryEnterpriseField() {
    super();
  }

  protected CollaborationAllowlistEntryEnterpriseField(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.name = builder.name;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<CollaborationAllowlistEntryEnterpriseTypeField> getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CollaborationAllowlistEntryEnterpriseField casted =
        (CollaborationAllowlistEntryEnterpriseField) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(name, casted.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, name);
  }

  @Override
  public String toString() {
    return "CollaborationAllowlistEntryEnterpriseField{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "name='"
        + name
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String id;

    protected EnumWrapper<CollaborationAllowlistEntryEnterpriseTypeField> type;

    protected String name;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder type(CollaborationAllowlistEntryEnterpriseTypeField type) {
      this.type = new EnumWrapper<CollaborationAllowlistEntryEnterpriseTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<CollaborationAllowlistEntryEnterpriseTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public CollaborationAllowlistEntryEnterpriseField build() {
      return new CollaborationAllowlistEntryEnterpriseField(this);
    }
  }
}
