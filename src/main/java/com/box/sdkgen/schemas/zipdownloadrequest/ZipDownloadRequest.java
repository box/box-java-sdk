package com.box.sdkgen.schemas.zipdownloadrequest;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class ZipDownloadRequest extends SerializableObject {

  protected final List<ZipDownloadRequestItemsField> items;

  @JsonProperty("download_file_name")
  protected String downloadFileName;

  public ZipDownloadRequest(@JsonProperty("items") List<ZipDownloadRequestItemsField> items) {
    super();
    this.items = items;
  }

  protected ZipDownloadRequest(ZipDownloadRequestBuilder builder) {
    super();
    this.items = builder.items;
    this.downloadFileName = builder.downloadFileName;
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

  public static class ZipDownloadRequestBuilder {

    protected final List<ZipDownloadRequestItemsField> items;

    protected String downloadFileName;

    public ZipDownloadRequestBuilder(List<ZipDownloadRequestItemsField> items) {
      this.items = items;
    }

    public ZipDownloadRequestBuilder downloadFileName(String downloadFileName) {
      this.downloadFileName = downloadFileName;
      return this;
    }

    public ZipDownloadRequest build() {
      return new ZipDownloadRequest(this);
    }
  }
}
