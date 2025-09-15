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

@JsonFilter("nullablePropertyFilter")
public class ZipDownload extends SerializableObject {

  @JsonProperty("download_url")
  protected String downloadUrl;

  @JsonProperty("status_url")
  protected String statusUrl;

  @JsonProperty("expires_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime expiresAt;

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
