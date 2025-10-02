package com.box.sdkgen.schemas.zipdownload;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

/** Represents a successful request to create a `zip` archive of a list of files and folders. */
@JsonFilter("nullablePropertyFilter")
public class ZipDownload extends SerializableObject {

  /**
   * The URL that can be used to download the `zip` archive. A `Get` request to this URL will start
   * streaming the items requested. By default, this URL is only valid for a few seconds, until the
   * `expires_at` time, unless a download is started after which it is valid for the duration of the
   * download.
   *
   * <p>It is important to note that the domain and path of this URL might change between API calls,
   * and therefore it's important to use this URL as-is.
   */
  @JsonProperty("download_url")
  protected String downloadUrl;

  /**
   * The URL that can be used to get the status of the `zip` archive being downloaded. A `Get`
   * request to this URL will return the number of files in the archive as well as the number of
   * items already downloaded or skipped. By default, this URL is only valid for a few seconds,
   * until the `expires_at` time, unless a download is started after which the URL is valid for 12
   * hours from the start of the download.
   *
   * <p>It is important to note that the domain and path of this URL might change between API calls,
   * and therefore it's important to use this URL as-is.
   */
  @JsonProperty("status_url")
  protected String statusUrl;

  /**
   * The time and date when this archive will expire. After this time the `status_url` and
   * `download_url` will return an error.
   *
   * <p>By default, these URLs are only valid for a few seconds, unless a download is started after
   * which the `download_url` is valid for the duration of the download, and the `status_url` is
   * valid for 12 hours from the start of the download.
   */
  @JsonProperty("expires_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime expiresAt;

  /**
   * A list of conflicts that occurred when trying to create the archive. This would occur when
   * multiple items have been requested with the same name.
   *
   * <p>To solve these conflicts, the API will automatically rename an item and return a mapping
   * between the original item's name and its new name.
   *
   * <p>For every conflict, both files will be renamed and therefore this list will always be a
   * multiple of 2.
   */
  @JsonProperty("name_conflicts")
  protected List<List<ZipDownloadNameConflictsField>> nameConflicts;

  public ZipDownload() {
    super();
  }

  protected ZipDownload(Builder builder) {
    super();
    this.downloadUrl = builder.downloadUrl;
    this.statusUrl = builder.statusUrl;
    this.expiresAt = builder.expiresAt;
    this.nameConflicts = builder.nameConflicts;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getDownloadUrl() {
    return downloadUrl;
  }

  public String getStatusUrl() {
    return statusUrl;
  }

  public OffsetDateTime getExpiresAt() {
    return expiresAt;
  }

  public List<List<ZipDownloadNameConflictsField>> getNameConflicts() {
    return nameConflicts;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ZipDownload casted = (ZipDownload) o;
    return Objects.equals(downloadUrl, casted.downloadUrl)
        && Objects.equals(statusUrl, casted.statusUrl)
        && Objects.equals(expiresAt, casted.expiresAt)
        && Objects.equals(nameConflicts, casted.nameConflicts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(downloadUrl, statusUrl, expiresAt, nameConflicts);
  }

  @Override
  public String toString() {
    return "ZipDownload{"
        + "downloadUrl='"
        + downloadUrl
        + '\''
        + ", "
        + "statusUrl='"
        + statusUrl
        + '\''
        + ", "
        + "expiresAt='"
        + expiresAt
        + '\''
        + ", "
        + "nameConflicts='"
        + nameConflicts
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String downloadUrl;

    protected String statusUrl;

    protected OffsetDateTime expiresAt;

    protected List<List<ZipDownloadNameConflictsField>> nameConflicts;

    public Builder downloadUrl(String downloadUrl) {
      this.downloadUrl = downloadUrl;
      return this;
    }

    public Builder statusUrl(String statusUrl) {
      this.statusUrl = statusUrl;
      return this;
    }

    public Builder expiresAt(OffsetDateTime expiresAt) {
      this.expiresAt = expiresAt;
      return this;
    }

    public Builder nameConflicts(List<List<ZipDownloadNameConflictsField>> nameConflicts) {
      this.nameConflicts = nameConflicts;
      return this;
    }

    public ZipDownload build() {
      return new ZipDownload(this);
    }
  }
}
