package com.box.sdkgen.schemas.retentionpolicyassignment;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class RetentionPolicyAssignmentAssignedToField extends SerializableObject {

  /**
   * The ID of the folder, enterprise, or metadata template the policy is assigned to. Set to null
   * or omit when type is set to enterprise.
   */
  @Nullable protected String id;

  /** The type of resource the policy is assigned to. */
  @JsonDeserialize(
      using =
          RetentionPolicyAssignmentAssignedToTypeField
              .RetentionPolicyAssignmentAssignedToTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          RetentionPolicyAssignmentAssignedToTypeField
              .RetentionPolicyAssignmentAssignedToTypeFieldSerializer.class)
  protected EnumWrapper<RetentionPolicyAssignmentAssignedToTypeField> type;

  public RetentionPolicyAssignmentAssignedToField() {
    super();
  }

  protected RetentionPolicyAssignmentAssignedToField(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<RetentionPolicyAssignmentAssignedToTypeField> getType() {
    return type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RetentionPolicyAssignmentAssignedToField casted = (RetentionPolicyAssignmentAssignedToField) o;
    return Objects.equals(id, casted.id) && Objects.equals(type, casted.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type);
  }

  @Override
  public String toString() {
    return "RetentionPolicyAssignmentAssignedToField{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String id;

    protected EnumWrapper<RetentionPolicyAssignmentAssignedToTypeField> type;

    public Builder id(String id) {
      this.id = id;
      this.markNullableFieldAsSet("id");
      return this;
    }

    public Builder type(RetentionPolicyAssignmentAssignedToTypeField type) {
      this.type = new EnumWrapper<RetentionPolicyAssignmentAssignedToTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<RetentionPolicyAssignmentAssignedToTypeField> type) {
      this.type = type;
      return this;
    }

    public RetentionPolicyAssignmentAssignedToField build() {
      return new RetentionPolicyAssignmentAssignedToField(this);
    }
  }
}
