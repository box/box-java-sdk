package com.box.sdkgen.managers.uploads;

import java.io.InputStream;

public class UploadFileVersionRequestBody {

  /**
   * The additional attributes of the file being uploaded. Mainly the name and the parent folder.
   * These attributes are part of the multi part request body and are in JSON format.
   *
   * <p>&lt;Message warning&gt;
   *
   * <p>The `attributes` part of the body must come **before** the `file` part. Requests that do not
   * follow this format when uploading the file will receive a HTTP `400` error with a
   * `metadata_after_file_contents` error code.
   *
   * <p>&lt;/Message&gt;
   */
  public final UploadFileVersionRequestBodyAttributesField attributes;

  /**
   * The content of the file to upload to Box.
   *
   * <p>&lt;Message warning&gt;
   *
   * <p>The `attributes` part of the body must come **before** the `file` part. Requests that do not
   * follow this format when uploading the file will receive a HTTP `400` error with a
   * `metadata_after_file_contents` error code.
   *
   * <p>&lt;/Message&gt;
   */
  public final InputStream file;

  public String fileFileName;

  public String fileContentType;

  public UploadFileVersionRequestBody(
      UploadFileVersionRequestBodyAttributesField attributes, InputStream file) {
    this.attributes = attributes;
    this.file = file;
  }

  protected UploadFileVersionRequestBody(Builder builder) {
    this.attributes = builder.attributes;
    this.file = builder.file;
    this.fileFileName = builder.fileFileName;
    this.fileContentType = builder.fileContentType;
  }

  public UploadFileVersionRequestBodyAttributesField getAttributes() {
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

    protected final UploadFileVersionRequestBodyAttributesField attributes;

    protected final InputStream file;

    protected String fileFileName;

    protected String fileContentType;

    public Builder(UploadFileVersionRequestBodyAttributesField attributes, InputStream file) {
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

    public UploadFileVersionRequestBody build() {
      return new UploadFileVersionRequestBody(this);
    }
  }
}
