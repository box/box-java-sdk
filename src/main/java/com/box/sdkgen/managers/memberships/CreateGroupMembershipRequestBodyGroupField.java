package com.box.sdkgen.managers.memberships;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CreateGroupMembershipRequestBodyGroupField extends SerializableObject {

  /** The ID of the group to add the user to. */
  protected final String id;

  public CreateGroupMembershipRequestBodyGroupField(@JsonProperty("id") String id) {
    super();
    this.id = id;
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
    CreateGroupMembershipRequestBodyGroupField casted =
        (CreateGroupMembershipRequestBodyGroupField) o;
    return Objects.equals(id, casted.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "CreateGroupMembershipRequestBodyGroupField{" + "id='" + id + '\'' + "}";
  }
}
