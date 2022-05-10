package com.box.sdk;

/**
 *
 */
public class AvatarUploadResponse {
    private final String small;
    private final String large;
    private final String preview;

    public AvatarUploadResponse(String small, String large, String preview) {
        this.small = small;
        this.large = large;
        this.preview = preview;
    }

    public String getSmall() {
        return small;
    }

    public String getLarge() {
        return large;
    }

    public String getPreview() {
        return preview;
    }
}
