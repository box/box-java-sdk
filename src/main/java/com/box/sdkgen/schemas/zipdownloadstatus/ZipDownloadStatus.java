package com.box.sdkgen.schemas.zipdownloadstatus;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class ZipDownloadStatus extends SerializableObject {

  @JsonProperty("total_file_count")
  protected Long totalFileCount;

  @JsonProperty("downloaded_file_count")
  protected Long downloadedFileCount;

  @JsonProperty("skipped_file_count")
  protected Long skippedFileCount;

  @JsonProperty("skipped_folder_count")
  protected Long skippedFolderCount;

  @JsonDeserialize(
      using = ZipDownloadStatusStateField.ZipDownloadStatusStateFieldDeserializer.class)
  @JsonSerialize(using = ZipDownloadStatusStateField.ZipDownloadStatusStateFieldSerializer.class)
  protected EnumWrapper<ZipDownloadStatusStateField> state;

  public ZipDownloadStatus() {
    super();
  }

  protected ZipDownloadStatus(ZipDownloadStatusBuilder builder) {
    super();
    this.totalFileCount = builder.totalFileCount;
    this.downloadedFileCount = builder.downloadedFileCount;
    this.skippedFileCount = builder.skippedFileCount;
    this.skippedFolderCount = builder.skippedFolderCount;
    this.state = builder.state;
  }

  public Long getTotalFileCount() {
    return totalFileCount;
  }

  public Long getDownloadedFileCount() {
    return downloadedFileCount;
  }

  public Long getSkippedFileCount() {
    return skippedFileCount;
  }

  public Long getSkippedFolderCount() {
    return skippedFolderCount;
  }

  public EnumWrapper<ZipDownloadStatusStateField> getState() {
    return state;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ZipDownloadStatus casted = (ZipDownloadStatus) o;
    return Objects.equals(totalFileCount, casted.totalFileCount)
        && Objects.equals(downloadedFileCount, casted.downloadedFileCount)
        && Objects.equals(skippedFileCount, casted.skippedFileCount)
        && Objects.equals(skippedFolderCount, casted.skippedFolderCount)
        && Objects.equals(state, casted.state);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        totalFileCount, downloadedFileCount, skippedFileCount, skippedFolderCount, state);
  }

  @Override
  public String toString() {
    return "ZipDownloadStatus{"
        + "totalFileCount='"
        + totalFileCount
        + '\''
        + ", "
        + "downloadedFileCount='"
        + downloadedFileCount
        + '\''
        + ", "
        + "skippedFileCount='"
        + skippedFileCount
        + '\''
        + ", "
        + "skippedFolderCount='"
        + skippedFolderCount
        + '\''
        + ", "
        + "state='"
        + state
        + '\''
        + "}";
  }

  public static class ZipDownloadStatusBuilder {

    protected Long totalFileCount;

    protected Long downloadedFileCount;

    protected Long skippedFileCount;

    protected Long skippedFolderCount;

    protected EnumWrapper<ZipDownloadStatusStateField> state;

    public ZipDownloadStatusBuilder totalFileCount(Long totalFileCount) {
      this.totalFileCount = totalFileCount;
      return this;
    }

    public ZipDownloadStatusBuilder downloadedFileCount(Long downloadedFileCount) {
      this.downloadedFileCount = downloadedFileCount;
      return this;
    }

    public ZipDownloadStatusBuilder skippedFileCount(Long skippedFileCount) {
      this.skippedFileCount = skippedFileCount;
      return this;
    }

    public ZipDownloadStatusBuilder skippedFolderCount(Long skippedFolderCount) {
      this.skippedFolderCount = skippedFolderCount;
      return this;
    }

    public ZipDownloadStatusBuilder state(ZipDownloadStatusStateField state) {
      this.state = new EnumWrapper<ZipDownloadStatusStateField>(state);
      return this;
    }

    public ZipDownloadStatusBuilder state(EnumWrapper<ZipDownloadStatusStateField> state) {
      this.state = state;
      return this;
    }

    public ZipDownloadStatus build() {
      return new ZipDownloadStatus(this);
    }
  }
}
