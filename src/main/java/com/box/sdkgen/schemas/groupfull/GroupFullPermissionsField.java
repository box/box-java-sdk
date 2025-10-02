package com.box.sdkgen.schemas.groupfull;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class GroupFullPermissionsField extends SerializableObject {

  /** Specifies if the user can invite the group to collaborate on any items. */
  @JsonProperty("can_invite_as_collaborator")
  protected Boolean canInviteAsCollaborator;

  public GroupFullPermissionsField() {
    super();
  }

  protected GroupFullPermissionsField(Builder builder) {
    super();
    this.canInviteAsCollaborator = builder.canInviteAsCollaborator;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public Boolean getCanInviteAsCollaborator() {
    return canInviteAsCollaborator;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GroupFullPermissionsField casted = (GroupFullPermissionsField) o;
    return Objects.equals(canInviteAsCollaborator, casted.canInviteAsCollaborator);
  }

  @Override
  public int hashCode() {
    return Objects.hash(canInviteAsCollaborator);
  }

  @Override
  public String toString() {
    return "GroupFullPermissionsField{"
        + "canInviteAsCollaborator='"
        + canInviteAsCollaborator
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected Boolean canInviteAsCollaborator;

    public Builder canInviteAsCollaborator(Boolean canInviteAsCollaborator) {
      this.canInviteAsCollaborator = canInviteAsCollaborator;
      return this;
    }

    public GroupFullPermissionsField build() {
      return new GroupFullPermissionsField(this);
    }
  }
}
