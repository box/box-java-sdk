package com.box.sdkgen.schemas.filerequestupdaterequest;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class FileRequestUpdateRequest extends SerializableObject {

  protected String title;

  protected String description;

  @JsonDeserialize(
      using =
          FileRequestUpdateRequestStatusField.FileRequestUpdateRequestStatusFieldDeserializer.class)
  @JsonSerialize(
      using =
          FileRequestUpdateRequestStatusField.FileRequestUpdateRequestStatusFieldSerializer.class)
  protected EnumWrapper<FileRequestUpdateRequestStatusField> status;

  @JsonProperty("is_email_required")
  protected Boolean isEmailRequired;

  @JsonProperty("is_description_required")
  protected Boolean isDescriptionRequired;

  @JsonProperty("expires_at")
  protected String expiresAt;

  public FileRequestUpdateRequest() {
    super();
  }

  protected FileRequestUpdateRequest(FileRequestUpdateRequestBuilder builder) {
    super();
    this.title = builder.title;
    this.description = builder.description;
    this.status = builder.status;
    this.isEmailRequired = builder.isEmailRequired;
    this.isDescriptionRequired = builder.isDescriptionRequired;
    this.expiresAt = builder.expiresAt;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public EnumWrapper<FileRequestUpdateRequestStatusField> getStatus() {
    return status;
  }

  public Boolean getIsEmailRequired() {
    return isEmailRequired;
  }

  public Boolean getIsDescriptionRequired() {
    return isDescriptionRequired;
  }

  public String getExpiresAt() {
    return expiresAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FileRequestUpdateRequest casted = (FileRequestUpdateRequest) o;
    return Objects.equals(title, casted.title)
        && Objects.equals(description, casted.description)
        && Objects.equals(status, casted.status)
        && Objects.equals(isEmailRequired, casted.isEmailRequired)
        && Objects.equals(isDescriptionRequired, casted.isDescriptionRequired)
        && Objects.equals(expiresAt, casted.expiresAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        title, description, status, isEmailRequired, isDescriptionRequired, expiresAt);
  }

  @Override
  public String toString() {
    return "FileRequestUpdateRequest{"
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
        + "}";
  }

  public static class FileRequestUpdateRequestBuilder {

    protected String title;

    protected String description;

    protected EnumWrapper<FileRequestUpdateRequestStatusField> status;

    protected Boolean isEmailRequired;

    protected Boolean isDescriptionRequired;

    protected String expiresAt;

    public FileRequestUpdateRequestBuilder title(String title) {
      this.title = title;
      return this;
    }

    public FileRequestUpdateRequestBuilder description(String description) {
      this.description = description;
      return this;
    }

    public FileRequestUpdateRequestBuilder status(FileRequestUpdateRequestStatusField status) {
      this.status = new EnumWrapper<FileRequestUpdateRequestStatusField>(status);
      return this;
    }

    public FileRequestUpdateRequestBuilder status(
        EnumWrapper<FileRequestUpdateRequestStatusField> status) {
      this.status = status;
      return this;
    }

    public FileRequestUpdateRequestBuilder isEmailRequired(Boolean isEmailRequired) {
      this.isEmailRequired = isEmailRequired;
      return this;
    }

    public FileRequestUpdateRequestBuilder isDescriptionRequired(Boolean isDescriptionRequired) {
      this.isDescriptionRequired = isDescriptionRequired;
      return this;
    }

    public FileRequestUpdateRequestBuilder expiresAt(String expiresAt) {
      this.expiresAt = expiresAt;
      return this;
    }

    public FileRequestUpdateRequest build() {
      return new FileRequestUpdateRequest(this);
    }
  }
}
