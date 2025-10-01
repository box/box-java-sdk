package com.box.sdkgen.networking.fetchoptions;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.InputStream;

/** Multipart item for multipart data */
public class MultipartItem {

  /** Name of the part */
  public final String partName;

  /** Data of the part */
  public JsonNode data;

  /** File stream of the part */
  public InputStream fileStream;

  /** File name of the part */
  public String fileName;

  /** Content type of the part */
  public String contentType;

  public MultipartItem(String partName) {
    this.partName = partName;
  }

  protected MultipartItem(Builder builder) {
    this.partName = builder.partName;
    this.data = builder.data;
    this.fileStream = builder.fileStream;
    this.fileName = builder.fileName;
    this.contentType = builder.contentType;
  }

  public String getPartName() {
    return partName;
  }

  public JsonNode getData() {
    return data;
  }

  public InputStream getFileStream() {
    return fileStream;
  }

  public String getFileName() {
    return fileName;
  }

  public String getContentType() {
    return contentType;
  }

  public static class Builder {

    protected final String partName;

    protected JsonNode data;

    protected InputStream fileStream;

    protected String fileName;

    protected String contentType;

    public Builder(String partName) {
      this.partName = partName;
    }

    public Builder data(JsonNode data) {
      this.data = data;
      return this;
    }

    public Builder fileStream(InputStream fileStream) {
      this.fileStream = fileStream;
      return this;
    }

    public Builder fileName(String fileName) {
      this.fileName = fileName;
      return this;
    }

    public Builder contentType(String contentType) {
      this.contentType = contentType;
      return this;
    }

    public MultipartItem build() {
      return new MultipartItem(this);
    }
  }
}
