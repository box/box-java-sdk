package com.box.sdkgen.schemas.zipdownloadrequest;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class ZipDownloadRequest extends SerializableObject {

  protected final List<ZipDownloadRequestItemsField> items;

  @JsonProperty("download_file_name")
  protected String downloadFileName;

  public ZipDownloadRequest(@JsonProperty("items") List<ZipDownloadRequestItemsField> items) {
    super();
    this.items = items;
  }

  protected ZipDownloadRequest(Builder builder) {
    super();
    this.items = builder.items;
    this.downloadFileName = builder.downloadFileName;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public List<ZipDownloadRequestItemsField> getItems() {
    return items;
  }

  public String getDownloadFileName() {
    return downloadFileName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ZipDownloadRequest casted = (ZipDownloadRequest) o;
    return Objects.equals(items, casted.items)
        && Objects.equals(downloadFileName, casted.downloadFileName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(items, downloadFileName);
  }

  @Override
  public String toString() {
    return "ZipDownloadRequest{"
        + "items='"
        + items
        + '\''
        + ", "
        + "downloadFileName='"
        + downloadFileName
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final List<ZipDownloadRequestItemsField> items;

    protected String downloadFileName;

    public Builder(List<ZipDownloadRequestItemsField> items) {
      super();
      this.items = items;
    }

    public Builder downloadFileName(String downloadFileName) {
      this.downloadFileName = downloadFileName;
      return this;
    }

    public ZipDownloadRequest build() {
      return new ZipDownloadRequest(this);
    }
  }
}
