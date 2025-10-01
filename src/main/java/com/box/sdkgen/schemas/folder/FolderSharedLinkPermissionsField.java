package com.box.sdkgen.schemas.folder;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class FolderSharedLinkPermissionsField extends SerializableObject {

  /**
   * Defines if the shared link allows for the item to be downloaded. For shared links on folders,
   * this also applies to any items in the folder.
   *
   * <p>This value can be set to `true` when the effective access level is set to `open` or
   * `company`, not `collaborators`.
   */
  @JsonProperty("can_download")
  protected final boolean canDownload;

  /**
   * Defines if the shared link allows for the item to be previewed.
   *
   * <p>This value is always `true`. For shared links on folders this also applies to any items in
   * the folder.
   */
  @JsonProperty("can_preview")
  protected final boolean canPreview;

  /**
   * Defines if the shared link allows for the item to be edited.
   *
   * <p>This value can only be `true` if `can_download` is also `true` and if the item has a type of
   * `file`.
   */
  @JsonProperty("can_edit")
  protected final boolean canEdit;

  public FolderSharedLinkPermissionsField(
      @JsonProperty("can_download") boolean canDownload,
      @JsonProperty("can_preview") boolean canPreview,
      @JsonProperty("can_edit") boolean canEdit) {
    super();
    this.canDownload = canDownload;
    this.canPreview = canPreview;
    this.canEdit = canEdit;
  }

  public boolean getCanDownload() {
    return canDownload;
  }

  public boolean getCanPreview() {
    return canPreview;
  }

  public boolean getCanEdit() {
    return canEdit;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FolderSharedLinkPermissionsField casted = (FolderSharedLinkPermissionsField) o;
    return Objects.equals(canDownload, casted.canDownload)
        && Objects.equals(canPreview, casted.canPreview)
        && Objects.equals(canEdit, casted.canEdit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(canDownload, canPreview, canEdit);
  }

  @Override
  public String toString() {
    return "FolderSharedLinkPermissionsField{"
        + "canDownload='"
        + canDownload
        + '\''
        + ", "
        + "canPreview='"
        + canPreview
        + '\''
        + ", "
        + "canEdit='"
        + canEdit
        + '\''
        + "}";
  }
}
