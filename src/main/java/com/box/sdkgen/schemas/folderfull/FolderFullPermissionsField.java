package com.box.sdkgen.schemas.folderfull;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class FolderFullPermissionsField extends SerializableObject {

  /** Specifies if the current user can delete this item. */
  @JsonProperty("can_delete")
  protected final boolean canDelete;

  /** Specifies if the current user can download this item. */
  @JsonProperty("can_download")
  protected final boolean canDownload;

  /**
   * Specifies if the current user can invite new users to collaborate on this item, and if the user
   * can update the role of a user already collaborated on this item.
   */
  @JsonProperty("can_invite_collaborator")
  protected final boolean canInviteCollaborator;

  /** Specifies if the user can rename this item. */
  @JsonProperty("can_rename")
  protected final boolean canRename;

  /** Specifies if the user can change the access level of an existing shared link on this item. */
  @JsonProperty("can_set_share_access")
  protected final boolean canSetShareAccess;

  /** Specifies if the user can create a shared link for this item. */
  @JsonProperty("can_share")
  protected final boolean canShare;

  /** Specifies if the user can upload into this folder. */
  @JsonProperty("can_upload")
  protected final boolean canUpload;

  /** Specifies if the user can apply a watermark to this folder and its contents. */
  @JsonProperty("can_apply_watermark")
  protected Boolean canApplyWatermark;

  public FolderFullPermissionsField(
      @JsonProperty("can_delete") boolean canDelete,
      @JsonProperty("can_download") boolean canDownload,
      @JsonProperty("can_invite_collaborator") boolean canInviteCollaborator,
      @JsonProperty("can_rename") boolean canRename,
      @JsonProperty("can_set_share_access") boolean canSetShareAccess,
      @JsonProperty("can_share") boolean canShare,
      @JsonProperty("can_upload") boolean canUpload) {
    super();
    this.canDelete = canDelete;
    this.canDownload = canDownload;
    this.canInviteCollaborator = canInviteCollaborator;
    this.canRename = canRename;
    this.canSetShareAccess = canSetShareAccess;
    this.canShare = canShare;
    this.canUpload = canUpload;
  }

  protected FolderFullPermissionsField(Builder builder) {
    super();
    this.canDelete = builder.canDelete;
    this.canDownload = builder.canDownload;
    this.canInviteCollaborator = builder.canInviteCollaborator;
    this.canRename = builder.canRename;
    this.canSetShareAccess = builder.canSetShareAccess;
    this.canShare = builder.canShare;
    this.canUpload = builder.canUpload;
    this.canApplyWatermark = builder.canApplyWatermark;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public boolean getCanDelete() {
    return canDelete;
  }

  public boolean getCanDownload() {
    return canDownload;
  }

  public boolean getCanInviteCollaborator() {
    return canInviteCollaborator;
  }

  public boolean getCanRename() {
    return canRename;
  }

  public boolean getCanSetShareAccess() {
    return canSetShareAccess;
  }

  public boolean getCanShare() {
    return canShare;
  }

  public boolean getCanUpload() {
    return canUpload;
  }

  public Boolean getCanApplyWatermark() {
    return canApplyWatermark;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FolderFullPermissionsField casted = (FolderFullPermissionsField) o;
    return Objects.equals(canDelete, casted.canDelete)
        && Objects.equals(canDownload, casted.canDownload)
        && Objects.equals(canInviteCollaborator, casted.canInviteCollaborator)
        && Objects.equals(canRename, casted.canRename)
        && Objects.equals(canSetShareAccess, casted.canSetShareAccess)
        && Objects.equals(canShare, casted.canShare)
        && Objects.equals(canUpload, casted.canUpload)
        && Objects.equals(canApplyWatermark, casted.canApplyWatermark);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        canDelete,
        canDownload,
        canInviteCollaborator,
        canRename,
        canSetShareAccess,
        canShare,
        canUpload,
        canApplyWatermark);
  }

  @Override
  public String toString() {
    return "FolderFullPermissionsField{"
        + "canDelete='"
        + canDelete
        + '\''
        + ", "
        + "canDownload='"
        + canDownload
        + '\''
        + ", "
        + "canInviteCollaborator='"
        + canInviteCollaborator
        + '\''
        + ", "
        + "canRename='"
        + canRename
        + '\''
        + ", "
        + "canSetShareAccess='"
        + canSetShareAccess
        + '\''
        + ", "
        + "canShare='"
        + canShare
        + '\''
        + ", "
        + "canUpload='"
        + canUpload
        + '\''
        + ", "
        + "canApplyWatermark='"
        + canApplyWatermark
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final boolean canDelete;

    protected final boolean canDownload;

    protected final boolean canInviteCollaborator;

    protected final boolean canRename;

    protected final boolean canSetShareAccess;

    protected final boolean canShare;

    protected final boolean canUpload;

    protected Boolean canApplyWatermark;

    public Builder(
        boolean canDelete,
        boolean canDownload,
        boolean canInviteCollaborator,
        boolean canRename,
        boolean canSetShareAccess,
        boolean canShare,
        boolean canUpload) {
      super();
      this.canDelete = canDelete;
      this.canDownload = canDownload;
      this.canInviteCollaborator = canInviteCollaborator;
      this.canRename = canRename;
      this.canSetShareAccess = canSetShareAccess;
      this.canShare = canShare;
      this.canUpload = canUpload;
    }

    public Builder canApplyWatermark(Boolean canApplyWatermark) {
      this.canApplyWatermark = canApplyWatermark;
      return this;
    }

    public FolderFullPermissionsField build() {
      return new FolderFullPermissionsField(this);
    }
  }
}
