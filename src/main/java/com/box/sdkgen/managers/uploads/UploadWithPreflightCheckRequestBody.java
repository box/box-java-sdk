package com.box.sdkgen.managers.uploads;

import java.io.InputStream;

public class UploadWithPreflightCheckRequestBody {

  public final UploadWithPreflightCheckRequestBodyAttributesField attributes;

  public final InputStream file;

  public String fileFileName;

  public String fileContentType;

  public UploadWithPreflightCheckRequestBody(
      UploadWithPreflightCheckRequestBodyAttributesField attributes, InputStream file) {
    this.attributes = attributes;
    this.file = file;
  }

  protected UploadWithPreflightCheckRequestBody(Builder builder) {
    this.attributes = builder.attributes;
    this.file = builder.file;
    this.fileFileName = builder.fileFileName;
    this.fileContentType = builder.fileContentType;
  }

  public UploadWithPreflightCheckRequestBodyAttributesField getAttributes() {
    return attributes;
  }

  public InputStream getFile() {
    return file;
  }

  public String getFileFileName() {
    return fileFileName;
  }

  public String getFileContentType() {
    return fileContentType;
  }

  public static class Builder {

    protected final UploadWithPreflightCheckRequestBodyAttributesField attributes;

    protected final InputStream file;

    protected String fileFileName;

    protected String fileContentType;

    public Builder(
        UploadWithPreflightCheckRequestBodyAttributesField attributes, InputStream file) {
      this.attributes = attributes;
      this.file = file;
    }

    public Builder fileFileName(String fileFileName) {
      this.fileFileName = fileFileName;
      return this;
    }

    public Builder fileContentType(String fileContentType) {
      this.fileContentType = fileContentType;
      return this;
    }

    public UploadWithPreflightCheckRequestBody build() {
      return new UploadWithPreflightCheckRequestBody(this);
    }
  }
}
