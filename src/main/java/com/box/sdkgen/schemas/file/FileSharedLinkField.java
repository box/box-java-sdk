package com.box.sdkgen.schemas.file;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class FileSharedLinkField extends SerializableObject {

  protected final String url;

  @JsonProperty("download_url")
  protected String downloadUrl;

  @JsonProperty("vanity_url")
  protected String vanityUrl;

  @JsonProperty("vanity_name")
  protected String vanityName;

  @JsonDeserialize(using = FileSharedLinkAccessField.FileSharedLinkAccessFieldDeserializer.class)
  @JsonSerialize(using = FileSharedLinkAccessField.FileSharedLinkAccessFieldSerializer.class)
  protected EnumWrapper<FileSharedLinkAccessField> access;

  @JsonDeserialize(
      using =
          FileSharedLinkEffectiveAccessField.FileSharedLinkEffectiveAccessFieldDeserializer.class)
  @JsonSerialize(
      using = FileSharedLinkEffectiveAccessField.FileSharedLinkEffectiveAccessFieldSerializer.class)
  @JsonProperty("effective_access")
  protected final EnumWrapper<FileSharedLinkEffectiveAccessField> effectiveAccess;

  @JsonDeserialize(
      using =
          FileSharedLinkEffectivePermissionField.FileSharedLinkEffectivePermissionFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          FileSharedLinkEffectivePermissionField.FileSharedLinkEffectivePermissionFieldSerializer
              .class)
  @JsonProperty("effective_permission")
  protected final EnumWrapper<FileSharedLinkEffectivePermissionField> effectivePermission;

  @JsonProperty("unshared_at")
  protected String unsharedAt;

  @JsonProperty("is_password_enabled")
  protected final boolean isPasswordEnabled;

  protected FileSharedLinkPermissionsField permissions;

  @JsonProperty("download_count")
  protected final long downloadCount;

  @JsonProperty("preview_count")
  protected final long previewCount;

  public FileSharedLinkField(
      @JsonProperty("url") String url,
      @JsonProperty("effective_access")
          EnumWrapper<FileSharedLinkEffectiveAccessField> effectiveAccess,
      @JsonProperty("effective_permission")
          EnumWrapper<FileSharedLinkEffectivePermissionField> effectivePermission,
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

  public FileSharedLinkField(
      String url,
      FileSharedLinkEffectiveAccessField effectiveAccess,
      FileSharedLinkEffectivePermissionField effectivePermission,
      boolean isPasswordEnabled,
      long downloadCount,
      long previewCount) {
    super();
    this.url = url;
    this.effectiveAccess = new EnumWrapper<FileSharedLinkEffectiveAccessField>(effectiveAccess);
    this.effectivePermission =
        new EnumWrapper<FileSharedLinkEffectivePermissionField>(effectivePermission);
    this.isPasswordEnabled = isPasswordEnabled;
    this.downloadCount = downloadCount;
    this.previewCount = previewCount;
  }

  protected FileSharedLinkField(FileSharedLinkFieldBuilder builder) {
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

  public EnumWrapper<FileSharedLinkAccessField> getAccess() {
    return access;
  }

  public EnumWrapper<FileSharedLinkEffectiveAccessField> getEffectiveAccess() {
    return effectiveAccess;
  }

  public EnumWrapper<FileSharedLinkEffectivePermissionField> getEffectivePermission() {
    return effectivePermission;
  }

  public String getUnsharedAt() {
    return unsharedAt;
  }

  public boolean getIsPasswordEnabled() {
    return isPasswordEnabled;
  }

  public FileSharedLinkPermissionsField getPermissions() {
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
    FileSharedLinkField casted = (FileSharedLinkField) o;
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
    return "FileSharedLinkField{"
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

  public static class FileSharedLinkFieldBuilder {

    protected final String url;

    protected String downloadUrl;

    protected String vanityUrl;

    protected String vanityName;

    protected EnumWrapper<FileSharedLinkAccessField> access;

    protected final EnumWrapper<FileSharedLinkEffectiveAccessField> effectiveAccess;

    protected final EnumWrapper<FileSharedLinkEffectivePermissionField> effectivePermission;

    protected String unsharedAt;

    protected final boolean isPasswordEnabled;

    protected FileSharedLinkPermissionsField permissions;

    protected final long downloadCount;

    protected final long previewCount;

    public FileSharedLinkFieldBuilder(
        String url,
        EnumWrapper<FileSharedLinkEffectiveAccessField> effectiveAccess,
        EnumWrapper<FileSharedLinkEffectivePermissionField> effectivePermission,
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

    public FileSharedLinkFieldBuilder(
        String url,
        FileSharedLinkEffectiveAccessField effectiveAccess,
        FileSharedLinkEffectivePermissionField effectivePermission,
        boolean isPasswordEnabled,
        long downloadCount,
        long previewCount) {
      this.url = url;
      this.effectiveAccess = new EnumWrapper<FileSharedLinkEffectiveAccessField>(effectiveAccess);
      this.effectivePermission =
          new EnumWrapper<FileSharedLinkEffectivePermissionField>(effectivePermission);
      this.isPasswordEnabled = isPasswordEnabled;
      this.downloadCount = downloadCount;
      this.previewCount = previewCount;
    }

    public FileSharedLinkFieldBuilder downloadUrl(String downloadUrl) {
      this.downloadUrl = downloadUrl;
      return this;
    }

    public FileSharedLinkFieldBuilder vanityUrl(String vanityUrl) {
      this.vanityUrl = vanityUrl;
      return this;
    }

    public FileSharedLinkFieldBuilder vanityName(String vanityName) {
      this.vanityName = vanityName;
      return this;
    }

    public FileSharedLinkFieldBuilder access(FileSharedLinkAccessField access) {
      this.access = new EnumWrapper<FileSharedLinkAccessField>(access);
      return this;
    }

    public FileSharedLinkFieldBuilder access(EnumWrapper<FileSharedLinkAccessField> access) {
      this.access = access;
      return this;
    }

    public FileSharedLinkFieldBuilder unsharedAt(String unsharedAt) {
      this.unsharedAt = unsharedAt;
      return this;
    }

    public FileSharedLinkFieldBuilder permissions(FileSharedLinkPermissionsField permissions) {
      this.permissions = permissions;
      return this;
    }

    public FileSharedLinkField build() {
      return new FileSharedLinkField(this);
    }
  }
}
