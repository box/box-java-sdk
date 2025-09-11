package com.box.sdkgen.schemas.weblink;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.OffsetDateTime;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class WebLinkSharedLinkField extends SerializableObject {

  protected final String url;

  @JsonProperty("download_url")
  @Nullable
  protected String downloadUrl;

  @JsonProperty("vanity_url")
  @Nullable
  protected String vanityUrl;

  @JsonProperty("vanity_name")
  @Nullable
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
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  @Nullable
  protected OffsetDateTime unsharedAt;

  @JsonProperty("is_password_enabled")
  protected final boolean isPasswordEnabled;

  protected WebLinkSharedLinkPermissionsField permissions;

  @JsonProperty("download_count")
  protected final long downloadCount;

  @JsonProperty("preview_count")
  protected final long previewCount;

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

  public WebLinkSharedLinkField(
      String url,
      WebLinkSharedLinkEffectiveAccessField effectiveAccess,
      EnumWrapper<WebLinkSharedLinkEffectivePermissionField> effectivePermission,
      boolean isPasswordEnabled,
      long downloadCount,
      long previewCount) {
    super();
    this.url = url;
    this.effectiveAccess = new EnumWrapper<WebLinkSharedLinkEffectiveAccessField>(effectiveAccess);
    this.effectivePermission = effectivePermission;
    this.isPasswordEnabled = isPasswordEnabled;
    this.downloadCount = downloadCount;
    this.previewCount = previewCount;
  }

  public WebLinkSharedLinkField(
      String url,
      EnumWrapper<WebLinkSharedLinkEffectiveAccessField> effectiveAccess,
      WebLinkSharedLinkEffectivePermissionField effectivePermission,
      boolean isPasswordEnabled,
      long downloadCount,
      long previewCount) {
    super();
    this.url = url;
    this.effectiveAccess = effectiveAccess;
    this.effectivePermission =
        new EnumWrapper<WebLinkSharedLinkEffectivePermissionField>(effectivePermission);
    this.isPasswordEnabled = isPasswordEnabled;
    this.downloadCount = downloadCount;
    this.previewCount = previewCount;
  }

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

  protected WebLinkSharedLinkField(Builder builder) {
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
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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

  public OffsetDateTime getUnsharedAt() {
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

  public static class Builder extends NullableFieldTracker {

    protected final String url;

    protected String downloadUrl;

    protected String vanityUrl;

    protected String vanityName;

    protected EnumWrapper<WebLinkSharedLinkAccessField> access;

    protected final EnumWrapper<WebLinkSharedLinkEffectiveAccessField> effectiveAccess;

    protected final EnumWrapper<WebLinkSharedLinkEffectivePermissionField> effectivePermission;

    protected OffsetDateTime unsharedAt;

    protected final boolean isPasswordEnabled;

    protected WebLinkSharedLinkPermissionsField permissions;

    protected final long downloadCount;

    protected final long previewCount;

    public Builder(
        String url,
        WebLinkSharedLinkEffectiveAccessField effectiveAccess,
        WebLinkSharedLinkEffectivePermissionField effectivePermission,
        boolean isPasswordEnabled,
        long downloadCount,
        long previewCount) {
      super();
      this.url = url;
      this.effectiveAccess =
          new EnumWrapper<WebLinkSharedLinkEffectiveAccessField>(effectiveAccess);
      this.effectivePermission =
          new EnumWrapper<WebLinkSharedLinkEffectivePermissionField>(effectivePermission);
      this.isPasswordEnabled = isPasswordEnabled;
      this.downloadCount = downloadCount;
      this.previewCount = previewCount;
    }

    public Builder(
        String url,
        WebLinkSharedLinkEffectiveAccessField effectiveAccess,
        EnumWrapper<WebLinkSharedLinkEffectivePermissionField> effectivePermission,
        boolean isPasswordEnabled,
        long downloadCount,
        long previewCount) {
      super();
      this.url = url;
      this.effectiveAccess =
          new EnumWrapper<WebLinkSharedLinkEffectiveAccessField>(effectiveAccess);
      this.effectivePermission = effectivePermission;
      this.isPasswordEnabled = isPasswordEnabled;
      this.downloadCount = downloadCount;
      this.previewCount = previewCount;
    }

    public Builder(
        String url,
        EnumWrapper<WebLinkSharedLinkEffectiveAccessField> effectiveAccess,
        WebLinkSharedLinkEffectivePermissionField effectivePermission,
        boolean isPasswordEnabled,
        long downloadCount,
        long previewCount) {
      super();
      this.url = url;
      this.effectiveAccess = effectiveAccess;
      this.effectivePermission =
          new EnumWrapper<WebLinkSharedLinkEffectivePermissionField>(effectivePermission);
      this.isPasswordEnabled = isPasswordEnabled;
      this.downloadCount = downloadCount;
      this.previewCount = previewCount;
    }

    public Builder(
        String url,
        EnumWrapper<WebLinkSharedLinkEffectiveAccessField> effectiveAccess,
        EnumWrapper<WebLinkSharedLinkEffectivePermissionField> effectivePermission,
        boolean isPasswordEnabled,
        long downloadCount,
        long previewCount) {
      super();
      this.url = url;
      this.effectiveAccess = effectiveAccess;
      this.effectivePermission = effectivePermission;
      this.isPasswordEnabled = isPasswordEnabled;
      this.downloadCount = downloadCount;
      this.previewCount = previewCount;
    }

    public Builder downloadUrl(String downloadUrl) {
      this.downloadUrl = downloadUrl;
      this.markNullableFieldAsSet("download_url");
      return this;
    }

    public Builder vanityUrl(String vanityUrl) {
      this.vanityUrl = vanityUrl;
      this.markNullableFieldAsSet("vanity_url");
      return this;
    }

    public Builder vanityName(String vanityName) {
      this.vanityName = vanityName;
      this.markNullableFieldAsSet("vanity_name");
      return this;
    }

    public Builder access(WebLinkSharedLinkAccessField access) {
      this.access = new EnumWrapper<WebLinkSharedLinkAccessField>(access);
      return this;
    }

    public Builder access(EnumWrapper<WebLinkSharedLinkAccessField> access) {
      this.access = access;
      return this;
    }

    public Builder unsharedAt(OffsetDateTime unsharedAt) {
      this.unsharedAt = unsharedAt;
      this.markNullableFieldAsSet("unshared_at");
      return this;
    }

    public Builder permissions(WebLinkSharedLinkPermissionsField permissions) {
      this.permissions = permissions;
      return this;
    }

    public WebLinkSharedLinkField build() {
      return new WebLinkSharedLinkField(this);
    }
  }
}
