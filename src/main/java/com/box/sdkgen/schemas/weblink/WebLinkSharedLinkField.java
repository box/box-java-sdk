package com.box.sdkgen.schemas.weblink;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class WebLinkSharedLinkField extends SerializableObject {

  protected final String url;

  @JsonProperty("download_url")
  protected String downloadUrl;

  @JsonProperty("vanity_url")
  protected String vanityUrl;

  @JsonProperty("vanity_name")
  protected String vanityName;

  @JsonDeserialize(
      using = WebLinkSharedLinkAccessField.WebLinkSharedLinkAccessFieldDeserializer.class)
  @JsonSerialize(using = WebLinkSharedLinkAccessField.WebLinkSharedLinkAccessFieldSerializer.class)
  protected EnumWrapper<WebLinkSharedLinkAccessField> access;

  @JsonDeserialize(
      using =
          WebLinkSharedLinkEffectiveAccessField.WebLinkSharedLinkEffectiveAccessFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          WebLinkSharedLinkEffectiveAccessField.WebLinkSharedLinkEffectiveAccessFieldSerializer
              .class)
  @JsonProperty("effective_access")
  protected final EnumWrapper<WebLinkSharedLinkEffectiveAccessField> effectiveAccess;

  @JsonDeserialize(
      using =
          WebLinkSharedLinkEffectivePermissionField
              .WebLinkSharedLinkEffectivePermissionFieldDeserializer.class)
  @JsonSerialize(
      using =
          WebLinkSharedLinkEffectivePermissionField
              .WebLinkSharedLinkEffectivePermissionFieldSerializer.class)
  @JsonProperty("effective_permission")
  protected final EnumWrapper<WebLinkSharedLinkEffectivePermissionField> effectivePermission;

  @JsonProperty("unshared_at")
  protected String unsharedAt;

  @JsonProperty("is_password_enabled")
  protected final boolean isPasswordEnabled;

  protected WebLinkSharedLinkPermissionsField permissions;

  @JsonProperty("download_count")
  protected final long downloadCount;

  @JsonProperty("preview_count")
  protected final long previewCount;

  public WebLinkSharedLinkField(
      @JsonProperty("url") String url,
      @JsonProperty("effective_access")
          EnumWrapper<WebLinkSharedLinkEffectiveAccessField> effectiveAccess,
      @JsonProperty("effective_permission")
          EnumWrapper<WebLinkSharedLinkEffectivePermissionField> effectivePermission,
      @JsonProperty("is_password_enabled") boolean isPasswordEnabled,
      @JsonProperty("download_count") long downloadCount,
      @JsonProperty("preview_count") long previewCount) {
    super();
    this.url = url;
    this.effectiveAccess = effectiveAccess;
    this.effectivePermission = effectivePermission;
    this.isPasswordEnabled = isPasswordEnabled;
    this.downloadCount = downloadCount;
    this.previewCount = previewCount;
  }

  public WebLinkSharedLinkField(
      String url,
      WebLinkSharedLinkEffectiveAccessField effectiveAccess,
      WebLinkSharedLinkEffectivePermissionField effectivePermission,
      boolean isPasswordEnabled,
      long downloadCount,
      long previewCount) {
    super();
    this.url = url;
    this.effectiveAccess = new EnumWrapper<WebLinkSharedLinkEffectiveAccessField>(effectiveAccess);
    this.effectivePermission =
        new EnumWrapper<WebLinkSharedLinkEffectivePermissionField>(effectivePermission);
    this.isPasswordEnabled = isPasswordEnabled;
    this.downloadCount = downloadCount;
    this.previewCount = previewCount;
  }

  protected WebLinkSharedLinkField(WebLinkSharedLinkFieldBuilder builder) {
    super();
    this.url = builder.url;
    this.downloadUrl = builder.downloadUrl;
    this.vanityUrl = builder.vanityUrl;
    this.vanityName = builder.vanityName;
    this.access = builder.access;
    this.effectiveAccess = builder.effectiveAccess;
    this.effectivePermission = builder.effectivePermission;
    this.unsharedAt = builder.unsharedAt;
    this.isPasswordEnabled = builder.isPasswordEnabled;
    this.permissions = builder.permissions;
    this.downloadCount = builder.downloadCount;
    this.previewCount = builder.previewCount;
  }

  public String getUrl() {
    return url;
  }

  public String getDownloadUrl() {
    return downloadUrl;
  }

  public String getVanityUrl() {
    return vanityUrl;
  }

  public String getVanityName() {
    return vanityName;
  }

  public EnumWrapper<WebLinkSharedLinkAccessField> getAccess() {
    return access;
  }

  public EnumWrapper<WebLinkSharedLinkEffectiveAccessField> getEffectiveAccess() {
    return effectiveAccess;
  }

  public EnumWrapper<WebLinkSharedLinkEffectivePermissionField> getEffectivePermission() {
    return effectivePermission;
  }

  public String getUnsharedAt() {
    return unsharedAt;
  }

  public boolean getIsPasswordEnabled() {
    return isPasswordEnabled;
  }

  public WebLinkSharedLinkPermissionsField getPermissions() {
    return permissions;
  }

  public long getDownloadCount() {
    return downloadCount;
  }

  public long getPreviewCount() {
    return previewCount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WebLinkSharedLinkField casted = (WebLinkSharedLinkField) o;
    return Objects.equals(url, casted.url)
        && Objects.equals(downloadUrl, casted.downloadUrl)
        && Objects.equals(vanityUrl, casted.vanityUrl)
        && Objects.equals(vanityName, casted.vanityName)
        && Objects.equals(access, casted.access)
        && Objects.equals(effectiveAccess, casted.effectiveAccess)
        && Objects.equals(effectivePermission, casted.effectivePermission)
        && Objects.equals(unsharedAt, casted.unsharedAt)
        && Objects.equals(isPasswordEnabled, casted.isPasswordEnabled)
        && Objects.equals(permissions, casted.permissions)
        && Objects.equals(downloadCount, casted.downloadCount)
        && Objects.equals(previewCount, casted.previewCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        url,
        downloadUrl,
        vanityUrl,
        vanityName,
        access,
        effectiveAccess,
        effectivePermission,
        unsharedAt,
        isPasswordEnabled,
        permissions,
        downloadCount,
        previewCount);
  }

  @Override
  public String toString() {
    return "WebLinkSharedLinkField{"
        + "url='"
        + url
        + '\''
        + ", "
        + "downloadUrl='"
        + downloadUrl
        + '\''
        + ", "
        + "vanityUrl='"
        + vanityUrl
        + '\''
        + ", "
        + "vanityName='"
        + vanityName
        + '\''
        + ", "
        + "access='"
        + access
        + '\''
        + ", "
        + "effectiveAccess='"
        + effectiveAccess
        + '\''
        + ", "
        + "effectivePermission='"
        + effectivePermission
        + '\''
        + ", "
        + "unsharedAt='"
        + unsharedAt
        + '\''
        + ", "
        + "isPasswordEnabled='"
        + isPasswordEnabled
        + '\''
        + ", "
        + "permissions='"
        + permissions
        + '\''
        + ", "
        + "downloadCount='"
        + downloadCount
        + '\''
        + ", "
        + "previewCount='"
        + previewCount
        + '\''
        + "}";
  }

  public static class WebLinkSharedLinkFieldBuilder {

    protected final String url;

    protected String downloadUrl;

    protected String vanityUrl;

    protected String vanityName;

    protected EnumWrapper<WebLinkSharedLinkAccessField> access;

    protected final EnumWrapper<WebLinkSharedLinkEffectiveAccessField> effectiveAccess;

    protected final EnumWrapper<WebLinkSharedLinkEffectivePermissionField> effectivePermission;

    protected String unsharedAt;

    protected final boolean isPasswordEnabled;

    protected WebLinkSharedLinkPermissionsField permissions;

    protected final long downloadCount;

    protected final long previewCount;

    public WebLinkSharedLinkFieldBuilder(
        String url,
        EnumWrapper<WebLinkSharedLinkEffectiveAccessField> effectiveAccess,
        EnumWrapper<WebLinkSharedLinkEffectivePermissionField> effectivePermission,
        boolean isPasswordEnabled,
        long downloadCount,
        long previewCount) {
      this.url = url;
      this.effectiveAccess = effectiveAccess;
      this.effectivePermission = effectivePermission;
      this.isPasswordEnabled = isPasswordEnabled;
      this.downloadCount = downloadCount;
      this.previewCount = previewCount;
    }

    public WebLinkSharedLinkFieldBuilder(
        String url,
        WebLinkSharedLinkEffectiveAccessField effectiveAccess,
        WebLinkSharedLinkEffectivePermissionField effectivePermission,
        boolean isPasswordEnabled,
        long downloadCount,
        long previewCount) {
      this.url = url;
      this.effectiveAccess =
          new EnumWrapper<WebLinkSharedLinkEffectiveAccessField>(effectiveAccess);
      this.effectivePermission =
          new EnumWrapper<WebLinkSharedLinkEffectivePermissionField>(effectivePermission);
      this.isPasswordEnabled = isPasswordEnabled;
      this.downloadCount = downloadCount;
      this.previewCount = previewCount;
    }

    public WebLinkSharedLinkFieldBuilder downloadUrl(String downloadUrl) {
      this.downloadUrl = downloadUrl;
      return this;
    }

    public WebLinkSharedLinkFieldBuilder vanityUrl(String vanityUrl) {
      this.vanityUrl = vanityUrl;
      return this;
    }

    public WebLinkSharedLinkFieldBuilder vanityName(String vanityName) {
      this.vanityName = vanityName;
      return this;
    }

    public WebLinkSharedLinkFieldBuilder access(WebLinkSharedLinkAccessField access) {
      this.access = new EnumWrapper<WebLinkSharedLinkAccessField>(access);
      return this;
    }

    public WebLinkSharedLinkFieldBuilder access(EnumWrapper<WebLinkSharedLinkAccessField> access) {
      this.access = access;
      return this;
    }

    public WebLinkSharedLinkFieldBuilder unsharedAt(String unsharedAt) {
      this.unsharedAt = unsharedAt;
      return this;
    }

    public WebLinkSharedLinkFieldBuilder permissions(
        WebLinkSharedLinkPermissionsField permissions) {
      this.permissions = permissions;
      return this;
    }

    public WebLinkSharedLinkField build() {
      return new WebLinkSharedLinkField(this);
    }
  }
}
