package com.box.sdk;

/**
 * Upload avatar response
 */
public class AvatarUploadResponse {
    private final String small;
    private final String large;
    private final String preview;

    AvatarUploadResponse(String small, String large, String preview) {
        this.small = small;
        this.large = large;
        this.preview = preview;
    }

    /**
     * URL with the small representation of Avatar.
     *
     * @return URL with the small representation of Avatar
     */
    public String getSmall() {
        return small;
    }

    /**
     * URL with the large representation of Avatar.
     *
     * @return URL with the large representation of Avatar
     */
    public String getLarge() {
        return large;
    }

    /**
     * URL with the preview representation of Avatar.
     *
     * @return URL with the preview representation of Avatar
     */
    public String getPreview() {
        return preview;
    }
}
