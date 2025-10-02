package com.box.sdkgen.schemas.filefull;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class FileFullPermissionsField extends SerializableObject {

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

  /** Specifies if the user can place annotations on this file. */
  @JsonProperty("can_annotate")
  protected final boolean canAnnotate;

  /** Specifies if the user can place comments on this file. */
  @JsonProperty("can_comment")
  protected final boolean canComment;

  /** Specifies if the user can preview this file. */
  @JsonProperty("can_preview")
  protected final boolean canPreview;

  /** Specifies if the user can upload a new version of this file. */
  @JsonProperty("can_upload")
  protected final boolean canUpload;

  /** Specifies if the user view all annotations placed on this file. */
  @JsonProperty("can_view_annotations_all")
  protected final boolean canViewAnnotationsAll;

  /** Specifies if the user view annotations placed by themselves on this file. */
  @JsonProperty("can_view_annotations_self")
  protected final boolean canViewAnnotationsSelf;

  public FileFullPermissionsField(
      @JsonProperty("can_delete") boolean canDelete,
      @JsonProperty("can_download") boolean canDownload,
      @JsonProperty("can_invite_collaborator") boolean canInviteCollaborator,
      @JsonProperty("can_rename") boolean canRename,
      @JsonProperty("can_set_share_access") boolean canSetShareAccess,
      @JsonProperty("can_share") boolean canShare,
      @JsonProperty("can_annotate") boolean canAnnotate,
      @JsonProperty("can_comment") boolean canComment,
      @JsonProperty("can_preview") boolean canPreview,
      @JsonProperty("can_upload") boolean canUpload,
      @JsonProperty("can_view_annotations_all") boolean canViewAnnotationsAll,
      @JsonProperty("can_view_annotations_self") boolean canViewAnnotationsSelf) {
    super();
    this.canDelete = canDelete;
    this.canDownload = canDownload;
    this.canInviteCollaborator = canInviteCollaborator;
    this.canRename = canRename;
    this.canSetShareAccess = canSetShareAccess;
    this.canShare = canShare;
    this.canAnnotate = canAnnotate;
    this.canComment = canComment;
    this.canPreview = canPreview;
    this.canUpload = canUpload;
    this.canViewAnnotationsAll = canViewAnnotationsAll;
    this.canViewAnnotationsSelf = canViewAnnotationsSelf;
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

  public boolean getCanAnnotate() {
    return canAnnotate;
  }

  public boolean getCanComment() {
    return canComment;
  }

  public boolean getCanPreview() {
    return canPreview;
  }

  public boolean getCanUpload() {
    return canUpload;
  }

  public boolean getCanViewAnnotationsAll() {
    return canViewAnnotationsAll;
  }

  public boolean getCanViewAnnotationsSelf() {
    return canViewAnnotationsSelf;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FileFullPermissionsField casted = (FileFullPermissionsField) o;
    return Objects.equals(canDelete, casted.canDelete)
        && Objects.equals(canDownload, casted.canDownload)
        && Objects.equals(canInviteCollaborator, casted.canInviteCollaborator)
        && Objects.equals(canRename, casted.canRename)
        && Objects.equals(canSetShareAccess, casted.canSetShareAccess)
        && Objects.equals(canShare, casted.canShare)
        && Objects.equals(canAnnotate, casted.canAnnotate)
        && Objects.equals(canComment, casted.canComment)
        && Objects.equals(canPreview, casted.canPreview)
        && Objects.equals(canUpload, casted.canUpload)
        && Objects.equals(canViewAnnotationsAll, casted.canViewAnnotationsAll)
        && Objects.equals(canViewAnnotationsSelf, casted.canViewAnnotationsSelf);
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
        canAnnotate,
        canComment,
        canPreview,
        canUpload,
        canViewAnnotationsAll,
        canViewAnnotationsSelf);
  }

  @Override
  public String toString() {
    return "FileFullPermissionsField{"
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
        + "canAnnotate='"
        + canAnnotate
        + '\''
        + ", "
        + "canComment='"
        + canComment
        + '\''
        + ", "
        + "canPreview='"
        + canPreview
        + '\''
        + ", "
        + "canUpload='"
        + canUpload
        + '\''
        + ", "
        + "canViewAnnotationsAll='"
        + canViewAnnotationsAll
        + '\''
        + ", "
        + "canViewAnnotationsSelf='"
        + canViewAnnotationsSelf
        + '\''
        + "}";
  }
}
