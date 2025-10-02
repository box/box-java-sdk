package com.box.sdkgen.managers.chunkeduploads;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CreateFileUploadSessionForExistingFileRequestBody extends SerializableObject {

  /** The total number of bytes of the file to be uploaded. */
  @JsonProperty("file_size")
  protected final long fileSize;

  /** The optional new name of new file. */
  @JsonProperty("file_name")
  protected String fileName;

  public CreateFileUploadSessionForExistingFileRequestBody(
      @JsonProperty("file_size") long fileSize) {
    super();
    this.fileSize = fileSize;
  }

  protected CreateFileUploadSessionForExistingFileRequestBody(Builder builder) {
    super();
    this.fileSize = builder.fileSize;
    this.fileName = builder.fileName;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public long getFileSize() {
    return fileSize;
  }

  public String getFileName() {
    return fileName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateFileUploadSessionForExistingFileRequestBody casted =
        (CreateFileUploadSessionForExistingFileRequestBody) o;
    return Objects.equals(fileSize, casted.fileSize) && Objects.equals(fileName, casted.fileName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fileSize, fileName);
  }

  @Override
  public String toString() {
    return "CreateFileUploadSessionForExistingFileRequestBody{"
        + "fileSize='"
        + fileSize
        + '\''
        + ", "
        + "fileName='"
        + fileName
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final long fileSize;

    protected String fileName;

    public Builder(long fileSize) {
      super();
      this.fileSize = fileSize;
    }

    public Builder fileName(String fileName) {
      this.fileName = fileName;
      return this;
    }

    public CreateFileUploadSessionForExistingFileRequestBody build() {
      return new CreateFileUploadSessionForExistingFileRequestBody(this);
    }
  }
}
