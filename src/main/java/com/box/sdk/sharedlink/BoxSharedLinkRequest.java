package com.box.sdk.sharedlink;

import com.box.sdk.BoxSharedLink;

/** Represents request to create shared link with permissions. */
public class BoxSharedLinkRequest extends AbstractSharedLinkRequest<BoxSharedLinkRequest> {

  /**
   * Sets the permissions associated with this shared link.
   *
   * @param canDownload whether the shared link can be downloaded.
   * @param canPreview whether the shared link can be previewed.
   * @param canEdit whether the file shared with the link can be edited.
   * @return this request.
   */
  public BoxSharedLinkRequest permissions(
      boolean canDownload, boolean canPreview, boolean canEdit) {
    BoxSharedLink.Permissions permissions = new BoxSharedLink.Permissions();
    permissions.setCanDownload(canDownload);
    permissions.setCanPreview(canPreview);
    permissions.setCanEdit(canEdit);
    getLink().setPermissions(permissions);
    return this;
  }

  /**
   * Sets the permissions associated with this shared link.
   *
   * @param canDownload whether the shared link can be downloaded.
   * @param canPreview whether the shared link can be previewed.
   * @return this request.
   */
  public BoxSharedLinkRequest permissions(boolean canDownload, boolean canPreview) {
    return this.permissions(canDownload, canPreview, false);
  }
}
