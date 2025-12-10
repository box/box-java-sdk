package com.box.sdkgen.managers.usercollaborations;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.OffsetDateTime;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class UpdateCollaborationByIdRequestBody extends SerializableObject {

  /** The level of access granted. */
  @JsonDeserialize(
      using =
          UpdateCollaborationByIdRequestBodyRoleField
              .UpdateCollaborationByIdRequestBodyRoleFieldDeserializer.class)
  @JsonSerialize(
      using =
          UpdateCollaborationByIdRequestBodyRoleField
              .UpdateCollaborationByIdRequestBodyRoleFieldSerializer.class)
  protected EnumWrapper<UpdateCollaborationByIdRequestBodyRoleField> role;

  /**
   * Set the status of a `pending` collaboration invitation, effectively accepting, or rejecting the
   * invite.
   */
  @JsonDeserialize(
      using =
          UpdateCollaborationByIdRequestBodyStatusField
              .UpdateCollaborationByIdRequestBodyStatusFieldDeserializer.class)
  @JsonSerialize(
      using =
          UpdateCollaborationByIdRequestBodyStatusField
              .UpdateCollaborationByIdRequestBodyStatusFieldSerializer.class)
  protected EnumWrapper<UpdateCollaborationByIdRequestBodyStatusField> status;

  /**
   * Update the expiration date for the collaboration. At this date, the collaboration will be
   * automatically removed from the item.
   *
   * <p>This feature will only work if the **Automatically remove invited collaborators: Allow
   * folder owners to extend the expiry date** setting has been enabled in the **Enterprise
   * Settings** of the **Admin Console**. When the setting is not enabled, collaborations can not
   * have an expiry date and a value for this field will be result in an error.
   *
   * <p>Additionally, a collaboration can only be given an expiration if it was created after the
   * **Automatically remove invited collaborator** setting was enabled.
   */
  @JsonProperty("expires_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime expiresAt;

  /**
   * Determines if the invited users can see the entire parent path to the associated folder. The
   * user will not gain privileges in any parent folder and therefore can not see content the user
   * is not collaborated on.
   *
   * <p>Be aware that this meaningfully increases the time required to load the invitee's **All
   * Files** page. We recommend you limit the number of collaborations with `can_view_path` enabled
   * to 1,000 per user.
   *
   * <p>Only an owner or co-owners can invite collaborators with a `can_view_path` of `true`. Only
   * an owner can update `can_view_path` on existing collaborations.
   *
   * <p>`can_view_path` can only be used for folder collaborations.
   *
   * <p>When you delete a folder with `can_view_path=true`, collaborators may still see the parent
   * path. For instructions on how to remove this, see [Even though a folder invited via
   * can_view_path is deleted, the path remains
   * displayed](https://support.box.com/hc/en-us/articles/37472814319891-Even-though-a-folder-invited-via-can-view-path-is-deleted-the-path-remains-displayed).
   */
  @JsonProperty("can_view_path")
  protected Boolean canViewPath;

  public UpdateCollaborationByIdRequestBody() {
    super();
  }

  protected UpdateCollaborationByIdRequestBody(Builder builder) {
    super();
    this.role = builder.role;
    this.status = builder.status;
    this.expiresAt = builder.expiresAt;
    this.canViewPath = builder.canViewPath;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<UpdateCollaborationByIdRequestBodyRoleField> getRole() {
    return role;
  }

  public EnumWrapper<UpdateCollaborationByIdRequestBodyStatusField> getStatus() {
    return status;
  }

  public OffsetDateTime getExpiresAt() {
    return expiresAt;
  }

  public Boolean getCanViewPath() {
    return canViewPath;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateCollaborationByIdRequestBody casted = (UpdateCollaborationByIdRequestBody) o;
    return Objects.equals(role, casted.role)
        && Objects.equals(status, casted.status)
        && Objects.equals(expiresAt, casted.expiresAt)
        && Objects.equals(canViewPath, casted.canViewPath);
  }

  @Override
  public int hashCode() {
    return Objects.hash(role, status, expiresAt, canViewPath);
  }

  @Override
  public String toString() {
    return "UpdateCollaborationByIdRequestBody{"
        + "role='"
        + role
        + '\''
        + ", "
        + "status='"
        + status
        + '\''
        + ", "
        + "expiresAt='"
        + expiresAt
        + '\''
        + ", "
        + "canViewPath='"
        + canViewPath
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<UpdateCollaborationByIdRequestBodyRoleField> role;

    protected EnumWrapper<UpdateCollaborationByIdRequestBodyStatusField> status;

    protected OffsetDateTime expiresAt;

    protected Boolean canViewPath;

    public Builder role(UpdateCollaborationByIdRequestBodyRoleField role) {
      this.role = new EnumWrapper<UpdateCollaborationByIdRequestBodyRoleField>(role);
      return this;
    }

    public Builder role(EnumWrapper<UpdateCollaborationByIdRequestBodyRoleField> role) {
      this.role = role;
      return this;
    }

    public Builder status(UpdateCollaborationByIdRequestBodyStatusField status) {
      this.status = new EnumWrapper<UpdateCollaborationByIdRequestBodyStatusField>(status);
      return this;
    }

    public Builder status(EnumWrapper<UpdateCollaborationByIdRequestBodyStatusField> status) {
      this.status = status;
      return this;
    }

    public Builder expiresAt(OffsetDateTime expiresAt) {
      this.expiresAt = expiresAt;
      return this;
    }

    public Builder canViewPath(Boolean canViewPath) {
      this.canViewPath = canViewPath;
      return this;
    }

    public UpdateCollaborationByIdRequestBody build() {
      return new UpdateCollaborationByIdRequestBody(this);
    }
  }
}
