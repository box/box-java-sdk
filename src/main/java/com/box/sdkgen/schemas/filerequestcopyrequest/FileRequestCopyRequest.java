package com.box.sdkgen.schemas.filerequestcopyrequest;

import com.box.sdkgen.schemas.filerequestupdaterequest.FileRequestUpdateRequest;
import com.box.sdkgen.schemas.filerequestupdaterequest.FileRequestUpdateRequestStatusField;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class FileRequestCopyRequest extends FileRequestUpdateRequest {

  protected final FileRequestCopyRequestFolderField folder;

  public FileRequestCopyRequest(@JsonProperty("folder") FileRequestCopyRequestFolderField folder) {
    super();
    this.folder = folder;
  }

  protected FileRequestCopyRequest(Builder builder) {
    super(builder);
    this.folder = builder.folder;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public FileRequestCopyRequestFolderField getFolder() {
    return folder;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FileRequestCopyRequest casted = (FileRequestCopyRequest) o;
    return Objects.equals(title, casted.title)
        && Objects.equals(description, casted.description)
        && Objects.equals(status, casted.status)
        && Objects.equals(isEmailRequired, casted.isEmailRequired)
        && Objects.equals(isDescriptionRequired, casted.isDescriptionRequired)
        && Objects.equals(expiresAt, casted.expiresAt)
        && Objects.equals(folder, casted.folder);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        title, description, status, isEmailRequired, isDescriptionRequired, expiresAt, folder);
  }

  @Override
  public String toString() {
    return "FileRequestCopyRequest{"
        + "title='"
        + title
        + '\''
        + ", "
        + "description='"
        + description
        + '\''
        + ", "
        + "status='"
        + status
        + '\''
        + ", "
        + "isEmailRequired='"
        + isEmailRequired
        + '\''
        + ", "
        + "isDescriptionRequired='"
        + isDescriptionRequired
        + '\''
        + ", "
        + "expiresAt='"
        + expiresAt
        + '\''
        + ", "
        + "folder='"
        + folder
        + '\''
        + "}";
  }

  public static class Builder extends FileRequestUpdateRequest.Builder {

    protected final FileRequestCopyRequestFolderField folder;

    public Builder(FileRequestCopyRequestFolderField folder) {
      super();
      this.folder = folder;
    }

    @Override
    public Builder title(String title) {
      this.title = title;
      return this;
    }

    @Override
    public Builder description(String description) {
      this.description = description;
      return this;
    }

    @Override
    public Builder status(FileRequestUpdateRequestStatusField status) {
      this.status = new EnumWrapper<FileRequestUpdateRequestStatusField>(status);
      return this;
    }

    @Override
    public Builder status(EnumWrapper<FileRequestUpdateRequestStatusField> status) {
      this.status = status;
      return this;
    }

    @Override
    public Builder isEmailRequired(Boolean isEmailRequired) {
      this.isEmailRequired = isEmailRequired;
      return this;
    }

    @Override
    public Builder isDescriptionRequired(Boolean isDescriptionRequired) {
      this.isDescriptionRequired = isDescriptionRequired;
      return this;
    }

    @Override
    public Builder expiresAt(Date expiresAt) {
      this.expiresAt = expiresAt;
      return this;
    }

    public FileRequestCopyRequest build() {
      return new FileRequestCopyRequest(this);
    }
  }
}
