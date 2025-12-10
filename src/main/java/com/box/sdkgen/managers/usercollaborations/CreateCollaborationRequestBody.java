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
public class CreateCollaborationRequestBody extends SerializableObject {

  /** The item to attach the comment to. */
  protected final CreateCollaborationRequestBodyItemField item;

  /** The user or group to give access to the item. */
  @JsonProperty("accessible_by")
  protected final CreateCollaborationRequestBodyAccessibleByField accessibleBy;

  /** The level of access granted. */
  @JsonDeserialize(
      using =
          CreateCollaborationRequestBodyRoleField
              .CreateCollaborationRequestBodyRoleFieldDeserializer.class)
  @JsonSerialize(
      using =
          CreateCollaborationRequestBodyRoleField.CreateCollaborationRequestBodyRoleFieldSerializer
              .class)
  protected final EnumWrapper<CreateCollaborationRequestBodyRoleField> role;

  /**
   * If set to `true`, collaborators have access to shared items, but such items won't be visible in
   * the All Files list. Additionally, collaborators won't see the path to the root folder for the
   * shared item.
   */
  @JsonProperty("is_access_only")
  protected Boolean isAccessOnly;

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

  /**
   * Set the expiration date for the collaboration. At this date, the collaboration will be
   * automatically removed from the item.
   *
   * <p>This feature will only work if the **Automatically remove invited collaborators: Allow
   * folder owners to extend the expiry date** setting has been enabled in the **Enterprise
   * Settings** of the **Admin Console**. When the setting is not enabled, collaborations can not
   * have an expiry date and a value for this field will be result in an error.
   */
  @JsonProperty("expires_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime expiresAt;

  public CreateCollaborationRequestBody(
      CreateCollaborationRequestBodyItemField item,
      CreateCollaborationRequestBodyAccessibleByField accessibleBy,
      CreateCollaborationRequestBodyRoleField role) {
    super();
    this.item = item;
    this.accessibleBy = accessibleBy;
    this.role = new EnumWrapper<CreateCollaborationRequestBodyRoleField>(role);
  }

  public CreateCollaborationRequestBody(
      @JsonProperty("item") CreateCollaborationRequestBodyItemField item,
      @JsonProperty("accessible_by") CreateCollaborationRequestBodyAccessibleByField accessibleBy,
      @JsonProperty("role") EnumWrapper<CreateCollaborationRequestBodyRoleField> role) {
    super();
    this.item = item;
    this.accessibleBy = accessibleBy;
    this.role = role;
  }

  protected CreateCollaborationRequestBody(Builder builder) {
    super();
    this.item = builder.item;
    this.accessibleBy = builder.accessibleBy;
    this.role = builder.role;
    this.isAccessOnly = builder.isAccessOnly;
    this.canViewPath = builder.canViewPath;
    this.expiresAt = builder.expiresAt;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public CreateCollaborationRequestBodyItemField getItem() {
    return item;
  }

  public CreateCollaborationRequestBodyAccessibleByField getAccessibleBy() {
    return accessibleBy;
  }

  public EnumWrapper<CreateCollaborationRequestBodyRoleField> getRole() {
    return role;
  }

  public Boolean getIsAccessOnly() {
    return isAccessOnly;
  }

  public Boolean getCanViewPath() {
    return canViewPath;
  }

  public OffsetDateTime getExpiresAt() {
    return expiresAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateCollaborationRequestBody casted = (CreateCollaborationRequestBody) o;
    return Objects.equals(item, casted.item)
        && Objects.equals(accessibleBy, casted.accessibleBy)
        && Objects.equals(role, casted.role)
        && Objects.equals(isAccessOnly, casted.isAccessOnly)
        && Objects.equals(canViewPath, casted.canViewPath)
        && Objects.equals(expiresAt, casted.expiresAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(item, accessibleBy, role, isAccessOnly, canViewPath, expiresAt);
  }

  @Override
  public String toString() {
    return "CreateCollaborationRequestBody{"
        + "item='"
        + item
        + '\''
        + ", "
        + "accessibleBy='"
        + accessibleBy
        + '\''
        + ", "
        + "role='"
        + role
        + '\''
        + ", "
        + "isAccessOnly='"
        + isAccessOnly
        + '\''
        + ", "
        + "canViewPath='"
        + canViewPath
        + '\''
        + ", "
        + "expiresAt='"
        + expiresAt
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final CreateCollaborationRequestBodyItemField item;

    protected final CreateCollaborationRequestBodyAccessibleByField accessibleBy;

    protected final EnumWrapper<CreateCollaborationRequestBodyRoleField> role;

    protected Boolean isAccessOnly;

    protected Boolean canViewPath;

    protected OffsetDateTime expiresAt;

    public Builder(
        CreateCollaborationRequestBodyItemField item,
        CreateCollaborationRequestBodyAccessibleByField accessibleBy,
        CreateCollaborationRequestBodyRoleField role) {
      super();
      this.item = item;
      this.accessibleBy = accessibleBy;
      this.role = new EnumWrapper<CreateCollaborationRequestBodyRoleField>(role);
    }

    public Builder(
        CreateCollaborationRequestBodyItemField item,
        CreateCollaborationRequestBodyAccessibleByField accessibleBy,
        EnumWrapper<CreateCollaborationRequestBodyRoleField> role) {
      super();
      this.item = item;
      this.accessibleBy = accessibleBy;
      this.role = role;
    }

    public Builder isAccessOnly(Boolean isAccessOnly) {
      this.isAccessOnly = isAccessOnly;
      return this;
    }

    public Builder canViewPath(Boolean canViewPath) {
      this.canViewPath = canViewPath;
      return this;
    }

    public Builder expiresAt(OffsetDateTime expiresAt) {
      this.expiresAt = expiresAt;
      return this;
    }

    public CreateCollaborationRequestBody build() {
      return new CreateCollaborationRequestBody(this);
    }
  }
}
