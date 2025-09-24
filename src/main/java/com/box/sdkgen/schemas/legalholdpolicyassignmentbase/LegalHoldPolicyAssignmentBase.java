package com.box.sdkgen.schemas.legalholdpolicyassignmentbase;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class LegalHoldPolicyAssignmentBase extends SerializableObject {

  protected String id;

  @JsonDeserialize(
      using =
          LegalHoldPolicyAssignmentBaseTypeField.LegalHoldPolicyAssignmentBaseTypeFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          LegalHoldPolicyAssignmentBaseTypeField.LegalHoldPolicyAssignmentBaseTypeFieldSerializer
              .class)
  protected EnumWrapper<LegalHoldPolicyAssignmentBaseTypeField> type;

  public LegalHoldPolicyAssignmentBase() {
    super();
  }

  protected LegalHoldPolicyAssignmentBase(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<LegalHoldPolicyAssignmentBaseTypeField> getType() {
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
    LegalHoldPolicyAssignmentBase casted = (LegalHoldPolicyAssignmentBase) o;
    return Objects.equals(id, casted.id) && Objects.equals(type, casted.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type);
  }

  @Override
  public String toString() {
    return "LegalHoldPolicyAssignmentBase{"
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

    protected EnumWrapper<LegalHoldPolicyAssignmentBaseTypeField> type;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder type(LegalHoldPolicyAssignmentBaseTypeField type) {
      this.type = new EnumWrapper<LegalHoldPolicyAssignmentBaseTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<LegalHoldPolicyAssignmentBaseTypeField> type) {
      this.type = type;
      return this;
    }

    public LegalHoldPolicyAssignmentBase build() {
      return new LegalHoldPolicyAssignmentBase(this);
    }
  }
}
