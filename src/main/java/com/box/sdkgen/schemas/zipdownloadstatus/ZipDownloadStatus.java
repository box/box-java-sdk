package com.box.sdkgen.schemas.zipdownloadstatus;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/** The status of a `zip` archive being downloaded. */
@JsonFilter("nullablePropertyFilter")
public class ZipDownloadStatus extends SerializableObject {

  /** The total number of files in the archive. */
  @JsonProperty("total_file_count")
  protected Long totalFileCount;

  /** The number of files that have already been downloaded. */
  @JsonProperty("downloaded_file_count")
  protected Long downloadedFileCount;

  /**
   * The number of files that have been skipped as they could not be downloaded. In many cases this
   * is due to permission issues that have surfaced between the creation of the request for the
   * archive and the archive being downloaded.
   */
  @JsonProperty("skipped_file_count")
  protected Long skippedFileCount;

  /**
   * The number of folders that have been skipped as they could not be downloaded. In many cases
   * this is due to permission issues that have surfaced between the creation of the request for the
   * archive and the archive being downloaded.
   */
  @JsonProperty("skipped_folder_count")
  protected Long skippedFolderCount;

  /** The state of the archive being downloaded. */
  @JsonDeserialize(
      using = ZipDownloadStatusStateField.ZipDownloadStatusStateFieldDeserializer.class)
  @JsonSerialize(using = ZipDownloadStatusStateField.ZipDownloadStatusStateFieldSerializer.class)
  protected EnumWrapper<ZipDownloadStatusStateField> state;

  public ZipDownloadStatus() {
    super();
  }

  protected ZipDownloadStatus(Builder builder) {
    super();
    this.totalFileCount = builder.totalFileCount;
    this.downloadedFileCount = builder.downloadedFileCount;
    this.skippedFileCount = builder.skippedFileCount;
    this.skippedFolderCount = builder.skippedFolderCount;
    this.state = builder.state;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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

  public static class Builder extends NullableFieldTracker {

    protected Long totalFileCount;

    protected Long downloadedFileCount;

    protected Long skippedFileCount;

    protected Long skippedFolderCount;

    protected EnumWrapper<ZipDownloadStatusStateField> state;

    public Builder totalFileCount(Long totalFileCount) {
      this.totalFileCount = totalFileCount;
      return this;
    }

    public Builder downloadedFileCount(Long downloadedFileCount) {
      this.downloadedFileCount = downloadedFileCount;
      return this;
    }

    public Builder skippedFileCount(Long skippedFileCount) {
      this.skippedFileCount = skippedFileCount;
      return this;
    }

    public Builder skippedFolderCount(Long skippedFolderCount) {
      this.skippedFolderCount = skippedFolderCount;
      return this;
    }

    public Builder state(ZipDownloadStatusStateField state) {
      this.state = new EnumWrapper<ZipDownloadStatusStateField>(state);
      return this;
    }

    public Builder state(EnumWrapper<ZipDownloadStatusStateField> state) {
      this.state = state;
      return this;
    }

    public ZipDownloadStatus build() {
      return new ZipDownloadStatus(this);
    }
  }
}
