package com.box.sdkgen.schemas.collaborationallowlistexempttarget;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class CollaborationAllowlistExemptTargetEnterpriseField extends SerializableObject {

  protected String id;

  @JsonDeserialize(
      using =
          CollaborationAllowlistExemptTargetEnterpriseTypeField
              .CollaborationAllowlistExemptTargetEnterpriseTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          CollaborationAllowlistExemptTargetEnterpriseTypeField
              .CollaborationAllowlistExemptTargetEnterpriseTypeFieldSerializer.class)
  protected EnumWrapper<CollaborationAllowlistExemptTargetEnterpriseTypeField> type;

  protected String name;

  public CollaborationAllowlistExemptTargetEnterpriseField() {
    super();
  }

  protected CollaborationAllowlistExemptTargetEnterpriseField(
      CollaborationAllowlistExemptTargetEnterpriseFieldBuilder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.name = builder.name;
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<CollaborationAllowlistExemptTargetEnterpriseTypeField> getType() {
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
    CollaborationAllowlistExemptTargetEnterpriseField casted =
        (CollaborationAllowlistExemptTargetEnterpriseField) o;
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
    return "CollaborationAllowlistExemptTargetEnterpriseField{"
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

  public static class CollaborationAllowlistExemptTargetEnterpriseFieldBuilder {

    protected String id;

    protected EnumWrapper<CollaborationAllowlistExemptTargetEnterpriseTypeField> type;

    protected String name;

    public CollaborationAllowlistExemptTargetEnterpriseFieldBuilder id(String id) {
      this.id = id;
      return this;
    }

    public CollaborationAllowlistExemptTargetEnterpriseFieldBuilder type(
        CollaborationAllowlistExemptTargetEnterpriseTypeField type) {
      this.type = new EnumWrapper<CollaborationAllowlistExemptTargetEnterpriseTypeField>(type);
      return this;
    }

    public CollaborationAllowlistExemptTargetEnterpriseFieldBuilder type(
        EnumWrapper<CollaborationAllowlistExemptTargetEnterpriseTypeField> type) {
      this.type = type;
      return this;
    }

    public CollaborationAllowlistExemptTargetEnterpriseFieldBuilder name(String name) {
      this.name = name;
      return this;
    }

    public CollaborationAllowlistExemptTargetEnterpriseField build() {
      return new CollaborationAllowlistExemptTargetEnterpriseField(this);
    }
  }
}
