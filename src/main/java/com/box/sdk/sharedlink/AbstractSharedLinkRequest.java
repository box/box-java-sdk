package com.box.sdk.sharedlink;

import com.box.sdk.BoxSharedLink;
import java.util.Date;

class AbstractSharedLinkRequest<T extends AbstractSharedLinkRequest<T>> {
    private final BoxSharedLink link = new BoxSharedLink();

    /**
     * Sets the access level of this shared link.
     *
     * @param access the new access level of this shared link.
     * @return this request.
     */
    public T access(BoxSharedLink.Access access) {
        getLink().setAccess(access);
        return (T) this;
    }

    /**
     * Sets the password of this shared link.
     *
     * @param password the password of this shared link.
     * @return this request.
     */
    public T password(String password) {
        getLink().setPassword(password);
        return (T) this;
    }

    /**
     * Sets the time that this shared link will be deactivated.
     *
     * @param unsharedDate the time that this shared link will be deactivated.
     * @return this request.
     */
    public T unsharedDate(Date unsharedDate) {
        getLink().setUnsharedDate(unsharedDate);
        return (T) this;
    }

    /**
     * Sets vanity name used to create vanity URL.
     * For example:
     * vanityName = myCustomName
     * will produce vanityUrl as:
     * https://app.box.com/v/myCustomName
     * Custom URLs should not be used when sharing sensitive content
     * as vanity URLs are a lot easier to guess than regular shared links.
     *
     * @param vanityName Vanity name. Vanity name must be at least 12 characters long.
     * @return this request.
     */
    public T vanityName(String vanityName) {
        getLink().setVanityName(vanityName);
        return (T) this;
    }

    public BoxSharedLink asSharedLink() {
        return getLink();
    }

    BoxSharedLink getLink() {
        return link;
    }
}
