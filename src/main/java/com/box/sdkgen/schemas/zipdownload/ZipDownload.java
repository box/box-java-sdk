package com.box.sdkgen.schemas.zipdownload;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class ZipDownload extends SerializableObject {

  @JsonProperty("download_url")
  protected String downloadUrl;

  @JsonProperty("status_url")
  protected String statusUrl;

  @JsonProperty("expires_at")
  protected String expiresAt;

  @JsonProperty("name_conflicts")
  protected List<List<ZipDownloadNameConflictsField>> nameConflicts;

  public ZipDownload() {
    super();
  }

  protected ZipDownload(ZipDownloadBuilder builder) {
    super();
    this.downloadUrl = builder.downloadUrl;
    this.statusUrl = builder.statusUrl;
    this.expiresAt = builder.expiresAt;
    this.nameConflicts = builder.nameConflicts;
  }

  public String getDownloadUrl() {
    return downloadUrl;
  }

  public String getStatusUrl() {
    return statusUrl;
  }

  public String getExpiresAt() {
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

  public static class ZipDownloadBuilder {

    protected String downloadUrl;

    protected String statusUrl;

    protected String expiresAt;

    protected List<List<ZipDownloadNameConflictsField>> nameConflicts;

    public ZipDownloadBuilder downloadUrl(String downloadUrl) {
      this.downloadUrl = downloadUrl;
      return this;
    }

    public ZipDownloadBuilder statusUrl(String statusUrl) {
      this.statusUrl = statusUrl;
      return this;
    }

    public ZipDownloadBuilder expiresAt(String expiresAt) {
      this.expiresAt = expiresAt;
      return this;
    }

    public ZipDownloadBuilder nameConflicts(
        List<List<ZipDownloadNameConflictsField>> nameConflicts) {
      this.nameConflicts = nameConflicts;
      return this;
    }

    public ZipDownload build() {
      return new ZipDownload(this);
    }
  }
}
