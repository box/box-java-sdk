package com.box.sdkgen.schemas.groupfull;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class GroupFullPermissionsField extends SerializableObject {

  @JsonProperty("can_invite_as_collaborator")
  protected Boolean canInviteAsCollaborator;

  public GroupFullPermissionsField() {
    super();
  }

  protected GroupFullPermissionsField(GroupFullPermissionsFieldBuilder builder) {
    super();
    this.canInviteAsCollaborator = builder.canInviteAsCollaborator;
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

  public static class GroupFullPermissionsFieldBuilder {

    protected Boolean canInviteAsCollaborator;

    public GroupFullPermissionsFieldBuilder canInviteAsCollaborator(
        Boolean canInviteAsCollaborator) {
      this.canInviteAsCollaborator = canInviteAsCollaborator;
      return this;
    }

    public GroupFullPermissionsField build() {
      return new GroupFullPermissionsField(this);
    }
  }
}
