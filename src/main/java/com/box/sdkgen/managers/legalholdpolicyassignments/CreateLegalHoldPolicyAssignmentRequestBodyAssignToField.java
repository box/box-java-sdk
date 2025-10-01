package com.box.sdkgen.managers.legalholdpolicyassignments;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CreateLegalHoldPolicyAssignmentRequestBodyAssignToField extends SerializableObject {

  /** The type of item to assign the policy to. */
  @JsonDeserialize(
      using =
          CreateLegalHoldPolicyAssignmentRequestBodyAssignToTypeField
              .CreateLegalHoldPolicyAssignmentRequestBodyAssignToTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          CreateLegalHoldPolicyAssignmentRequestBodyAssignToTypeField
              .CreateLegalHoldPolicyAssignmentRequestBodyAssignToTypeFieldSerializer.class)
  protected final EnumWrapper<CreateLegalHoldPolicyAssignmentRequestBodyAssignToTypeField> type;

  /** The ID of item to assign the policy to. */
  protected final String id;

  public CreateLegalHoldPolicyAssignmentRequestBodyAssignToField(
      CreateLegalHoldPolicyAssignmentRequestBodyAssignToTypeField type, String id) {
    super();
    this.type = new EnumWrapper<CreateLegalHoldPolicyAssignmentRequestBodyAssignToTypeField>(type);
    this.id = id;
  }

  public CreateLegalHoldPolicyAssignmentRequestBodyAssignToField(
      @JsonProperty("type")
          EnumWrapper<CreateLegalHoldPolicyAssignmentRequestBodyAssignToTypeField> type,
      @JsonProperty("id") String id) {
    super();
    this.type = type;
    this.id = id;
  }

  public EnumWrapper<CreateLegalHoldPolicyAssignmentRequestBodyAssignToTypeField> getType() {
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
    CreateLegalHoldPolicyAssignmentRequestBodyAssignToField casted =
        (CreateLegalHoldPolicyAssignmentRequestBodyAssignToField) o;
    return Objects.equals(type, casted.type) && Objects.equals(id, casted.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id);
  }

  @Override
  public String toString() {
    return "CreateLegalHoldPolicyAssignmentRequestBodyAssignToField{"
        + "type='"
        + type
        + '\''
        + ", "
        + "id='"
        + id
        + '\''
        + "}";
  }
}
