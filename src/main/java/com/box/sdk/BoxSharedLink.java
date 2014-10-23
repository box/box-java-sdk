package com.box.sdk;

import java.text.ParseException;
import java.util.Date;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

public class BoxSharedLink extends BoxJSONObject {
    private String url;
    private String downloadUrl;
    private String vanityUrl;
    private boolean isPasswordEnabled;
    private Date unsharedAt;
    private long downloadCount;
    private long previewCount;
    private Access access;
    private Permissions permissions;

    public enum Access {
        DEFAULT (null),
        OPEN ("open"),
        COMPANY ("company"),
        COLLABORATORS ("collaborators");

        private final String jsonValue;

        private Access(String jsonValue) {
            this.jsonValue = jsonValue;
        }

        @Override
        public String toString() {
            return this.jsonValue;
        }
    }

    public BoxSharedLink() { }

    public BoxSharedLink(String json) {
        super(json);
    }

    BoxSharedLink(JsonObject jsonObject) {
        super(jsonObject);
    }

    BoxSharedLink(BoxSharedLink.Access access, Date unshareDate, BoxSharedLink.Permissions permissions) {
        this.setAccess(access);
        this.setPermissions(permissions);

        if (unshareDate != null) {
            this.setUnsharedDate(unshareDate);
        }
    }

    public String getURL() {
        return this.url;
    }

    public String getDownloadURL() {
        return this.downloadUrl;
    }

    public String getVanityURL() {
        return this.vanityUrl;
    }

    public boolean getIsPasswordEnabled() {
        return this.isPasswordEnabled;
    }

    public Date getUnsharedDate() {
        return this.unsharedAt;
    }

    public void setUnsharedDate(Date unsharedDate) {
        this.unsharedAt = unsharedDate;
        this.addPendingChange("unshared_at", unsharedDate.toString());
    }

    public long getDownloadCount() {
        return this.downloadCount;
    }

    public long getPreviewCount() {
        return this.previewCount;
    }

    public Access getAccess() {
        return this.access;
    }

    public void setAccess(Access access) {
        this.access = access;
        this.addPendingChange("access", access.toString());
    }

    public Permissions getPermissions() {
        return this.permissions;
    }

    public void setPermissions(Permissions permissions) {
        if (this.permissions == permissions) {
            return;
        }

        this.removeChildObject("permissions");
        this.permissions = permissions;
        this.addChildObject("permissions", permissions);
    }

    @Override
    void parseJSONMember(JsonObject.Member member) {
        JsonValue value = member.getValue();
        try {
            switch (member.getName()) {
                case "url":
                    this.url = value.asString();
                    break;
                case "download_url":
                    this.downloadUrl = value.asString();
                    break;
                case "vanity_url":
                    this.vanityUrl = value.asString();
                    break;
                case "is_password_enabled":
                    this.isPasswordEnabled = value.asBoolean();
                    break;
                case "unshared_at":
                    this.unsharedAt = BoxDateParser.parse(value.asString());
                    break;
                case "download_count":
                    this.downloadCount = Double.valueOf(value.toString()).longValue();
                    break;
                case "preview_count":
                    this.previewCount = Double.valueOf(value.toString()).longValue();
                    break;
                case "access":
                    String accessString = value.asString().toUpperCase();
                    this.access = Access.valueOf(accessString);
                    break;
                case "permissions":
                    if (this.permissions == null) {
                        this.setPermissions(new Permissions(value.asObject()));
                    } else {
                        this.permissions.update(value.asObject());
                    }
                    break;
                default:
                    break;
            }
        } catch (ParseException e) {
            assert false : "A ParseException indicates a bug in the SDK.";
        }
    }

    public static class Permissions extends BoxJSONObject {
        private boolean canDownload;
        private boolean canPreview;

        public Permissions() { }

        public Permissions(String json) {
            super(json);
        }

        Permissions(JsonObject jsonObject) {
            super(jsonObject);
        }

        public boolean getCanDownload() {
            return this.canDownload;
        }

        public void setCanDownload(boolean enabled) {
            this.canDownload = enabled;
            this.addPendingChange("can_download", enabled);
        }

        public boolean getCanPreview() {
            return this.canPreview;
        }

        public void setCanPreview(boolean enabled) {
            this.canPreview = enabled;
            this.addPendingChange("can_preview", enabled);
        }

        @Override
        void parseJSONMember(JsonObject.Member member) {
            JsonValue value = member.getValue();
            switch (member.getName()) {
                case "can_download":
                    this.canDownload = value.asBoolean();
                    break;
                case "can_preview":
                    this.canPreview = value.asBoolean();
                    break;
                default:
                    break;
            }
        }
    }
}
