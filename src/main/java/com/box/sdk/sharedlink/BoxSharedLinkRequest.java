package com.box.sdk.sharedlink;

import com.box.sdk.BoxSharedLink;

/**
 * Represents request to create shared link with permissions.
 */
public class BoxSharedLinkRequest extends AbstractSharedLinkRequest<BoxSharedLinkRequest> {

    /**
     * Sets the permissions associated with this shared link.
     *
     * @param canDownload whether the shared link can be downloaded.
     * @param canPreview  whether the shared link can be previewed.
     * @return this request.
     */
    public BoxSharedLinkRequest permissions(boolean canDownload, boolean canPreview) {
        BoxSharedLink.Permissions permissions = new BoxSharedLink.Permissions();
        permissions.setCanDownload(canDownload);
        permissions.setCanPreview(canPreview);
        getLink().setPermissions(permissions);
        return this;
    }

}
