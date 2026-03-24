package com.box.sdkgen.schemas.legalholdpolicyassigneditem;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/** The item that the legal hold policy is assigned to. Includes type and ID. */
@JsonFilter("nullablePropertyFilter")
public class LegalHoldPolicyAssignedItem extends SerializableObject {

  /** The type of item the policy is assigned to. */
  @JsonDeserialize(
      using =
          LegalHoldPolicyAssignedItemTypeField.LegalHoldPolicyAssignedItemTypeFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          LegalHoldPolicyAssignedItemTypeField.LegalHoldPolicyAssignedItemTypeFieldSerializer.class)
  protected final EnumWrapper<LegalHoldPolicyAssignedItemTypeField> type;

  /** The ID of the item the policy is assigned to. */
  protected final String id;

  public LegalHoldPolicyAssignedItem(LegalHoldPolicyAssignedItemTypeField type, String id) {
    super();
    this.type = new EnumWrapper<LegalHoldPolicyAssignedItemTypeField>(type);
    this.id = id;
  }

  public LegalHoldPolicyAssignedItem(
      @JsonProperty("type") EnumWrapper<LegalHoldPolicyAssignedItemTypeField> type,
      @JsonProperty("id") String id) {
    super();
    this.type = type;
    this.id = id;
  }

  public EnumWrapper<LegalHoldPolicyAssignedItemTypeField> getType() {
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
    LegalHoldPolicyAssignedItem casted = (LegalHoldPolicyAssignedItem) o;
    return Objects.equals(type, casted.type) && Objects.equals(id, casted.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id);
  }

  @Override
  public String toString() {
    return "LegalHoldPolicyAssignedItem{"
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
