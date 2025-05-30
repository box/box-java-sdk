package com.box.sdkgen.networking.fetchoptions;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.InputStream;

public class MultipartItem {

  public final String partName;

  public JsonNode data;

  public InputStream fileStream;

  public String fileName;

  public String contentType;

  public MultipartItem(String partName) {
    this.partName = partName;
  }

  protected MultipartItem(MultipartItemBuilder builder) {
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

  public static class MultipartItemBuilder {

    protected final String partName;

    protected JsonNode data;

    protected InputStream fileStream;

    protected String fileName;

    protected String contentType;

    public MultipartItemBuilder(String partName) {
      this.partName = partName;
    }

    public MultipartItemBuilder data(JsonNode data) {
      this.data = data;
      return this;
    }

    public MultipartItemBuilder fileStream(InputStream fileStream) {
      this.fileStream = fileStream;
      return this;
    }

    public MultipartItemBuilder fileName(String fileName) {
      this.fileName = fileName;
      return this;
    }

    public MultipartItemBuilder contentType(String contentType) {
      this.contentType = contentType;
      return this;
    }

    public MultipartItem build() {
      return new MultipartItem(this);
    }
  }
}
