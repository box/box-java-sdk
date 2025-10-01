package com.box.sdkgen.managers.chunkeduploads;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CreateFileUploadSessionRequestBody extends SerializableObject {

  /** The ID of the folder to upload the new file to. */
  @JsonProperty("folder_id")
  protected final String folderId;

  /** The total number of bytes of the file to be uploaded. */
  @JsonProperty("file_size")
  protected final long fileSize;

  /** The name of new file. */
  @JsonProperty("file_name")
  protected final String fileName;

  public CreateFileUploadSessionRequestBody(
      @JsonProperty("folder_id") String folderId,
      @JsonProperty("file_size") long fileSize,
      @JsonProperty("file_name") String fileName) {
    super();
    this.folderId = folderId;
    this.fileSize = fileSize;
    this.fileName = fileName;
  }

  public String getFolderId() {
    return folderId;
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
    CreateFileUploadSessionRequestBody casted = (CreateFileUploadSessionRequestBody) o;
    return Objects.equals(folderId, casted.folderId)
        && Objects.equals(fileSize, casted.fileSize)
        && Objects.equals(fileName, casted.fileName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(folderId, fileSize, fileName);
  }

  @Override
  public String toString() {
    return "CreateFileUploadSessionRequestBody{"
        + "folderId='"
        + folderId
        + '\''
        + ", "
        + "fileSize='"
        + fileSize
        + '\''
        + ", "
        + "fileName='"
        + fileName
        + '\''
        + "}";
  }
}
