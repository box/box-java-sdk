package com.box.sdkgen.managers.collaborationallowlistexempttargets;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CreateCollaborationWhitelistExemptTargetRequestBodyUserField
    extends SerializableObject {

  /** The ID of the user to exempt. */
  protected final String id;

  public CreateCollaborationWhitelistExemptTargetRequestBodyUserField(
      @JsonProperty("id") String id) {
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
    CreateCollaborationWhitelistExemptTargetRequestBodyUserField casted =
        (CreateCollaborationWhitelistExemptTargetRequestBodyUserField) o;
    return Objects.equals(id, casted.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "CreateCollaborationWhitelistExemptTargetRequestBodyUserField{"
        + "id='"
        + id
        + '\''
        + "}";
  }
}
