package com.box.sdkgen.schemas.folder;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class FolderSharedLinkField extends SerializableObject {

  protected final String url;

  @JsonProperty("download_url")
  protected String downloadUrl;

  @JsonProperty("vanity_url")
  protected String vanityUrl;

  @JsonProperty("vanity_name")
  protected String vanityName;

  @JsonDeserialize(
      using = FolderSharedLinkAccessField.FolderSharedLinkAccessFieldDeserializer.class)
  @JsonSerialize(using = FolderSharedLinkAccessField.FolderSharedLinkAccessFieldSerializer.class)
  protected EnumWrapper<FolderSharedLinkAccessField> access;

  @JsonDeserialize(
      using =
          FolderSharedLinkEffectiveAccessField.FolderSharedLinkEffectiveAccessFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          FolderSharedLinkEffectiveAccessField.FolderSharedLinkEffectiveAccessFieldSerializer.class)
  @JsonProperty("effective_access")
  protected final EnumWrapper<FolderSharedLinkEffectiveAccessField> effectiveAccess;

  @JsonDeserialize(
      using =
          FolderSharedLinkEffectivePermissionField
              .FolderSharedLinkEffectivePermissionFieldDeserializer.class)
  @JsonSerialize(
      using =
          FolderSharedLinkEffectivePermissionField
              .FolderSharedLinkEffectivePermissionFieldSerializer.class)
  @JsonProperty("effective_permission")
  protected final EnumWrapper<FolderSharedLinkEffectivePermissionField> effectivePermission;

  @JsonProperty("unshared_at")
  protected String unsharedAt;

  @JsonProperty("is_password_enabled")
  protected final boolean isPasswordEnabled;

  protected FolderSharedLinkPermissionsField permissions;

  @JsonProperty("download_count")
  protected final long downloadCount;

  @JsonProperty("preview_count")
  protected final long previewCount;

  public FolderSharedLinkField(
      @JsonProperty("url") String url,
      @JsonProperty("effective_access")
          EnumWrapper<FolderSharedLinkEffectiveAccessField> effectiveAccess,
      @JsonProperty("effective_permission")
          EnumWrapper<FolderSharedLinkEffectivePermissionField> effectivePermission,
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

  public FolderSharedLinkField(
      String url,
      FolderSharedLinkEffectiveAccessField effectiveAccess,
      FolderSharedLinkEffectivePermissionField effectivePermission,
      boolean isPasswordEnabled,
      long downloadCount,
      long previewCount) {
    super();
    this.url = url;
    this.effectiveAccess = new EnumWrapper<FolderSharedLinkEffectiveAccessField>(effectiveAccess);
    this.effectivePermission =
        new EnumWrapper<FolderSharedLinkEffectivePermissionField>(effectivePermission);
    this.isPasswordEnabled = isPasswordEnabled;
    this.downloadCount = downloadCount;
    this.previewCount = previewCount;
  }

  protected FolderSharedLinkField(FolderSharedLinkFieldBuilder builder) {
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

  public EnumWrapper<FolderSharedLinkAccessField> getAccess() {
    return access;
  }

  public EnumWrapper<FolderSharedLinkEffectiveAccessField> getEffectiveAccess() {
    return effectiveAccess;
  }

  public EnumWrapper<FolderSharedLinkEffectivePermissionField> getEffectivePermission() {
    return effectivePermission;
  }

  public String getUnsharedAt() {
    return unsharedAt;
  }

  public boolean getIsPasswordEnabled() {
    return isPasswordEnabled;
  }

  public FolderSharedLinkPermissionsField getPermissions() {
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
    FolderSharedLinkField casted = (FolderSharedLinkField) o;
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
    return "FolderSharedLinkField{"
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

  public static class FolderSharedLinkFieldBuilder {

    protected final String url;

    protected String downloadUrl;

    protected String vanityUrl;

    protected String vanityName;

    protected EnumWrapper<FolderSharedLinkAccessField> access;

    protected final EnumWrapper<FolderSharedLinkEffectiveAccessField> effectiveAccess;

    protected final EnumWrapper<FolderSharedLinkEffectivePermissionField> effectivePermission;

    protected String unsharedAt;

    protected final boolean isPasswordEnabled;

    protected FolderSharedLinkPermissionsField permissions;

    protected final long downloadCount;

    protected final long previewCount;

    public FolderSharedLinkFieldBuilder(
        String url,
        EnumWrapper<FolderSharedLinkEffectiveAccessField> effectiveAccess,
        EnumWrapper<FolderSharedLinkEffectivePermissionField> effectivePermission,
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

    public FolderSharedLinkFieldBuilder(
        String url,
        FolderSharedLinkEffectiveAccessField effectiveAccess,
        FolderSharedLinkEffectivePermissionField effectivePermission,
        boolean isPasswordEnabled,
        long downloadCount,
        long previewCount) {
      this.url = url;
      this.effectiveAccess = new EnumWrapper<FolderSharedLinkEffectiveAccessField>(effectiveAccess);
      this.effectivePermission =
          new EnumWrapper<FolderSharedLinkEffectivePermissionField>(effectivePermission);
      this.isPasswordEnabled = isPasswordEnabled;
      this.downloadCount = downloadCount;
      this.previewCount = previewCount;
    }

    public FolderSharedLinkFieldBuilder downloadUrl(String downloadUrl) {
      this.downloadUrl = downloadUrl;
      return this;
    }

    public FolderSharedLinkFieldBuilder vanityUrl(String vanityUrl) {
      this.vanityUrl = vanityUrl;
      return this;
    }

    public FolderSharedLinkFieldBuilder vanityName(String vanityName) {
      this.vanityName = vanityName;
      return this;
    }

    public FolderSharedLinkFieldBuilder access(FolderSharedLinkAccessField access) {
      this.access = new EnumWrapper<FolderSharedLinkAccessField>(access);
      return this;
    }

    public FolderSharedLinkFieldBuilder access(EnumWrapper<FolderSharedLinkAccessField> access) {
      this.access = access;
      return this;
    }

    public FolderSharedLinkFieldBuilder unsharedAt(String unsharedAt) {
      this.unsharedAt = unsharedAt;
      return this;
    }

    public FolderSharedLinkFieldBuilder permissions(FolderSharedLinkPermissionsField permissions) {
      this.permissions = permissions;
      return this;
    }

    public FolderSharedLinkField build() {
      return new FolderSharedLinkField(this);
    }
  }
}
