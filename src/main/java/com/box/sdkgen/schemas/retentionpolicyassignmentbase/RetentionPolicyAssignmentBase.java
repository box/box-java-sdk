package com.box.sdkgen.schemas.retentionpolicyassignmentbase;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class RetentionPolicyAssignmentBase extends SerializableObject {

  protected final String id;

  @JsonDeserialize(
      using =
          RetentionPolicyAssignmentBaseTypeField.RetentionPolicyAssignmentBaseTypeFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          RetentionPolicyAssignmentBaseTypeField.RetentionPolicyAssignmentBaseTypeFieldSerializer
              .class)
  protected EnumWrapper<RetentionPolicyAssignmentBaseTypeField> type;

  public RetentionPolicyAssignmentBase(@JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type =
        new EnumWrapper<RetentionPolicyAssignmentBaseTypeField>(
            RetentionPolicyAssignmentBaseTypeField.RETENTION_POLICY_ASSIGNMENT);
  }

  protected RetentionPolicyAssignmentBase(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<RetentionPolicyAssignmentBaseTypeField> getType() {
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
    RetentionPolicyAssignmentBase casted = (RetentionPolicyAssignmentBase) o;
    return Objects.equals(id, casted.id) && Objects.equals(type, casted.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type);
  }

  @Override
  public String toString() {
    return "RetentionPolicyAssignmentBase{"
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

    protected final String id;

    protected EnumWrapper<RetentionPolicyAssignmentBaseTypeField> type;

    public Builder(String id) {
      super();
      this.id = id;
      this.type =
          new EnumWrapper<RetentionPolicyAssignmentBaseTypeField>(
              RetentionPolicyAssignmentBaseTypeField.RETENTION_POLICY_ASSIGNMENT);
    }

    public Builder type(RetentionPolicyAssignmentBaseTypeField type) {
      this.type = new EnumWrapper<RetentionPolicyAssignmentBaseTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<RetentionPolicyAssignmentBaseTypeField> type) {
      this.type = type;
      return this;
    }

    public RetentionPolicyAssignmentBase build() {
      return new RetentionPolicyAssignmentBase(this);
    }
  }
}
